package dev.CodeWizz.main.objects.environment;

import java.awt.event.KeyEvent;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class World {

	public static int WORLD_SIZE_W = 24;
	public static int WORLD_SIZE_H = 48;

	public Cell[][] grid;

	public World() {
		grid = new Cell[WORLD_SIZE_W][WORLD_SIZE_H];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 2 == 0) {
					grid[i][j] = new Cell(i, j, i * 64, j * 16, false, this);
				} else {
					grid[i][j] = new Cell(i, j, i * 64 + 32, j * 16, true, this);
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
		for(int i = 2; i < 5; i++) {
			grid[i][0].setGrass();
		}
		
		for(int i = 2; i < 5; i++) {
			grid[i][2].setGrass();
		}
		
		for(int i = 2; i < 5; i++) {
			grid[i][3].setGrass();
		}
	}

	public void render(GameContainer gc, Renderer r) {
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[j][i].getTile().render(gc, r);
			}
		}
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[j][i].getTile().render2(gc, r);
				if(gc.getInput().isKey(KeyEvent.VK_ALT)) {
					r.drawPolygon(0xffffffff, grid[j][i].getTile().getHitbox());
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
