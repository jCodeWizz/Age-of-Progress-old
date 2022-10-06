package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.World;

public class Main extends ApplicationAdapter {
	
	private World world;
	private Camera camera;
	private SpriteBatch tileBatch;
	
	@Override
	public void create () {
		Assets.create();
		
		camera = new Camera();
		world = new World();
		
		tileBatch = new SpriteBatch();
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(camera);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render () {
		
		Gdx.graphics.setTitle("Age of Progress | HPG | FPS: "+Gdx.graphics.getFramesPerSecond());
		
		
		/*
		 * update game
		 */
		
		camera.update(Gdx.graphics.getDeltaTime());
		
		
		/*
		 * render game
		 */
		
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		tileBatch.begin();
		
		world.render(tileBatch);
		
		tileBatch.setProjectionMatrix(camera.cam.combined);
		tileBatch.end();
	}
	
	
	
	@Override
	public void dispose () {
		Assets.dispose();
		tileBatch.dispose();
	}
}
