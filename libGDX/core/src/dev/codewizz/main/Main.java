package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.ScreenUtils;

import dev.codewizz.gfx.Renderer;
import dev.codewizz.gfx.gui.layers.GameLayer;
import dev.codewizz.gfx.gui.layers.MainMenuLayer;
import dev.codewizz.input.KeyInput;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.World;

public class Main extends ApplicationAdapter {
	
	public static boolean DEBUG = false;
	public static boolean PLAYING = false;
	public static boolean PAUSED = false;
	
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
		
		mouseInput = new MouseInput();
		keyInput = new KeyInput();
		
		setInputMultiplexer();
	}
	
	public void setInputMultiplexer() {
		
		/*
		 * 
		 * Set all classes that need key/mouse inputs
		 * 
		 */
		
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(renderer.ui);
		inputMultiplexer.addProcessor(keyInput);
		inputMultiplexer.addProcessor(mouseInput);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		Gdx.graphics.setTitle("Age of Progress | HPG | FPS: " + Gdx.graphics.getFramesPerSecond());
		
		
		/*
		 * update game
		 * 
		 */
		
		camera.update(Gdx.graphics.getDeltaTime());
		mouseInput.update(Gdx.graphics.getDeltaTime());
		
		/*
		 * render game
		 * 
		 */
		
		if(PLAYING) {
			renderer.render(world, camera.cam);
		}
		renderer.renderUI();
		
		if(DEBUG && PLAYING) {
			renderer.renderDebug(world, camera.cam);
		}
	}
	
	public void openWorld(World world) {
		
		/*
		 * 
		 * get the new world and set it
		 * 
		 */
		
		
		this.world = world;
		this.world.init();
		PLAYING = true;
		
		renderer.ui = new GameLayer();
		setInputMultiplexer();
	}
	
	public void closeWorld() {
		
		/*
		 * 
		 * set world to null
		 *
		 */
		
		
		PLAYING = false;
		this.world = null;
		
		renderer.ui = new MainMenuLayer();
		setInputMultiplexer();
	}
	
	public static void exit() {
		
		Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		Assets.dispose();
		renderer.dispose();
	}
}
