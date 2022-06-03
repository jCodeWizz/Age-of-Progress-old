package dev.CodeWizz.main;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.main.graphics.Camera;

public class AgeOfProgress extends AbstractGame {

	public static AgeOfProgress inst;
	public Camera cam;
	
	public AgeOfProgress() {
		inst = this;
		cam = new Camera();
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void renderBackground(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void init(GameContainer gc) {
		
	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new AgeOfProgress());
		gc.start();
	}
}
