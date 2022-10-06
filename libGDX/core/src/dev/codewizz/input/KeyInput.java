package dev.codewizz.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import dev.codewizz.main.Main;

public class KeyInput implements InputProcessor {

	@Override
	public boolean keyDown(int key) {
		
		if(key == Input.Keys.ESCAPE) {
			Gdx.app.exit();
			return true;
		}
		if(key == Input.Keys.TAB) {
			Main.DEBUG = true;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int key) {
		if(key == Input.Keys.TAB) {
			Main.DEBUG = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
	/*
	 * 
	 *  MOUSE INPUT NOT IN THIS CLASS
	 * 
	 */

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
