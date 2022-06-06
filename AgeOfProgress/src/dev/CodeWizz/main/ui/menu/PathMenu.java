package dev.CodeWizz.main.ui.menu;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.main.ui.Button;

public class PathMenu extends Menu {

	public PathMenu() {
		this.buttons = new Button[0];
	}
	
	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		if(open) {
			r.fillRectUI(10, 10, 100, 100, 0xffff00ff, Light.NONE);
		}
	}
	
	@Override
	public void onOpen(GameContainer gc) {

	}

	@Override
	public void onClose(GameContainer gc) {

	}
}
