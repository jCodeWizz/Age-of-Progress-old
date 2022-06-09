package dev.codewizz.engine.scene;

public abstract class Scene {

	public Scene() {
		
	}
	
	public abstract void init();
	public abstract void update(float dt);
}