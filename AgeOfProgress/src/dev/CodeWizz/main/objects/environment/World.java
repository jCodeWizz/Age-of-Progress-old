package dev.CodeWizz.main.objects.environment;

import java.awt.event.KeyEvent;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.main.objects.environment.tiles.GrassTile;

public class World {

	public static int WORLD_SIZE_W = 48;
	public static int WORLD_SIZE_H = 96;

	public Cell[][] grid;

	public World() {
		grid = new Cell[WORLD_SIZE_W][WORLD_SIZE_H];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 2 == 0) {
					grid[i][j] = new Cell(i, j, (i-(WORLD_SIZE_W/2)) * 64, (j-(WORLD_SIZE_H/2)) * 16, false, this);
				} else {
					grid[i][j] = new Cell(i, j, (i-(WORLD_SIZE_W/2)) * 64 + 32, (j-(WORLD_SIZE_H/2)) * 16, true, this);
				}
			}
		}
	}
	
	public void tick(GameContainer gc) {
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[j][i].getTile().tick(gc);
			}
		}
	}
	
	public void init(GameContainer gc) {
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[j][i].setTile(new GrassTile(grid[j][i].getTileX(), grid[j][i].getTileY(), grid[j][i]));
			}
		}
	}

	public void render(GameContainer gc, Renderer r) {
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if(gc.getScreenBounds().intersects(grid[j][i].getBounds())) {
					grid[j][i].getTile().render(gc, r);
				}
			}
		}
		
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if(gc.getScreenBounds().intersects(grid[j][i].getBounds())) {
					grid[j][i].getTile().render2(gc, r);
					if(gc.getInput().isKey(KeyEvent.VK_ALT)) {
						r.drawPolygon(0xffffffff, grid[j][i].getTile().getHitbox());
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		String text = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				text += grid[i][j];
				text += ", ";
				if (j == grid.length - 1)
					text += "\n";
			}
		}
		return text;
	}
}
