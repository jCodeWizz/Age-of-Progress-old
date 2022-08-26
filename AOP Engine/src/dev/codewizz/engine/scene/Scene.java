package dev.codewizz.engine.scene;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joml.Vector2f;

import dev.codewizz.engine.object.GameObject;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.renderer.Renderer;

public abstract class Scene {

    protected Renderer renderer = new Renderer();
    protected Camera camera;
    public boolean isRunning = false;
    protected List<GameObject> gameObjects = new CopyOnWriteArrayList<>();

    public Scene() {
    	camera = new Camera(new Vector2f(0f, 0f));
    }

    public void init() {
    	
    }

    public void start() {
        isRunning = true;
        for (GameObject go : gameObjects) {
            go.start();
            this.renderer.add(go);
        }
    }

    public void addGameObject(GameObject go) {
        if (!isRunning) {
            gameObjects.add(go);
        } else {
        	gameObjects.add(go);
        	go.start();
        	this.renderer.add(go);
        }
    }
    
    public void removeGameObject(GameObject go) {
    	gameObjects.remove(go);
    	this.renderer.destroyGameObject(go);
    }
    
    public GameObject getGameObject(String name) {
    	for(GameObject object : gameObjects) {
    		if(object.name.equals(name)) {
    			return object;
    		}
    	}
    	
    	return null;
    }

    public void update(float dt) {
    	for(GameObject object : gameObjects) {
    		object.update(dt);
    	}
    	
    	renderer.render();
    }
    
    public void renderGameObjectUI() {
    	for(GameObject b : gameObjects) {
    		b.renderUI();
    	}
    	
    	renderUI();
    }

    public void renderUI() {
    	
    }

    public Camera getCamera() {
        return this.camera;
    }
}