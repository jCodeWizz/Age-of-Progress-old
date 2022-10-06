package dev.codewizz.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {

	public static final float speed = 550f;
	
	public OrthographicCamera cam;
	public float x, y;
	
	public Camera() {
		cam = new OrthographicCamera(1920, 1080);
	}
	
	public void update(float d) {
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			x -= d * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			x += d * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			y -= d * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			y += d * speed;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		cam.position.set(x, y, 0);
		cam.update();
	}
	
}
