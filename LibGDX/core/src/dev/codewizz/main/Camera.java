package dev.codewizz.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera implements InputProcessor {

	public static final float speed = 550f;
	
	public OrthographicCamera cam;
	public float x, y, zoom;
	
	public Camera() {
		cam = new OrthographicCamera(1920, 1080);
		zoom = cam.zoom;
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
		
		cam.zoom = zoom;
		
		cam.position.set(x, y, 0);
		cam.update();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		zoom += amountY;
		return true;
	}
	
}
