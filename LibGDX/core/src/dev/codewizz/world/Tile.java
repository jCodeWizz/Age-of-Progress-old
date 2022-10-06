package dev.codewizz.world;

import java.awt.Polygon;

import com.badlogic.gdx.graphics.g2d.Sprite;

import dev.codewizz.utils.Assets;

public abstract class Tile {
	
	protected Cell cell;
	protected Sprite texture;
	protected TileType type;
	
	public Tile(Cell cell) {
		this.cell = cell;
		this.type = TileType.Base;
		this.texture = Assets.getSprite("base-tile");
	}
	
	public Sprite getCurrentSprite() {
		return texture;
	}
	
	public Polygon getHitbox() {
		return new Polygon(cell.getXPoints(), cell.getYPoints(), 4);
	}
}
