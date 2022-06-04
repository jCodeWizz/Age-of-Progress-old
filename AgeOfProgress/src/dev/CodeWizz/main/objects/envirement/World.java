package dev.CodeWizz.main.objects.envirement;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;

public class World {

	public static int WORLD_SIZE_W = 8;
	public static int WORLD_SIZE_H = 16;
	
	public Tile[][] grid;
	
	public World() {
		grid = new Tile[WORLD_SIZE_W][WORLD_SIZE_H];
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(j % 2 == 0) {
					grid[i][j] = new Tile(i*64, j*16, TileType.Base);
				} else {
					grid[i][j] = new Tile(i*64 + 32, j*16, TileType.Base);
				}
			}
		}
	}
	
	public void render(GameContainer gc, Renderer r) {
		for(int i = 0; i < grid[0].length; i++) {
			for(int j = 0; j < grid.length; j++) {
				grid[j][i].render(gc, r);
			}
		}
		for(int i = 0; i < grid[0].length; i++) {
			for(int j = 0; j < grid.length; j++) {
				grid[j][i].render2(gc, r);
			}
		}
		
		r.fillRect(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 1, 1, 0xffffff00, Light.NONE);
	}
}
