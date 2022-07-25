package dev.codewizz.aop.component.tiles;

import dev.codewizz.aop.world.Cell;
import dev.codewizz.aop.world.TileID;
import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.object.component.SpriteRenderer;
import dev.codewizz.engine.renderer.Sprite;
import dev.codewizz.engine.util.AssetPool;

public abstract class Tile extends Component {

	public static final int WIDTH = 64, HEIGHT = 48;

	protected String texturePath;
	protected int indexX, indexY;
	protected TileID id;
	protected Cell cell;

	public Tile(int i, int j, Cell cell) {
		this.indexX = i;
		this.indexY = j;
		this.cell = cell;
	}

	public void onPlace() {
		Cell[] cells = this.cell.getNeighbours();

		for (int i = 0; i < cells.length; i++) {
			if (cells[i] != null && cells[i].tile != null) {
				cells[i].tile.getComponent(Tile.class).updateTile();
			}
		}
	}
	
	public boolean[] checkNeighbours() {
		boolean[] data = new boolean[] { false, false, false, false };
		Cell[] neighbours = this.cell.getNeighbours();

		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i] == null) {
				data[i] = false;
			} else {
				data[i] = neighbours[i].getTile().getID() == this.id;
			}
		}
		return data;
	}
	
	public boolean[] checkNeighbours(TileID type) {
		boolean[] data = new boolean[] { false, false, false, false };
		Cell[] neighbours = this.cell.getNeighbours();

		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i] == null) {
				data[i] = false;
			} else {
				data[i] = neighbours[i].getTile().id == type;
			}
		}
		return data;
	}

	public void onBreak() {
	}

	public void onUpdate() {
	}

	public void updateTile() {
		onUpdate();
		updateSprite();
	}

	@Override
	public void start() {
		updateSprite();
	}

	public void updateSprite() {
		this.gameObject.getComponent(SpriteRenderer.class).setSprite(new Sprite(AssetPool.getTexture(texturePath)));
	}

	public String getTexturePath() {
		return this.texturePath;
	}

	public TileID getID() {
		return id;
	}

}
