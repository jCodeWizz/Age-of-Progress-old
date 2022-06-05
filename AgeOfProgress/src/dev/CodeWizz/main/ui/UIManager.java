package dev.CodeWizz.main.ui;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.main.objects.environment.World;

public class UIManager {

	private MousePreview mousepreview;
	
	public UIManager() {
		mousepreview = new MousePreview();
	}
	
	public void update(GameContainer gc, World world) {
		mousepreview.update(gc, world, this);
	}
	
	public void render(GameContainer gc, Renderer r, World world) {
		mousepreview.render(gc, r, world, this);
	}
	
	public void renderUI(GameContainer gc, Renderer r, World world) {
		
	}
}
