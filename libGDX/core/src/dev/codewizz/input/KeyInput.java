package dev.codewizz.input;

import java.util.zip.Deflater;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import dev.codewizz.main.Main;

public class KeyInput implements InputProcessor {

	@Override
	public boolean keyDown(int key) {
		
		if(key == Input.Keys.ESCAPE) {
			
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				Main.exit();
			}
			
			
			
			if(Main.PLAYING) {
				if(!Main.inst.renderer.ui.closeMenus()) {
					Main.inst.renderer.ui.getElement("pauseMenu").enable();
				}
			}
			
			
			return true;
		}
		if(key == Input.Keys.TAB) {
			Main.DEBUG = true;
			return true;
		}
		
		if(key == Input.Keys.C) {
			Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			PixmapIO.writePNG(Gdx.files.external("\\Desktop\\Java Coding\\Eclipse\\Age-of-Progress\\LibGDX\\pixmap.png"), pixmap, Deflater.DEFAULT_COMPRESSION, true);
			pixmap.dispose();


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
