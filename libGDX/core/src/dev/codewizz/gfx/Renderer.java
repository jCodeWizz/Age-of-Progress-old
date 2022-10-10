package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.ScreenUtils;

import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.main.Main;
import dev.codewizz.world.World;

public class Renderer {

	private static ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public SpriteBatch tileBatch;
	public SpriteBatch objectBatch;
	public SpriteBatch uiBatch;
	
	public UILayer ui;
	
	public Renderer() {
		tileBatch = new SpriteBatch();
		objectBatch = new SpriteBatch();
		uiBatch = new SpriteBatch();
		
		ui = new UILayer();
	}
	
	public void render(World world, OrthographicCamera cam) {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		
		tileBatch.begin();
		world.render(tileBatch);
		tileBatch.setProjectionMatrix(cam.combined);
		tileBatch.end();
		/*
		 * 
		 * TILE PART DONE CONTINUE TO OBJECT
		 * 
		 */
		objectBatch.begin();
		
		world.renderTileObjects(objectBatch);
		ScrollPane pane;
		objectBatch.setProjectionMatrix(cam.combined);
		objectBatch.end();
		/*
		 * 
		 * OBJECT PART DONE CONTINUE TO UI
		 * 
		 */
		uiBatch.begin();
		ui.render(uiBatch);
		uiBatch.end();
	}
	
	public void renderDebug(World world, OrthographicCamera cam) {
		debugRenderer.setProjectionMatrix(Main.inst.camera.cam.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.WHITE);
        
        world.renderDebug();
        
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
		
	}
	
	public void dispose() {
		tileBatch.dispose();
		objectBatch.dispose();
	}
	
	public static void drawDebugLine(Vector2 start, Vector2 end) {
        debugRenderer.line(start, end);
	}
}
