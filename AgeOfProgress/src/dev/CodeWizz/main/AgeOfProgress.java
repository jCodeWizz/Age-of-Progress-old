package dev.CodeWizz.main;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.main.graphics.Camera;
import dev.CodeWizz.main.input.Input;
import dev.CodeWizz.main.objects.Tree;
import dev.CodeWizz.main.objects.envirement.World;

public class AgeOfProgress extends AbstractGame {

	public static AgeOfProgress inst;
	public Camera cam;
	public Input input;
	public World world;
	
	public AgeOfProgress() {
		inst = this;
		cam = new Camera();
		input = new Input();
		world = new World();
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		input.update(gc);
		cam.update(gc);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		world.render(gc, r);
	}

	@Override
	public void renderBackground(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void init(GameContainer gc) {
		gc.handler.addObject(new Tree(0, 0));
	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new AgeOfProgress());
		gc.start();
	}
}
