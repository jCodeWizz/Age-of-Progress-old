package dev.codewizz.world;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Direction;
import dev.codewizz.world.tiles.BaseTile;

public class Cell {

	public Tile tile;
	public float x, y;
	public int indexX, indexY;
	public boolean odd = false;
	public World world;

	public Cell(float x, float y, int indexX, int indexY, boolean odd, World world) {
		this.x = x;
		this.y = y;
		this.indexX = indexX;
		this.indexY = indexY;
		this.tile = new BaseTile(this);

		this.odd = odd;
		this.world = world;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
		this.tile.cell = this;
	}

	public void render(SpriteBatch b) {
		b.draw(tile.getCurrentSprite(), x, y);
	}

	public Cell getNeighbour(Direction dir, Direction dir2) {

		if (odd) {
			if (dir == Direction.North && dir2 == Direction.West) {
				if (indexY > 0) {
					return (world.grid[indexX][indexY - 1]);
				}
			} else if (dir == Direction.North && dir2 == Direction.East) {
				if (indexY > 0 && indexX < World.WORLD_SIZE_W - 1) {
					return (world.grid[indexX + 1][indexY - 1]);
				}
			} else if (dir == Direction.South && dir2 == Direction.West) {
				if (indexY < World.WORLD_SIZE_H - 1) {
					return (world.grid[indexX][indexY + 1]);
				}
			} else if (dir == Direction.South && dir2 == Direction.East) {
				if (indexY < World.WORLD_SIZE_H - 1 && indexX < World.WORLD_SIZE_W - 1) {
					return (world.grid[indexX + 1][indexY + 1]);
				}
			}
		} else {
			if (dir == Direction.North && dir2 == Direction.West) {
				if (indexY > 0 && indexX > 0) {
					return (world.grid[indexX - 1][indexY - 1]);
				}
			} else if (dir == Direction.North && dir2 == Direction.East) {
				if (indexY > 0) {
					return (world.grid[indexX][indexY - 1]);
				}
			} else if (dir == Direction.South && dir2 == Direction.West) {
				if (indexY < World.WORLD_SIZE_H && indexX > 0) {
					return (world.grid[indexX - 1][indexY + 1]);
				}
			} else if (dir == Direction.South && dir2 == Direction.East) {
				if (indexY < World.WORLD_SIZE_H) {
					return (world.grid[indexX][indexY + 1]);
				}
			}
		}

		return null;
	}

	public Cell[] getNeighbours() {
		Cell[] data = new Cell[] { null, null, null, null };

		data[0] = getNeighbour(Direction.North, Direction.West);
		data[1] = getNeighbour(Direction.North, Direction.East);
		data[2] = getNeighbour(Direction.South, Direction.East);
		data[3] = getNeighbour(Direction.South, Direction.West);

		return data;
	}
	
	public int[] getXPoints() {
		return new int[] { (int)x - 33, (int)x - 1, (int)x + 31, (int)x - 1 };
	}
	
	public int[] getYPoints() {
		return new int[] { (int)y - 8, (int)y - 24, (int)y - 8, (int)y + 8 };
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x-32, (int)y-24, 64, 48);
	}
}
