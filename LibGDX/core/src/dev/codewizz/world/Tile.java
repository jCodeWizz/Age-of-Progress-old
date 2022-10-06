package dev.codewizz.world;

import com.badlogic.gdx.graphics.g2d.Sprite;

import dev.codewizz.utils.Assets;

public abstract class Tile {
	
	protected Cell cell;
	protected Sprite texture;
	
	public Tile(Cell cell) {
		this.cell = cell;
		this.texture = Assets.getSprite("base-tile");
	}
	
	public Sprite getCurrentSprite() {
		return texture;
	}
}
