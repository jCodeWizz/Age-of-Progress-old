package dev.codewizz.engine.scene;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joml.Vector2f;

import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.renderer.Renderer;
import dev.codewizz.engine.util.AssetPool;

public abstract class Scene {

	protected Renderer renderer;
	protected Camera camera;
	private boolean isRunning = false;
	protected List<GameObject> objects = new CopyOnWriteArrayList<>();
	
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
	
	public void init() {
		this.camera = new Camera(new Vector2f());
		loadResources();
	}
	
	public void update(float dt) {
		///System.out.println("FPS: " + (1.0f / dt));
		
		for(GameObject object : objects) {
			object.update(dt);
		}
		
		this.renderer.render();
	}
	
	private void loadResources() {
		AssetPool.getShader(".//res/assets/shaders/default.glsl");
	}
}
