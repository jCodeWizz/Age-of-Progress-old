package dev.codewizz.aop.scenes;

import org.joml.Vector2f;
import org.joml.Vector4f;

import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.scene.Scene;

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
		
		int xOffset = 10;
		int yOffset = 10;
		
		float totalWidth = (float)(600 - xOffset * 2);
		float totalHeight = (float)(300 - yOffset * 2);
		float sizeX = totalWidth / 100.0f;
		float sizeY = totalHeight / 100.0f;
		
		for(int x = 0; x < 100; x++) {
			for(int y = 0; y < 100; y++) {
				float xPos = xOffset + (x * sizeX);
				float yPos = yOffset + (y * sizeY);
				
				GameObject object = new GameObject("Obj" + x + " " + y, new Transform(new Vector2f(xPos, yPos), new Vector2f(sizeX, sizeY)));
				object.addComponent(new SpriteRenderer(new Vector4f(xPos / totalWidth, yPos / totalHeight, 1, 1)));
				this.addGameObjectToScene(object);
			}
		}
	}
}
