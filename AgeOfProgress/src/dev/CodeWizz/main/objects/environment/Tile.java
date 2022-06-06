package dev.CodeWizz.main.objects.environment;

import java.awt.Polygon;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Camera;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.objects.environment.tiles.EmptyTile;
import dev.CodeWizz.main.objects.environment.tiles.GrassTile;

public abstract class Tile {

	private static Image basetexture = Textures.get("base-tile");

	protected TileType type;
	protected Cell cell;
	protected int x, y, w, h;

	public Tile(int x, int y, Cell cell) {
		this.cell = cell;
		this.x = x;
		this.y = y;

		w = 64;
		h = 48;

	}

	public void tick(GameContainer gc) {
	}

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(basetexture, x - (w / 2), y - (h / 2));
	}
	
	public void onPlace() {
		Camera.shake(13, 5);
		
		Cell[] cells = this.cell.getNeighbours();
		
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] != null) {
				cells[i].getTile().update();
			}
		}
	}
	
	public void update() {
		
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
	
	public static Tile tileFromType(TileType type, int x, int y, Cell cell) {
		if(type == TileType.Empty) {
			return EmptyTile.getNew(x, y, cell);
		} else if(type == TileType.Grass) {
			return GrassTile.getNew(x, y, cell);
		}
		
		return null;
	}

	public void render2(GameContainer gc, Renderer r) {
		// r.drawPolygon(0xffffffff, getHitbox());
		// r.fillRect(x, y, 1, 1, 0xffff0000, Light.NONE);
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public Cell getCell() {
		return cell;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Polygon getHitbox() {
		return new Polygon(cell.getXPoints(), cell.getYPoints(), 4);
	}
}
