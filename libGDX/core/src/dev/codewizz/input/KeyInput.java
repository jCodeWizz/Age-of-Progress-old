package dev.codewizz.input;

import java.nio.ByteBuffer;
import java.util.zip.Deflater;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.TileType;
import dev.codewizz.world.tiles.EmptyTile;

public class KeyInput implements InputProcessor {

	/*
	 * 
	 * Key Input handling class
	 * 
	 */
	
	
	@Override
	public boolean keyDown(int key) {
		
		if(key == Input.Keys.ESCAPE) {
			// if shift is also pressed just force-close the program
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				Main.exit();
			}
			
			// closes all menus before going to main menu
			if(Main.PLAYING) {
				if(!Main.inst.renderer.ui.closeMenus()) {
					Main.inst.renderer.ui.getElement("pauseMenu").enable();
				}
			}
			
			
			return true;
		}
		if(key == Input.Keys.TAB) {
			// enter debug mode
			Main.DEBUG = true;
			return true;
		}
		
		
		// create a screenshot
		if(key == Input.Keys.C) {
			Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
			ByteBuffer pixels = pixmap.getPixels();

			// This loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
			int size = Gdx.graphics.getBackBufferWidth() * Gdx.graphics.getBackBufferHeight() * 4;
			for (int i = 3; i < size; i += 4) {
				pixels.put(i, (byte) 255);
			}

			PixmapIO.writePNG(Gdx.files.external("\\Desktop\\Java Coding\\Eclipse\\Age-of-Progress\\LibGDX\\screenshot.png"), pixmap, Deflater.DEFAULT_COMPRESSION, true);
			pixmap.dispose();



			return true;
		}
		
		if(key == Input.Keys.SPACE) {
			Cell cell = Main.inst.world.findCell(MouseInput.coords.x, MouseInput.coords.y, 5, false, TileType.Base);
			
			if(cell != null) {
				cell.setTile(new EmptyTile(cell));
			}
			
			
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int key) {
		
		// exit debug mode
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
