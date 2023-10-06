package dev.codewizz.world;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.dongbat.jbump.util.MathUtils;

import dev.codewizz.gfx.Shaders;
import dev.codewizz.input.MouseInput;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Direction;
import dev.codewizz.utils.Utils;
import dev.codewizz.utils.WNoise;
import dev.codewizz.utils.saving.GameObjectData;
import dev.codewizz.utils.saving.WorldData;
import dev.codewizz.utils.serialization.RCDatabase;
import dev.codewizz.world.objects.Entity;
import dev.codewizz.world.objects.Tree;
import dev.codewizz.world.pathfinding.CellGraph;
import dev.codewizz.world.settlement.Settlement;
import dev.codewizz.world.tiles.ClayTile;
import dev.codewizz.world.tiles.FlowerTile;
import dev.codewizz.world.tiles.SandTile;
import dev.codewizz.world.tiles.WaterTile;

public class World {

	public static final int WORLD_SIZE_W = 48*2;
	public static final int WORLD_SIZE_H = 96*2;
	public static final int WORLD_SIZE_WP = WORLD_SIZE_W * 64;
	public static final int WORLD_SIZE_HP = WORLD_SIZE_H * 16;
	public static final int RADIUS = 2;
	public static final int MAX_RIVER_LENGTH = 1000;
	public static final float E = 0.5f;

	public Cell[][] grid;
	public List<GameObject> objects = new CopyOnWriteArrayList<>();
	public Settlement settlement;
	public Nature nature;

	public CellGraph cellGraph;

	public WNoise noise = new WNoise();
	public WNoise terrainNoise = new WNoise();

	public boolean showInfoSartMenu = true;

