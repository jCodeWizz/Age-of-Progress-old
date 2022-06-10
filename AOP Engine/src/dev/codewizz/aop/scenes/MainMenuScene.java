package dev.codewizz.aop.scenes;

import org.joml.Vector2f;

import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.scene.Scene;
import dev.codewizz.engine.util.AssetPool;

public class MainMenuScene extends Scene {

	public MainMenuScene() {
	}
	
	@Override
	public void update(float dt) {
		System.out.println("FPS: " + (1.0f / dt));
		
		for(GameObject object : objects) {
			object.update(dt);
		}
		
		this.renderer.render();
	}

	@Override
	public void init() {
		this.camera = new Camera(new Vector2f());
		
		GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(64, 48)));
		obj1.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/base-tile.png"))));
		this.addGameObjectToScene(obj1);
		GameObject obj2 = new GameObject("Object 1", new Transform(new Vector2f(100, 400), new Vector2f(64, 48)));
		obj2.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/base-tile.png"))));
		this.addGameObjectToScene(obj2);
		
		loadResources();
	}
	
	private void loadResources() {
		AssetPool.getShader(".//res/assets/shaders/default.glsl");
	}
}
