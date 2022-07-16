package dev.codewizz.aop.scene;

import dev.codewizz.aop.component.CameraController;
import dev.codewizz.aop.component.WorldController;
import dev.codewizz.engine.object.GameObject;
import dev.codewizz.engine.object.component.Transform;
import dev.codewizz.engine.scene.Scene;

public class WorldScene extends Scene {

    private GameObject camera;
    private GameObject world;
    
    public WorldScene() {

    }

    @Override
    public void init() {
    	camera = new GameObject("Camera", new Transform(), 0);
    	camera.addComponent(new CameraController());
    	addGameObject(camera);
    	
    	world = new GameObject("World", new Transform(), 0);
    	world.addComponent(new WorldController());
    	addGameObject(world);
    }

    @Override
    public void update(float dt) {
    	super.update(dt);
    }
}