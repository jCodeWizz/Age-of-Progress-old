package dev.codewizz.gfx.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;

public class UIIcon extends UIElement {

	private Sprite button, buttonPressed, icon;
	
	public UIIcon(int x, int y, int w, int h, String buttonName, String buttonPressedName, String iconName) {
		super(x, y, w, h);
		
		this.button = Assets.getSprite(buttonName);
		this.buttonPressed = Assets.getSprite(buttonPressedName);
		this.icon = Assets.getSprite(iconName);
	}

	@Override
	public void render(SpriteBatch b) {
		if(pressed) {
			b.draw(buttonPressed, x, y, w * UILayer.SCALE, h * UILayer.SCALE);
			b.draw(icon, x, y - 2 * UILayer.SCALE, w * UILayer.SCALE, h * UILayer.SCALE);
		} else {
			b.draw(button, x, y, w * UILayer.SCALE, h * UILayer.SCALE);
			b.draw(icon, x, y, w * UILayer.SCALE, h * UILayer.SCALE);
		}
	}
}
