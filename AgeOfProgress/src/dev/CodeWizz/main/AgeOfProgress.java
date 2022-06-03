package dev.CodeWizz.main;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.graphics.Camera;
import dev.CodeWizz.main.input.Input;
import dev.CodeWizz.main.objects.Tree;

public class AgeOfProgress extends AbstractGame {

	public static AgeOfProgress inst;
	public Camera cam;
	public Input input;
	
	public AgeOfProgress() {
		inst = this;
		cam = new Camera();
		input = new Input();
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		input.update(gc);
		cam.update(gc);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(Textures.get("base-tile"), 100, 100);
		r.drawImage(Textures.get("base-tile"), 164, 100);
		r.drawImage(Textures.get("base-tile"), 132, 116);
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
