package dev.codewizz.engine.scene;

import java.util.ArrayList;
import java.util.List;

import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.renderer.Renderer;

public abstract class Scene {

	protected Renderer renderer;
	protected Camera camera;
	private boolean isRunning = false;
	protected List<GameObject> objects = new ArrayList<>();
	
	public Scene() {
		renderer = new Renderer();
	}
	
	public void start() {
		for(GameObject object : objects) {
			object.start();
			this.renderer.add(object);
		}
	}
	
	public void addGameObjectToScene(GameObject object) {
		if(!isRunning) {
			objects.add(object);
		} else {
			objects.add(object);
			object.start();
			this.renderer.add(object);
		}
	}
	
	public Camera camera() {
		return this.camera;
	}
	
	public abstract void init();
	public abstract void update(float dt);
}
