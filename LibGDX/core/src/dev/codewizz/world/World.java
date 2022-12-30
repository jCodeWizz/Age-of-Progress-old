package dev.codewizz.world;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dongbat.jbump.util.MathUtils;

import dev.codewizz.input.MouseInput;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Utils;
import dev.codewizz.utils.WNoise;
import dev.codewizz.world.objects.Cow;
import dev.codewizz.world.objects.Herd;
import dev.codewizz.world.objects.Tree;
import dev.codewizz.world.pathfinding.CellGraph;
import dev.codewizz.world.tiles.WaterTile;

public class World {

	public static final int WORLD_SIZE_W = 48;
	public static final int WORLD_SIZE_H = 96;
	public static final int RADIUS = 2;
	
	public Cell[][] grid;
	public List<GameObject> objects = new CopyOnWriteArrayList<>();
	
	public CellGraph cellGraph;
	
	public WNoise noise = new WNoise();
	public WNoise terrainNoise = new WNoise();
	
	public World() {
		grid = new Cell[WORLD_SIZE_W][WORLD_SIZE_H];
		cellGraph = new CellGraph();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 2 == 0) {
					Cell cell= new Cell((i-(WORLD_SIZE_W/2)) * 64, (j-(WORLD_SIZE_H/2)) * 16, i, j, false, this);
					grid[i][j] = cell;
					cellGraph.addCell(cell);
				} else {
					Cell cell = new Cell((i-(WORLD_SIZE_W/2)) * 64 + 32, (j-(WORLD_SIZE_H/2)) * 16, i, j, true, this);
					grid[i][j] = cell;
					cellGraph.addCell(cell);
				}
			}
		}
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].init(cellGraph);
			}
		}
	}
	
	public void init() {
		Herd herd = new Herd();
		Random RANDOM = new Random();
		
		for(int i = 0; i < 7; i++) {
			herd.addMember(new Cow(RANDOM.nextInt(100), RANDOM.nextInt(100), herd));
		}
	
		herd.setLeader();
	
		this.objects.addAll(herd.getMembers());

		spawnRivers();
		spawnTree();
	}
	
	private void spawnRivers() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Cell cell = grid[i][j];
				
				float e = 5f;
				float n = (float) terrainNoise.noise(i * e, j * e);
				
				float d = Math.abs((n*2)-1);
				
				if(d > -0.1f && d < 0.5f) {
					cell.setTile(new WaterTile(cell));
				}
				
			}
		}
	}
	
	private void spawnTree() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Cell cell = grid[i][j];
				
				if(cell.tile.getType() == TileType.Base) {
					float e = 5f;
					float n = (float) noise.noise(i * e, j * e);
					
					if(n > 0.4f) {
						cell.tile.addObject(new Tree(cell.x, cell.y));
					}
				}
				
			}
		}
	}
	
	public void renderTiles(SpriteBatch b) {
		for (int i = grid[0].length - 1; i >= 0; i--) {
			for (int j = grid.length - 1; j >= 0; j--) {
				grid[j][i].render(b);
			}
		}
		
		if(!Main.PAUSED) {
			if(MouseInput.hoveringOverCell != null) {
				if(MouseInput.clear) {
					b.draw(Assets.getSprite("tile-highlight"), MouseInput.hoveringOverCell.x, MouseInput.hoveringOverCell.y);
				} else {
					b.draw(Assets.getSprite("tile-highlight-red"), MouseInput.hoveringOverCell.x, MouseInput.hoveringOverCell.y);
				}
				
				
				/*
				 * 
				 * TODO: Need to add a good way to select a tile and place it. This will be great when starting to work on menus.
				 * 
				 */
			}
		}

	}
	
	public void renderObjects(SpriteBatch b) {
		Collections.sort(objects);
		
		if(!Main.PAUSED) {
			for(GameObject object : objects) {
				object.update(Gdx.graphics.getDeltaTime());
			}
		}
		for(GameObject object : objects) {
			object.render(b);
		}
	}
	
	public void renderTileObjects(SpriteBatch objectBatch) {
		for (int i = grid[0].length - 1; i >= 0; i--) {
			for (int j = grid.length - 1; j >= 0; j--) {
				Tile tile = grid[j][i].tile;
				for(GameObject b : tile.getObjects()) {
					b.render(objectBatch);
				}
			}
		}
	}
	
	public Cell getCell(float x, float y) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].tile.getHitbox().contains(x, y)) {
					return grid[i][j];
				}
			}
		}
		
		return null;
	}
	
	public Cell getCellIndex(int x, int y) {
		x = MathUtils.clamp(x, 0, WORLD_SIZE_W-1);
		y = MathUtils.clamp(y, 0, WORLD_SIZE_H-1);
		
		return grid[x][y];
	}
	
	public void renderDebug() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(Utils.distance(grid[i][j].x + 32, MouseInput.coords.x) < RADIUS * 64 && Utils.distance(grid[i][j].y + 24, MouseInput.coords.y) < RADIUS * 48) {
					grid[i][j].drawBorders();
				}
			}
		}
	}
}
