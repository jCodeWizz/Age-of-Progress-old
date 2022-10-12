package dev.codewizz.world;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Utils;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.pathfinding.CellGraph;

public class World {

	public static final int WORLD_SIZE_W = 48;
	public static final int WORLD_SIZE_H = 96;
	public static final int RADIUS = 2;
	
	public Cell[][] grid;
	public List<GameObject> objects = new CopyOnWriteArrayList<>();
	
	public CellGraph cellGraph;
	
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
		objects.add(new Hermit(0, 0));
	}
	
	public void renderTiles(SpriteBatch b) {
		for (int i = grid[0].length - 1; i >= 0; i--) {
			for (int j = grid.length - 1; j >= 0; j--) {
				grid[j][i].render(b);
			}
		}
		if(MouseInput.hoveringOverCell != null) {
			b.draw(Assets.getSprite("tile-highlight"), MouseInput.hoveringOverCell.x, MouseInput.hoveringOverCell.y);
			
			/*
			 * 
			 * TODO: Need to add a good way to select a tile and place it. This will be great when starting to work on menus.
			 * 
			 */
		}

	}
	
	public void renderObjects(SpriteBatch b) {
		for(GameObject object : objects) {
			object.update(Gdx.graphics.getDeltaTime());
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
