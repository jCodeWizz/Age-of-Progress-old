package dev.codewizz.aop.scenes;

import dev.codewizz.aop.world.World;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.scene.Scene;

public class LevelScene extends Scene {

	private GameObject world;
	
	public LevelScene() {
	}
	
	@Override
	public void update(float dt) {
		if (MouseListener.getScrollY() != 0.0f) {
            Window.getScene().camera().adjustZoom(Camera.zoomConst * MouseListener.getScrollY());
		}
		super.update(dt);
	}

	@Override
	public void init() {
		super.init();

		world = new GameObject("World");
		world.addComponent(new World());
		this.addGameObjectToScene(world);
		
	}
}
