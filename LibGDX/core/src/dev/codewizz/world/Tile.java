package dev.codewizz.world;

import java.awt.Polygon;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.pathfinding.CellGraph;
import dev.codewizz.world.pathfinding.Link;
import dev.codewizz.world.tiles.BaseTile;
import dev.codewizz.world.tiles.DirtPathTile;
import dev.codewizz.world.tiles.DirtTile;
import dev.codewizz.world.tiles.EmptyTile;
import dev.codewizz.world.tiles.TiledTile;

public abstract class Tile {
	
	protected Cell cell;
	protected Sprite texture;
	protected TileType type;
	protected String name;
	protected int cost = 1;
	
	protected List<GameObject> objects = new CopyOnWriteArrayList<>();
	
	public Tile(Cell cell) {
		this.cell = cell;
		this.type = TileType.Base;
		this.texture = Assets.getSprite("base-tile");
		this.name = "Grass Tile";
	}
	
	public void onPlace() {};
	public void onDestroy() {};
	public void update() {};
	
	public void place() {
		Cell[] cells = this.cell.getCrossedNeighbours();
		for(int i = 0; i < 4; i++) {
			if(cells[i] != null) {
				cells[i].tile.update();				
			}
		}
		
		CellGraph c = Main.inst.world.cellGraph;
		if(cost != -1) {
			if(c.containsCell(cell)) {
				for(Link link : c.getLinks(cell)) {
					link.setCost(cost);
				}
			} else {
				Cell[] neighBours = cell.getCrossedNeighbours();
				for(int i = 0; i < neighBours.length; i++) {
					if(neighBours[i] != null) {
						c.connectCells(cell, neighBours[i], this.cost);
					}
				}
			}
		} else {
			c.removeConnections(cell);
		}
		
		onPlace();
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}
	
	public void addObject(GameObject g) {
		objects.add(g);
	}
	
	public void removeObject(GameObject g) {
		objects.add(g);
	}
	
	public Sprite getCurrentSprite() {
		return texture;
	}
	
	public String getName() {
		return name;
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
	
	public static Tile getTileFromType(TileType type, Cell cell) {
		if(type == TileType.Empty) {
			return new EmptyTile(cell);
		} else if(type == TileType.Base) {
			return new BaseTile(cell);
		} else if(type == TileType.Dirt) {
			return new DirtTile(cell);
		} else if(type == TileType.DirtPath) {
			return new DirtPathTile(cell);
		} else if(type == TileType.Tiled) {
			return new TiledTile(cell);
		}
		
		return null;
	}
	
	public TileType getType() {
		return type;
	}
}
