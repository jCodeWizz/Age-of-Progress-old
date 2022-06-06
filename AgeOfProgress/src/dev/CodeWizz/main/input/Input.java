package dev.CodeWizz.main.input;

import java.awt.event.KeyEvent;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.main.AgeOfProgress;
import dev.CodeWizz.main.ui.Button;
import dev.CodeWizz.main.ui.UIManager;
import dev.CodeWizz.main.ui.menu.Menu;

public class Input {

	public Input() {

	}

	public void update(GameContainer gc) {
		//World world = AgeOfProgress.inst.world;
		UIManager uiManager = AgeOfProgress.inst.uiManager;
		
		if(gc.getInput().isButtonDown(1)) {
			for(Button b : uiManager.getButtons()) {
				if(b.getBounds().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY())) {
					b.press(gc);
				}
			}
		}
		
		if(gc.getInput().isButtonUp(1)) {
			for(Button b : uiManager.getButtons()) {
				b.unpress(gc, b.getBounds().contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY()));
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
