package dev.codewizz.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.world.tiles.BaseTile;

public class Cell {
	
	public Tile tile;
	public float x, y;
	public int indexX, indexY;
	
	public Cell(float x, float y, int indexX, int indexY) {
		this.x = x;
		this.y = y;
		this.indexX = indexX;
		this.indexY = indexY;
		
		this.tile = new BaseTile(this);
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
		this.tile.cell = this;
	}
	
	public void render(SpriteBatch b) {
		b.draw(tile.getCurrentSprite(), x, y);
	}
	
}
