package dev.codewizz.aop.world;

import dev.codewizz.aop.component.WorldController;
import dev.codewizz.aop.component.tiles.Tile;
import dev.codewizz.engine.object.GameObject;
import dev.codewizz.engine.util.Direction;

public class Cell {

	public int gridX, gridY;
	public float tileX, tileY;
	public boolean odd;
	private WorldController world;

	public GameObject tile;

	public Cell(int gridX, int gridY, float tileX, float tileY, boolean odd, WorldController world) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.tileX = tileX;
		this.tileY = tileY;
		this.world = world;
	}
	
	public Tile getTile() {
		return tile.getComponent(Tile.class);
	}

	public void setTile(Tile t) {
		if (this.tile.getComponent(Tile.class) == null) {
			tile.addComponent(t);
			tile.getComponent(Tile.class).onPlace();
			return;
		}

		if (this.tile.getComponent(Tile.class).getID() != t.getID()) {
			//Window.getScene().getGameObject("Camera").getComponent(CameraController.class).shake(0.25f, 5);
			this.tile.getComponent(Tile.class).onBreak();
			tile.removeComponent(Tile.class);
			tile.addComponent(t);
			tile.getComponent(Tile.class).onPlace();
		}
	}

	public Cell getNeighbour(Direction dir, Direction dir2) {

		if (odd) {
			if (dir == Direction.Up && dir2 == Direction.Left) {
				if (gridY > 0) {
					return (world.grid[gridX][gridY - 1]);
				}
			} else if (dir == Direction.Up && dir2 == Direction.Right) {
				if (gridY > 0 && gridX < WorldController.WIDTH - 1) {
					return (world.grid[gridX + 1][gridY - 1]);
				}
			} else if (dir == Direction.Down && dir2 == Direction.Left) {
				if (gridY < WorldController.HEIGHT - 1) {
					return (world.grid[gridX][gridY + 1]);
				}
			} else if (dir == Direction.Down && dir2 == Direction.Right) {
				if (gridY < WorldController.HEIGHT - 1 && gridX < WorldController.WIDTH - 1) {
					return (world.grid[gridX + 1][gridY + 1]);
				}
			}
		} else {
			if (dir == Direction.Up && dir2 == Direction.Left) {
				if (gridY > 0 && gridX > 0) {
					return (world.grid[gridX - 1][gridY - 1]);
				}
			} else if (dir == Direction.Up && dir2 == Direction.Right) {
				if (gridY > 0) {
					return (world.grid[gridX][gridY - 1]);
				}
			} else if (dir == Direction.Down && dir2 == Direction.Left) {
				if (gridY < WorldController.HEIGHT-1 && gridX > 0) {
					return (world.grid[gridX - 1][gridY + 1]);
				}
			} else if (dir == Direction.Down && dir2 == Direction.Right) {
				if (gridY < WorldController.HEIGHT-1) {
					return (world.grid[gridX][gridY + 1]);
				}
			}
		}

		return null;
	}

	public Cell[] getNeighbours() {
		Cell[] data = new Cell[] { null, null, null, null };

		data[0] = getNeighbour(Direction.Up, Direction.Left);
		data[1] = getNeighbour(Direction.Up, Direction.Right);
		data[2] = getNeighbour(Direction.Down, Direction.Right);
		data[3] = getNeighbour(Direction.Down, Direction.Left);

		return data;
	}

}
