package dev.codewizz.aop.world;

import dev.codewizz.aop.component.Tile;
import dev.codewizz.engine.object.GameObject;

public class Cell {

	public int gridX, gridY;
	public float tileX, tileY;
	public boolean odd;
	
	public GameObject tile;
	
	public Cell(int gridX, int gridY, float tileX, float tileY, boolean odd) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.tileX = tileX;
		this.tileY = tileY;
	}
	
	public void setTile(Tile t) {
		tile.removeComponent(Tile.class);
		tile.addComponent(t);
		tile.getComponent(Tile.class).updateSprite();
	}
	
}
