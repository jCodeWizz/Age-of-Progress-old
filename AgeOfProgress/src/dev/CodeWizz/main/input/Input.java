package dev.CodeWizz.main.input;

import java.awt.event.KeyEvent;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.main.AgeOfProgress;
import dev.CodeWizz.main.objects.environment.Tile;
import dev.CodeWizz.main.objects.environment.TileType;
import dev.CodeWizz.main.objects.environment.World;
import dev.CodeWizz.main.ui.Button;
import dev.CodeWizz.main.ui.UIManager;
import dev.CodeWizz.main.ui.menu.Menu;

public class Input {

	private Menu draggingMenu = null;
	private int draggingOffset = 0;
	
	public static TileType placing;
	
	public Input() {

	}

	public void update(GameContainer gc) {
		World world = AgeOfProgress.inst.world;
		UIManager uiManager = AgeOfProgress.inst.uiManager;
		
		if(gc.getInput().isButtonDown(1)) {
			for(Button b : uiManager.getButtons()) {
				if(b.getBounds().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY())) {
					b.press(gc);
				}
			}
			
			for(Menu menu : uiManager.getMenus()) {
				if(menu.isOpen()) {
					if(menu.getBoundsTop().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY())) {
						draggingMenu = menu;
						draggingOffset = gc.getInput().getMouseX() - gc.camera.getX() - menu.getX();
						draggingMenu.setDragging(true);
					}
				}
			}
		}
		
		if(gc.getInput().isButton(1)) {
			if(draggingMenu != null) {
				draggingMenu.setX(gc.getInput().getMouseX() - gc.camera.getX() - draggingOffset);
				draggingMenu.setY(gc.getInput().getMouseY() - gc.camera.getY() - 10);
			}
			
			if(placing != null) {
				for(int i = 0; i < World.WORLD_SIZE_W; i++) {
					for(int j = 0; j < World.WORLD_SIZE_H; j++) {
						if(world.grid[i][j].getTile().getHitbox().contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
							world.grid[i][j].setTile(Tile.tileFromType(placing, world.grid[i][j].getTileX(), world.grid[i][j].getTileY(), world.grid[i][j]));
						}
					}
				}
			}
		}
		
		
		
		if(gc.getInput().isButtonUp(1)) {
			for(Button b : uiManager.getButtons()) {
				b.unpress(gc, b.getBounds().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY()));
			}
			
			if(draggingMenu != null) {
				draggingMenu.setDragging(false);
				draggingMenu = null;
			}
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
			for(Menu menu  : uiManager.getMenus()) {
				if(menu.isOpen()) {
					menu.close(gc);
				}
			}
		}
	}
}
