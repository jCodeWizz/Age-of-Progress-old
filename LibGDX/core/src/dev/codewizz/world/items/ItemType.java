package dev.codewizz.world.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

import dev.codewizz.utils.Assets;

public enum ItemType {

	Carrot("item-carrot"),
	Clay("item-clay"),
	Mushrooms("mushrooms"),
	Stone("item-stone"),
	Wood("item-wood");
	
	private Sprite s;
	ItemType(String sprite) {
		s = Assets.getSprite(sprite);
	}
	public Sprite getSprite() {
		return s;
	}
}
