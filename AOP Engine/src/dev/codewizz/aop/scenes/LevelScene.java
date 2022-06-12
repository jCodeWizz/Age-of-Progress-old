package dev.codewizz.aop.scenes;

import dev.codewizz.aop.components.CameraComponent;
import dev.codewizz.aop.world.World;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.scene.Scene;

public class LevelScene extends Scene {

	private GameObject world;
	private GameObject cameraObject;
	
	public LevelScene() {
	}
	
	@Override
	public void update(float dt) {
		
		super.update(dt);
	}

	@Override
	public void init() {
		super.init();

		world = new GameObject("World");
		world.addComponent(new World());
		this.addGameObjectToScene(world);
		
		cameraObject = new GameObject("Camera");
		cameraObject.addComponent(new CameraComponent());
		this.addGameObjectToScene(cameraObject);
		
		
		
	}
}
