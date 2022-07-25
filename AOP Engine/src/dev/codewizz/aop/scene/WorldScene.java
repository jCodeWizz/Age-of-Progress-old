package dev.codewizz.aop.scene;

import org.joml.Vector2f;

import dev.codewizz.aop.component.CameraController;
import dev.codewizz.aop.component.InputController;
import dev.codewizz.aop.component.WorldController;
import dev.codewizz.aop.component.entities.EntityController;
import dev.codewizz.engine.object.GameObject;
import dev.codewizz.engine.object.component.SpriteRenderer;
import dev.codewizz.engine.object.component.Transform;
import dev.codewizz.engine.renderer.Sprite;
import dev.codewizz.engine.scene.Scene;
import dev.codewizz.engine.util.AssetPool;

public class WorldScene extends Scene {

    private GameObject camera;
    private GameObject world;
    private GameObject input;
    
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

    	input = new GameObject("Input", new Transform(new Vector2f(0, 0), new Vector2f(64, 48)), 0);
    	input.addComponent(new InputController());
    	input.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/textures/ui/tiles/tile-highlight.png"))));
    	addGameObject(input);
    	
    	addGameObject(new GameObject("Entity", new Transform(new Vector2f(0, 0), new Vector2f(20, 58)), 0).addComponent(new EntityController()).addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/textures/entities/dude.png")))));
    	
    }
    
    @Override
    public void update(float dt) {
    	System.out.println("BATCHES: " + renderer.batches.size());
    	super.update(dt);
    }
}