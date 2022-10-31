package dev.codewizz.gfx.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;

public class UIImage extends UIElement {
	
	public Sprite sprite;
	public int scale = 1;
	
	public UIImage(String id, int x, int y, int w, int h, String spriteName) {
		super(id, x, y, w, h);

		this.scale = UILayer.SCALE;
		this.wantsClick = false;
		sprite = Assets.getSprite(spriteName);
	}
	
	public UIImage(String id, int x, int y, int w, int h, String spriteName, int scale) {
		super(id, x, y, w, h);

		this.scale = scale;
		this.wantsClick = false;
		
		sprite = Assets.getSprite(spriteName);
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(sprite, x, y, w * scale, h * scale);
	}
}
