package dev.CodeWizz.main;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.main.graphics.Camera;
import dev.CodeWizz.main.input.Input;
import dev.CodeWizz.main.objects.environment.World;
import dev.CodeWizz.main.ui.UIManager;

public class AgeOfProgress extends AbstractGame {

	public static AgeOfProgress inst;
	public Camera cam;
	public Input input;
	public World world;
	public UIManager uiManager;
	
	
	public AgeOfProgress() {
		inst = this;
		cam = new Camera();
		input = new Input();
		uiManager = new UIManager();
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		input.update(gc);
		world.tick(gc);
		cam.update(gc);
		uiManager.update(gc, world);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		world.render(gc, r);

		
		
		
		uiManager.render(gc, r, world);
	}

	@Override
	public void renderBackground(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		uiManager.renderUI(gc, r, world);
	}

	@Override
	public void init(GameContainer gc) {
		uiManager.init(gc);
		world = new World();
		world.init(gc);
	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new AgeOfProgress());
		gc.start();
	}
}
