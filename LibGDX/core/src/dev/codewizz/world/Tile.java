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
	
	public void onPlace() {};
	public void onDestroy() {};
	public void update() {};
	
	public void place() {
		Cell[] cells = this.cell.getNeighbours();
		for(int i = 0; i < cells.length; i++) {
			cells[i].tile.update();
		}
		onPlace();
	}
	
	public Sprite getCurrentSprite() {
		return texture;
	}
	
	public Polygon getHitbox() {
		return new Polygon(cell.getXPoints(), cell.getYPoints(), 4);
	}
	
	public boolean[] checkNeighbours() {
		boolean[] data = new boolean[] { false, false, false, false };
		Cell[] neighbours = this.cell.getNeighbours();

		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i] == null) {
				data[i] = false;
			} else {
				data[i] = neighbours[i].getTile().getType() == this.type;
			}
		}
		return data;
	}
	
	public boolean[] checkNeighbours(TileType type) {
		boolean[] data = new boolean[] { false, false, false, false };
		Cell[] neighbours = this.cell.getNeighbours();

		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i] == null) {
				data[i] = false;
			} else {
				data[i] = neighbours[i].getTile().getType() == type;
			}
		}
		return data;
	}
	
	public TileType getType() {
		return type;
	}
}
