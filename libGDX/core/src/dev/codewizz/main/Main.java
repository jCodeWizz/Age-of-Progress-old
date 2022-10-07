package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import dev.codewizz.gfx.Renderer;
import dev.codewizz.input.KeyInput;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.World;

public class Main extends ApplicationAdapter {
	
	public static boolean DEBUG = false;
	
	public static Main inst;

	public Renderer renderer;
	public Camera camera;
	public InputMultiplexer inputMultiplexer;

	public World world;
	public MouseInput mouseInput;
	public KeyInput keyInput;
	
	@Override
	public void create () {
		inst = this;
		Assets.create();
		
		renderer = new Renderer();
		camera = new Camera();
		
		world = new World();
		mouseInput = new MouseInput();
		keyInput = new KeyInput();
		
		
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(renderer.ui);
		inputMultiplexer.addProcessor(keyInput);
		inputMultiplexer.addProcessor(mouseInput);
		Gdx.input.setInputProcessor(inputMultiplexer);
		
	}

	@Override
	public void render () {
		
		Gdx.graphics.setTitle("Age of Progress | HPG | FPS: " + Gdx.graphics.getFramesPerSecond());
		
		
		/*
		 * update game
		 */
		
		camera.update(Gdx.graphics.getDeltaTime());
		mouseInput.update(Gdx.graphics.getDeltaTime());
		
		/*
		 * render game
		 */
		
		renderer.render(world, camera.cam);
		if(DEBUG) {
			renderer.renderDebug(world, camera.cam);
		}
	}
	
	@Override
	public void dispose () {
		Assets.dispose();
		renderer.dispose();
	}
}
