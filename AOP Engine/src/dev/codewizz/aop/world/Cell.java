package dev.codewizz.aop.world;

import java.awt.Rectangle;

import org.joml.Vector2f;

import dev.codewizz.aop.components.Tile;
import dev.codewizz.aop.components.TileType;
import dev.codewizz.aop.components.tiles.DirtTile;
import dev.codewizz.aop.components.tiles.EmptyTile;
import dev.codewizz.aop.components.tiles.GrassTile;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.util.AssetPool;
import dev.codewizz.engine.util.Direction;

public class Cell {

	private int x, y, tileX, tileY;
	private GameObject tile;
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
		this.tile = new GameObject("Tile", new Transform(new Vector2f(tileX, tileY), new Vector2f(Tile.WIDTH, Tile.HEIGHT))).addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/base-tile.png"))));
		this.setTile(TileType.EmptyTile);
		Window.getScene().addGameObjectToScene(this.tile);
		this.state = CellState.Empty;
	}
	
	public Cell(int x, int y, int tileX, int tileY, boolean odd, World world, GameObject tile) {
		this.x = x;
		this.y = y;
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.odd = odd;
		
		this.world = world;
		this.tile = tile;
		Window.getScene().addGameObjectToScene(this.tile);
		this.state = CellState.Used;
	}
	
	public Cell(int x, int y, int tileX, int tileY, boolean odd, World world, TileType tile) {
		this.x = x;
		this.y = y;
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.odd = odd;
		
		this.world = world;
		this.tile = new GameObject("Tile", new Transform(new Vector2f(tileX, tileY), new Vector2f(Tile.WIDTH, Tile.HEIGHT))).addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/base-tile.png"))));
		this.setTile(tile);
		Window.getScene().addGameObjectToScene(this.tile);
		this.state = CellState.Used;
	}
	
	public GameObject setTile(TileType tile) {
		if(tile == TileType.GrassTile) {
			this.tile.removeComponent(Tile.class);
			this.tile.addComponent(new GrassTile(x, y));
			this.tile.getComponent(Tile.class).setSprite();
			
			return this.tile;
		} else if(tile == TileType.EmptyTile) {
			this.tile.removeComponent(Tile.class);
			this.tile.addComponent(new EmptyTile(x, y));
			this.tile.getComponent(Tile.class).setSprite();
			
			return this.tile;
		} else if(tile == TileType.DirtTile) {
			this.tile.removeComponent(Tile.class);
			this.tile.addComponent(new DirtTile(x, y));
			this.tile.getComponent(Tile.class).setSprite();
			
			return this.tile;
		} else if(tile == TileType.DirtPath) {
			this.tile.removeComponent(Tile.class);
			this.tile.addComponent(new GrassTile(x, y));
			this.tile.getComponent(Tile.class).setSprite();
			
			return this.tile;
		} else if(tile == TileType.TiledPath) {
			this.tile.removeComponent(Tile.class);
			this.tile.addComponent(new GrassTile(x, y));
			this.tile.getComponent(Tile.class).setSprite();
			
			return this.tile;
	}
		
		return null;
	}
	
	public Cell getNeighbour(Direction dir, Direction dir2) {
		
		if(odd) {
			if(dir == Direction.Up && dir2 == Direction.Left) {
				if(y > 0) {
					return(world.grid[x][y-1]);
				}
			} else if(dir == Direction.Up && dir2 == Direction.Right) {
				if(y > 0 && x < World.WIDTH-1) {
					return(world.grid[x+1][y-1]);
				}
			} else if(dir == Direction.Down && dir2 == Direction.Left) {
				if(y < World.HEIGHT-1) {
					return(world.grid[x][y+1]);
				}
			} else if(dir == Direction.Down && dir2 == Direction.Right) {
				if(y < World.HEIGHT-1 && x < World.WIDTH-1) {
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
				if(y < World.HEIGHT && x > 0) {
					return(world.grid[x-1][y+1]);
				}
			} else if(dir == Direction.Down && dir2 == Direction.Right) {
				if(y < World.HEIGHT) {
					return(world.grid[x][y+1]);
				}
			}
		}
		
		return null;
	}
	
	public void setTile(GameObject tileObject) {
		if(tileObject.getComponent(Tile.class).getType() != this.getTile().getComponent(Tile.class).getType()) {
			if(tileObject.getComponent(Tile.class).getType() == TileType.EmptyTile)
				this.state = CellState.Empty;
			else
				this.state = CellState.Used;
			this.tile = tileObject;
			tileObject.getComponent(Tile.class).onPlace();
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public GameObject getTile() {
		return tile;
	}

	public CellState getState() {
		return state;
	}

	public World getWorld() {
		return world;
	}

	public boolean isOdd() {
		return odd;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(tileX, tileY, Tile.WIDTH, Tile.HEIGHT);
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
