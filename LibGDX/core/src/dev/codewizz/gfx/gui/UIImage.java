package dev.codewizz.gfx.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;

public class UIImage extends UIElement {
	
	public Sprite sprite;
	
	public UIImage(int x, int y, int w, int h, String spriteName) {
		super(x, y, w, h);

		sprite = Assets.getSprite(spriteName);
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(sprite, x, y, w * UILayer.SCALE, h * UILayer.SCALE);
	}
}
