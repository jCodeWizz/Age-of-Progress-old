package dev.CodeWizz.main.ui;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.objects.environment.Cell;
import dev.CodeWizz.main.objects.environment.CellState;
import dev.CodeWizz.main.objects.environment.TileType;
import dev.CodeWizz.main.objects.environment.World;

public class MousePreview {

	public Cell selected = null;
	public boolean blocked = false;
	
	public void update(GameContainer gc, World world, UIManager manager) {
		
		blocked = false;
		boolean found = false;
		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				if (world.grid[i][j].getTile().getHitbox().contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
					selected = world.grid[i][j];
					found = true;
					if(world.grid[i][j].getState() == CellState.Empty || world.grid[i][j].getTile().getType() == TileType.Stone)
						blocked = true;
				}
			}
		}
		if(!found) {
			selected = null;
		}
	}
	
	public void render(GameContainer gc, Renderer r, World world, UIManager manager) {
		if(selected != null && !UIManager.mouseOnUI) {
			if(blocked) {
				r.drawImage(Textures.get("tile-highlight2"), selected.getTileX() - (64 / 2), selected.getTileY() - (48 / 2));
			} else {
				r.drawImage(Textures.get("tile-highlight1"), selected.getTileX() - (64 / 2), selected.getTileY() - (48 / 2));
			}
		}
	}
}
