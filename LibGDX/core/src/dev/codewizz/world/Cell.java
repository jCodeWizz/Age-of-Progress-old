package dev.codewizz.world;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Direction;
import dev.codewizz.world.pathfinding.CellGraph;
import dev.codewizz.world.pathfinding.Link;
import dev.codewizz.world.tiles.BaseTile;
import dev.codewizz.world.tiles.WaterTile;

public class Cell {

	public Tile tile;
	public float x, y;
	public int indexX, indexY;
	public boolean odd = false;
	public World world;
	public int index;

	public Cell(float x, float y, int indexX, int indexY, boolean odd, World world) {
		this.x = x;
		this.y = y;
		this.indexX = indexX;
		this.indexY = indexY;
		this.tile = new BaseTile(this);
		this.odd = odd;
		this.world = world;
		
	}
	
	public void init(CellGraph graph) {
		Cell[] neighBours = getCrossedNeighbours();
		for(int i = 0; i < neighBours.length; i++) {
			if(neighBours[i] != null) {
				graph.connectCells(this, neighBours[i], tile.cost);
			}
		}
	}

	public void setTile(Tile tile) {
		if(this.tile.type != tile.type) {
			this.tile.onDestroy();
			this.tile = tile;
			this.tile.cell = this;
			this.tile.place();
		} else {
		}
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
	
	public Cell[] getCrossedNeighbours() {
		Cell[] data = new Cell[] { null, null, null, null , null, null, null, null };
		
		data[0] = getNeighbour(Direction.South, Direction.West);
		data[1] = getNeighbour(Direction.South, Direction.East);
		data[2] = getNeighbour(Direction.North, Direction.East);
		data[3] = getNeighbour(Direction.North, Direction.West);
		if(indexX > 0) {
			data[4] = world.grid[indexX-1][indexY];
		}
		if(indexX < World.WORLD_SIZE_W-1) {
			data[5] = world.grid[indexX+1][indexY];
		}
		if(indexY > 1) {
			data[6] = world.grid[indexX][indexY-2];
		}
		if(indexY < World.WORLD_SIZE_H-2) {
			data[7] = world.grid[indexX][indexY+2];
		}
		
		return data;
	}

	public Cell[] getNeighbours() {
		Cell[] data = new Cell[] { null, null, null, null };

		data[0] = getNeighbour(Direction.South, Direction.West);
		data[1] = getNeighbour(Direction.South, Direction.East);
		data[2] = getNeighbour(Direction.North, Direction.East);
		data[3] = getNeighbour(Direction.North, Direction.West);

		return data;
	}
	
	public void drawBorders() {
		Renderer.drawDebugLine(new Vector2(getXPoints()[0], getYPoints()[0]), new Vector2(getXPoints()[1], getYPoints()[1]));
		Renderer.drawDebugLine(new Vector2(getXPoints()[1], getYPoints()[1]), new Vector2(getXPoints()[2], getYPoints()[2]));
		Renderer.drawDebugLine(new Vector2(getXPoints()[2], getYPoints()[2]), new Vector2(getXPoints()[3], getYPoints()[3]));
		Renderer.drawDebugLine(new Vector2(getXPoints()[3], getYPoints()[3]), new Vector2(getXPoints()[0], getYPoints()[0]));
	}
	
	public static void printDebugInfo(Cell cell) {
		System.out.println("CELL: [" + cell.indexX + "][" + cell.indexY + "]");
		System.out.println(" - X: " + cell.x + " Y: " + cell.y);
		System.out.println(" - ODD: " + cell.odd + " INDEX: " + cell.index);
		
		Array<Link> links = Main.inst.world.cellGraph.getLinks(cell);
		
		for(int i = 0; i < links.size; i++) {
			System.out.println(" - Link[" + i + "] " + links.get(i).getCost());
		}
		
		System.out.println("TILE: [" + cell.tile.getName() + "]");
		System.out.println(" - COST: " + cell.tile.cost);
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public int[] getXPoints() {
		return new int[] { (int)x, (int)x + 32, (int)x + 64, (int)x + 32};
	}
	
	public int[] getYPoints() {
		return new int[] { (int)y + 32, (int)y + 48, (int)y + 32, (int)y + 16};
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x-32, (int)y-24, 64, 48);
	}
	
	public Vector2 getMiddlePoint() {
		return new Vector2(x+32, y+32);
	}
}
