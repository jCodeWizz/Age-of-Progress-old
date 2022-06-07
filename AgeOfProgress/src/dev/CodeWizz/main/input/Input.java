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
	public static final int SCROLL_SPEED = 8;
	
	public Input() {

	}

	public void update(GameContainer gc) {
		World world = AgeOfProgress.inst.world;
		UIManager uiManager = AgeOfProgress.inst.uiManager;
		
		boolean used = false;
		
		// 
		
		if(gc.getInput().isButtonDown(1)) {
			for(Button b : uiManager.getButtons()) {
				if(b.getBounds().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY())) {
					b.press(gc);
					
					used = true;
				}
			}
			
			if(!used) {
				for(Menu menu : uiManager.getMenus()) {
					if(menu.isOpen() ) {
						if(menu.getBoundsTop().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY())) {
							draggingMenu = menu;
							draggingOffset = gc.getInput().getMouseX() - gc.camera.getX() - menu.getX();
							draggingMenu.setDragging(true);
						}
					}
				}
			}
		}
		
		if(gc.getInput().getScroll() != 0) {
			for(Menu menu : uiManager.getMenus()) {
				if(menu.isOpen() && menu.getBounds().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY())) {
					menu.scroll(gc.getInput().getScroll());
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
		
		// MOUSE WORLD
		
		if(gc.getInput().isButton(1)) {
			if(draggingMenu != null) {
				draggingMenu.setX(gc.getInput().getMouseX() - gc.camera.getX() - draggingOffset);
				draggingMenu.setY(gc.getInput().getMouseY() - gc.camera.getY() - 10);
			}
			
			if(placing != null && !UIManager.isMouseOnUI(gc)) {
				for(int i = 0; i < World.WORLD_SIZE_W; i++) {
					for(int j = 0; j < World.WORLD_SIZE_H; j++) {
						if(world.grid[i][j].getTile().getHitbox().contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
							world.grid[i][j].setTile(Tile.tileFromType(placing, world.grid[i][j].getTileX(), world.grid[i][j].getTileY(), world.grid[i][j]));
						}
					}
				}
			}
		}
		
		
		// KEYS
		
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
			for(Menu menu  : uiManager.getMenus()) {
				if(menu.isOpen()) {
					menu.close(gc);
				}
			}
		}
	}
}
