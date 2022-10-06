package dev.codewizz.gfx.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;

public class UIButton extends UIElement {

	private Sprite button, buttonPressed;
	
	public UIButton(int x, int y, int w, int h, String buttonName, String buttonPressedName) {
		super(x, y, w, h);
		
		this.button = Assets.getSprite(buttonName);
		this.buttonPressed = Assets.getSprite(buttonPressedName);
	}

	@Override
	public void render(SpriteBatch b) {
		if(pressed) {
			b.draw(buttonPressed, x, y, w * UILayer.SCALE, h * UILayer.SCALE);
		} else {
			b.draw(button, x, y, w * UILayer.SCALE, h * UILayer.SCALE);
		}
	}
}
