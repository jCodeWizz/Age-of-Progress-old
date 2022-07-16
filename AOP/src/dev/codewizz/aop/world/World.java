package dev.codewizz.aop.world;

import dev.codewizz.aop.components.TileType;
import dev.codewizz.engine.gameobject.Component;

public class World extends Component {
	
	public static final int WIDTH = 32, HEIGHT = 64;
	private int counter = 0;
	
	public Cell[][] grid;
	
	public World() {
		grid = new Cell[WIDTH][HEIGHT];
	}
	
	@Override
	public void start() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 2 == 0) {
					grid[i][j] = new Cell(i, j, (i-(WIDTH/2)) * 64, (j-(HEIGHT/2)) * 16, false, this, TileType.GrassTile);
				} else {
					grid[i][j] = new Cell(i, j, (i-(WIDTH/2)) * 64 + 32, (j-(HEIGHT/2)) * 16, true, this, TileType.DirtTile);
				}
			}
		}
		
		grid[20][20].setTile(TileType.EmptyTile);
	}
	
	@Override
	public void update(float dt) {
		
		if(counter < 120) {
			counter++;
		} else if (counter == 120){
			grid[20][20].setTile(TileType.DirtTile);
			counter = 150;
		}
	}

	@Override
	public void remove() {
		
	}
}
