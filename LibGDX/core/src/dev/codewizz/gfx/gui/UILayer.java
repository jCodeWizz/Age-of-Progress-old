package dev.codewizz.gfx.gui;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.input.MouseInput;

public class UILayer implements InputProcessor {

	public static int SCALE = 3, WIDTH = 1920, HEIGHT = 1080;
	
	private UIElement current;
	public List<UIElement> elements = new CopyOnWriteArrayList<>();
	
	public UILayer() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		// MANAGE ICON
		elements.add(new UIIcon((WIDTH/2)-(134*SCALE)/2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "manage-icon"));
		
		// PATH ICON
		elements.add(new UIIcon((WIDTH/2)-(78*SCALE)/2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "path-icon"));
		
		// BUILD ICON
		elements.add(new UIIcon((WIDTH/2)-(22*SCALE)/2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "build-icon"));
		
		// PEOPLE ICON
		elements.add(new UIIcon((WIDTH/2)-(-34*SCALE)/2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "people-icon"));
		
		// TOOL ICON
		elements.add(new UIIcon((WIDTH/2)-(-90*SCALE)/2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "tool-icon"));
		
		// BACKGROUND
		
		elements.add(new UIImage((WIDTH/2)-(146*SCALE)/2, 0, 146, 30, "icon-board"));
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(UIElement e : elements) {
			if(e.getBounds().contains(screenX, screenY) && e.isAvailable() && e.isEnabled()) {
				current = e;
				e.click();
				return true;
			}
		}
		return false;
	}
	
	

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		MouseInput.dragging[button] = false;

		for(UIElement e : elements) {
			if(e.getBounds().contains(screenX, screenY) && e.equals(current) && e.isAvailable() && e.isEnabled()) {
				current.deClick();
				return true;
			}
			e.pressed = false;
		}
		
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
	
	public void render(SpriteBatch b) {
		for(int i = elements.size() - 1; i >= 0; i--) {
			if(elements.get(i).isEnabled()) {
				elements.get(i).render(b);
			}
		}
	}

	/*
	 * 
	 * 
	 * 
	 *    USELESS METHODS
	 * 
	 * 
	 * 
	 */
	
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
}