	public World() {
		grid = new Cell[WORLD_SIZE_W][WORLD_SIZE_H];
		cellGraph = new CellGraph();
		Main.inst.world = this;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 2 == 0) {
					Cell cell = new Cell((i - (WORLD_SIZE_W / 2)) * 64, (j - (WORLD_SIZE_H / 2)) * 16, i, j, false);
					grid[i][j] = cell;
					cellGraph.addCell(cell);
				} else {
					Cell cell = new Cell((i - (WORLD_SIZE_W / 2)) * 64 + 32, (j - (WORLD_SIZE_H / 2)) * 16, i, j, true);
					grid[i][j] = cell;
					cellGraph.addCell(cell);
				}
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].init(cellGraph, this);
			}
		}
		init();
	}

	public static World openWorld(String path) {
		File file = Gdx.files.internal(path).file();
		RCDatabase db = RCDatabase.DeserializeFromFile(file);

		World world = new World(WorldData.load(db));
		GameObjectData.load(db);

		return world;
	}

	public World(WorldData data) {
		Main.inst.world = this;

		this.grid = data.grid;
		this.settlement = data.settlement;
		this.objects = data.objects;
		this.showInfoSartMenu = data.showStartInfo;
		this.cellGraph = data.cellGraph;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].init(cellGraph, this);
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				try {
					grid[i][j].setTile(Tile.getTileFromType(data.tileTypes[i + (j * World.WORLD_SIZE_W)], grid[i][j]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void init() {
		spawnRivers();
		spawnTree();
		spawnResources();

		nature = new Nature(this);
		
		grid[20][20].setTile(new WaterTile(grid[20][20]));
		objects.add(new Tree(grid[20][20].x, grid[20][20].y));
	}

	public void start(Settlement s) {
		this.settlement = s;
		this.showInfoSartMenu = false;

		for (int i = 0; i < 5; i++) {
			this.settlement.addHermit(Utils.getRandom(-75, 75) + s.getX(), Utils.getRandom(-75, 75) + s.getY());
		}
	}

	private void spawnResources() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Cell cell = grid[i][j];

				if (cell.tile.getType() == TileType.Sand) {
					float e = 1f;
					float n = (float) noise.noise(i * e, j * e);

					if (n > 0.2f) {
						cell.setTile(new ClayTile(cell));
					}
				} else if (cell.tile.getType() == TileType.Base) {
					float e = 20f;
					float n = (float) noise.noise(i * e, j * e);

					if (n > 0.65f) {
						cell.setTile(new FlowerTile(cell));
					}
				}

			}
		}
	}

	private void spawnRivers() {
		Cell currentCell = grid[0][(int) (WORLD_SIZE_H / 2)];
		int length = 0;
		boolean done = false;

		List<Cell> cells = new CopyOnWriteArrayList<>();
		List<Cell> cellsSand = new CopyOnWriteArrayList<>();

		do {
			float sizeNoise = (float) terrainNoise.noise(currentCell.indexX, currentCell.indexY);
			int size = (int) (3 * ((sizeNoise + 1) / 2)) + 2;
			currentCell.setTile(new WaterTile(grid[currentCell.indexX][currentCell.indexY]));

			cells.addAll(currentCell.getCellsInRadius((int) (size)));
			cellsSand.addAll(currentCell.getCellsInRadius((int) (size * 2)));

			float noise = (float) terrainNoise.noise(currentCell.indexX * E, currentCell.indexY * E);
			int degrees = 90 + (int) (90 * noise);

			Direction dir = Direction.getDirFromDeg(degrees);
			Cell newCell = currentCell.getNeighbourCrossed(dir);
			if (newCell != null) {
				currentCell = newCell;
				length++;
			} else {
				if (length > 5) {
					done = true;
				}
			}
		} while (length < MAX_RIVER_LENGTH && done == false);

		for (int i = 0; i < cellsSand.size(); i++) {
			cellsSand.get(i).setTile(new SandTile(cellsSand.get(i)));
		}

		for (int i = 0; i < cells.size(); i++) {
			cells.get(i).setTile(new WaterTile(cells.get(i)));
		}
	}

	private void spawnTree() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Cell cell = grid[i][j];

				if (cell.tile.getType() == TileType.Base) {
					float e = 5f;
					float n = (float) noise.noise(i * e, j * e);

					if (n > 0.4f) {
						objects.add(new Tree(cell.x, cell.y + Utils.RANDOM.nextFloat()));
					}
				}
			}
		}
	}

	public void renderTiles(SpriteBatch b) {
		
		Vector3 p1 = Main.inst.camera.cam.unproject(new Vector3(0, 0, 0));
		Vector3 p2 = Main.inst.camera.cam.unproject(new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0));
		
		int x = (int)p1.x;
		int y = (int)p1.y;
		int x2 = (int)p2.x;
		int y2 = (int)p2.y;
		
		Rectangle screen = new Rectangle(x, y - (y - y2), (x2-x), (y-y2));
		
		for (int i = grid[0].length - 1; i >= 0; i--) {
			for (int j = grid.length - 1; j >= 0; j--) {

				if(screen.contains(grid[j][i].getMiddlePoint().x, grid[j][i].getMiddlePoint().y)) {
					grid[j][i].render(b);
				}
				
				
			}
		}

		if (!Main.PAUSED) {
			if (MouseInput.hoveringOverCell != null) {
				if (MouseInput.clear) {
					b.draw(Assets.getSprite("tile-highlight"), MouseInput.hoveringOverCell.x,
							MouseInput.hoveringOverCell.y);
				} else {
					b.draw(Assets.getSprite("tile-highlight-red"), MouseInput.hoveringOverCell.x,
							MouseInput.hoveringOverCell.y);
				}

				/*
				 * 
				 * TODO: Need to add a good way to select a tile and place it. This will be
				 * great when starting to work on menus.
				 * 
				 */
			}
		}

	}

	public void renderObjects(SpriteBatch b) {

		if (settlement != null)
			settlement.update(Gdx.graphics.getDeltaTime());
		if (nature != null)
			nature.update(Gdx.graphics.getDeltaTime());

		if (!Main.PAUSED) {
			for (GameObject object : objects) {
				object.update(Gdx.graphics.getDeltaTime());
			}
		}

		List<GameObject> o = getObjects();
		Collections.sort(o);

		for (GameObject object : o) {
			b.setShader(Shaders.defaultShader);
			if (object instanceof Entity) {
				if (((Entity) object).isSelected()) {
					b.setShader(Shaders.outlineShader);
				}
			}
			object.render(b);
		}
	}

	public Cell getCell(float x, float y) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].tile.getHitbox().contains(x, y)) {
					return grid[i][j];
				}
			}
		}

		return null;
	}

	public Cell getCellIndex(int x, int y) {
		x = MathUtils.clamp(x, 0, WORLD_SIZE_W - 1);
		y = MathUtils.clamp(y, 0, WORLD_SIZE_H - 1);

		return grid[x][y];
	}

	public List<GameObject> getObjects() {
		List<GameObject> list = new CopyOnWriteArrayList<>();

		list.addAll(objects);

		return list;
	}

	public void renderDebug() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (Utils.distance(grid[i][j].x + 32, MouseInput.coords.x) < RADIUS * 64
						&& Utils.distance(grid[i][j].y + 24, MouseInput.coords.y) < RADIUS * 48) {
					grid[i][j].drawBorders();
				}
			}
		}
	}

	public Cell findCell(float x, float y, int r, boolean filter, TileType... types) {
		ArrayList<Cell> cells = new ArrayList<>();
		ArrayList<TileType> t = new ArrayList<>();
		for (int i = 0; i < types.length; i++) {
			t.add(types[i]);
		}

		Cell cell = getCell(x, y);

		if(cell == null) return null;
		
		// if filter is on, tiletype should not be in list. if filer is off, tiletype
		// should be in list
		if (!evaluateTile(cell, filter, t)) {
			
			int m = r;
			int n = (int)((float)r * 1.5f);
			
			int xx = cell.indexX - m / 2;
			int yy = cell.indexY - n / 2;

			int i, k = 0, l = 0;

			while (k < m && l < n) {
				
				for (i = l; i < n; ++i) {
					Cell c = getCellIndex(k + xx, i + yy);
					if (evaluateTile(c, filter, t)) {
						cells.add(c);
					}
				}
				k++;

				for (i = k; i < m; ++i) {

					Cell c = getCellIndex(i + xx, n - 1 + yy);
					if (evaluateTile(c, filter, t)) {
						cells.add(c);
					}

				}
				n--;

				if (k < m) {
					for (i = n - 1; i >= l; --i) {

						Cell c = getCellIndex(m - 1 + xx, i + yy);
						if (evaluateTile(c, filter, t)) {
							cells.add(c);
						}
					}
					m--;
				}

				if (l < n) {
					for (i = m - 1; i >= k; --i) {

						Cell c = getCellIndex(i + xx, l + yy);
						if (evaluateTile(c, filter, t)) {
							cells.add(c);
						}
					}
					l++;
				}
			}
			
			if(!cells.isEmpty()) {
				return cells.get(cells.size()-1);
			}
			
		} else {
			return cell;
		}

		// not found, return null
		return null;
	}
	
	public Cell getRandomCell() {
		return grid[Utils.getRandom(0, WORLD_SIZE_W)][Utils.getRandom(0, WORLD_SIZE_H)];
	}

	private boolean evaluateTile(Cell cell, boolean filter, List<TileType> t) {
		return (!filter && t.contains(cell.tile.type)) || filter && !t.contains(cell.tile.getType());
	}

	public Nature getNature() {
		return this.nature;
	}
}
