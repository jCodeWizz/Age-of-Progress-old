package dev.codewizz.gfx.gui;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

import dev.codewizz.input.MouseInput;
import dev.codewizz.main.Main;

public class UILayer implements InputProcessor {

	public static int SCALE = 3, WIDTH = 1920, HEIGHT = 1080;
	
	private UIElement current;
	public List<UIElement> elements = new CopyOnWriteArrayList<>();
	
	public UILayer() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		// PATH MENU
		elements.add(new UIMenu("menu", 0, 0, 128, 260, this).disable());
		
		// MANAGE ICON
		elements.add(new UIIcon("manage-icon", (WIDTH/2)-(134*SCALE)/2, 6 * SCALE, 22, 24, "manage-icon"));
		
		// PATH ICON
		elements.add(new UIIcon("path-icon", (WIDTH/2)-(78*SCALE)/2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "path-icon") {
			@Override
			protected void onDeClick() {
				UIElement e = getElement("menu");
				if(e.isEnabled())
					e.disable();
				else
					e.enable();
			}
		});
		
		// BUILD ICON
		elements.add(new UIIcon("build-icon", (WIDTH/2)-(22*SCALE)/2, 6 * SCALE, 22, 24, "build-icon"));
		
		// PEOPLE ICON
		elements.add(new UIIcon("people-icon", (WIDTH/2)-(-34*SCALE)/2, 6 * SCALE, 22, 24, "people-icon"));
		
		// TOOL ICON
		elements.add(new UIIcon("tool-icon", (WIDTH/2)-(-90*SCALE)/2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "tool-icon"));
		
		// BACKGROUND
		elements.add(new UIImage("icon-background", (WIDTH/2)-(146*SCALE)/2, 0, 146, 30, "icon-board"));
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(UIElement e : elements) {
			if(e.getBounds().contains(screenX, screenY) && e.isAvailable() && e.isEnabled() && e.wantsClick) {
				current = e;
				e.click();
				return true;
			}
		}
		return false;
	}
	
	public UIElement getElement(String id) {
		for(UIElement e : elements) {
			if(e.id.equalsIgnoreCase(id)) {
				return e;
			}
		}
		return null;
	}
	
	

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		MouseInput.dragging[button] = false;

		for(UIElement e : elements) {
			if(e.getBounds().contains(screenX, screenY) && e.equals(current) && e.isAvailable() && e.isEnabled() && !(e instanceof UIImage)) {
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
			UIElement e = elements.get(i);
			if(e.isEnabled()) { // CHECK IF UI COMPONENT SHOULD BE RENDERED AT ALL
				if(e.id.substring(0, 4).equals("slot")) { // CHECK IF UI COMPONENT IS A MOVEABLE SLOT
					b.flush();
					Rectangle scissors = ((UIMenu) getElement("menu")).getSlotArea(); // MAKE liBGDX RECTANGLE FROM SLOT VAlUES
					if (ScissorStack.pushScissors(scissors)) {
					    e.render(b);
					    b.flush();
						ScissorStack.popScissors();
					}
				} else {
					e.render(b);
				}
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
