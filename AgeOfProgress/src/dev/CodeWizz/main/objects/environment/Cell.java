package dev.CodeWizz.main.objects.environment;

import dev.CodeWizz.engine.util.Direction;
import dev.CodeWizz.main.objects.environment.tiles.DirtPathTile;
import dev.CodeWizz.main.objects.environment.tiles.DirtTile;
import dev.CodeWizz.main.objects.environment.tiles.EmptyTile;
import dev.CodeWizz.main.objects.environment.tiles.GrassTile;
import dev.CodeWizz.main.objects.environment.tiles.TiledTile;

public class Cell {

	private int x, y, tileX, tileY;
	private Tile tile;
	private CellState state;
	private World world;
	private boolean odd;
	
	public Cell(int x, int y, int tileX, int tileY, boolean odd, World world) {
		this.x = x;
		this.y = y;
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.odd = odd;
		
		this.world = world;
		this.tile = new EmptyTile(tileX, tileY, this);
		this.state = CellState.Empty;
	}
	
	public Cell(int x, int y, int tileX, int tileY, boolean odd, World world, Tile tile) {
		this.x = x;
		this.y = y;
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.odd = odd;
		
		this.world = world;
		this.tile = tile;
		this.state = CellState.Full;
	}
	
	public Cell getNeighbour(Direction dir, Direction dir2) {
		
		if(odd) {
			if(dir == Direction.Up && dir2 == Direction.Left) {
				if(y > 0) {
					return(world.grid[x][y-1]);
				}
			} else if(dir == Direction.Up && dir2 == Direction.Right) {
				if(y > 0 && x < World.WORLD_SIZE_W-1) {
					return(world.grid[x+1][y-1]);
				}
			} else if(dir == Direction.Down && dir2 == Direction.Left) {
				if(y < World.WORLD_SIZE_H-1) {
					return(world.grid[x][y+1]);
				}
			} else if(dir == Direction.Down && dir2 == Direction.Right) {
				if(y < World.WORLD_SIZE_H-1 && x < World.WORLD_SIZE_W-1) {
					return(world.grid[x+1][y+1]);
				}
			}
		} else {
			if(dir == Direction.Up && dir2 == Direction.Left) {
				if(y > 0 && x > 0) {
					return(world.grid[x-1][y-1]);
				}
			} else if(dir == Direction.Up && dir2 == Direction.Right) {
				if(y > 0) {
					return(world.grid[x][y-1]);
				}
			} else if(dir == Direction.Down && dir2 == Direction.Left) {
				if(y < World.WORLD_SIZE_H && x > 0) {
					return(world.grid[x-1][y+1]);
				}
			} else if(dir == Direction.Down && dir2 == Direction.Right) {
				if(y < World.WORLD_SIZE_H) {
					return(world.grid[x][y+1]);
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
	
	public Cell setGrass() {
		this.state = CellState.Full;
		setTile(new GrassTile(tileX, tileY, this));
		return this;
	}
	
	public Cell setDirtPath() {
		this.state = CellState.Full;
		setTile(new DirtPathTile(tileX, tileY, this));
		return this;
	}
	
	public Cell setTiled() {
		this.state = CellState.Full;
		setTile(new TiledTile(tileX, tileY, this));
		return this;
	}
	
	public Cell setDirt() {
		this.state = CellState.Full;
		setTile(new DirtTile(tileX, tileY, this));
		return this;
	}
	
	public Cell setEmpty() {
		this.state = CellState.Empty;
		setTile(new EmptyTile(tileX, tileY, this));
		return this;
	}
	
	public int[] getXPoints() {
		return new int[] { tileX - 33, tileX - 1, tileX + 31, tileX - 1 };
	}
	
	public int[] getYPoints() {
		return new int[] { tileY - 8, tileY - 24, tileY - 8, tileY + 8 };
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		if(tile.getType() != this.getTile().getType()) {
			this.tile = tile;
			tile.onPlace();
		}
	}

	public CellState getState() {
		return state;
	}

	public void setState(CellState state) {
		this.state = state;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		if(this.state == CellState.Full) {
			return "1"; 
		} else {
			return "0";
		}
	}
	
	
}
