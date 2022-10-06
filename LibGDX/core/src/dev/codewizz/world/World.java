package dev.codewizz.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Utils;

public class World {

	public static final int WORLD_SIZE_W = 48;
	public static final int WORLD_SIZE_H = 96;
	public static final int RADIUS = 2;
	
	public Cell[][] grid;
	
	public World() {
		grid = new Cell[WORLD_SIZE_W][WORLD_SIZE_H];
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 2 == 0) {
					grid[i][j] = new Cell((i-(WORLD_SIZE_W/2)) * 64, (j-(WORLD_SIZE_H/2)) * 16, i, j, false, this);
				} else {
					grid[i][j] = new Cell((i-(WORLD_SIZE_W/2)) * 64 + 32, (j-(WORLD_SIZE_H/2)) * 16, i, j, true, this);
				}
			}
		}
	}
	
	public void render(SpriteBatch b) {
		for (int i = grid[0].length - 1; i > 0; i--) {
			for (int j = grid.length - 1; j > 0; j--) {
				grid[j][i].render(b);
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
