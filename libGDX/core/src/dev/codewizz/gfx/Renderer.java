package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import dev.codewizz.main.Main;

public class Renderer {

	private static ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public SpriteBatch tileBatch;
	public SpriteBatch objectBatch;
	
	public Renderer() {
		tileBatch = new SpriteBatch();
		objectBatch = new SpriteBatch();
	}
	
	public void render() {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		
		tileBatch.begin();
		Main.inst.world.render(tileBatch);
		tileBatch.setProjectionMatrix(Main.inst.camera.cam.combined);
		tileBatch.end();
		
		objectBatch.begin();
		
		objectBatch.setProjectionMatrix(Main.inst.camera.cam.combined);
		objectBatch.end();
	}
	
	public void dispose() {
		tileBatch.dispose();
		objectBatch.dispose();
	}
	
	public static void drawDebugLine(Vector2 start, Vector2 end) {
		Gdx.gl.glLineWidth(2);
        debugRenderer.setProjectionMatrix(Main.inst.camera.cam.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.WHITE);
        debugRenderer.line(start, end);
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
	}
}
