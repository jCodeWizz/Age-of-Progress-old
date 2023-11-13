package dev.codewizz.world.objects;

import java.awt.Polygon;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Serializable;
import dev.codewizz.world.items.Item;
import dev.codewizz.world.pathfinding.CellGraph;

public class FencePost extends GameObject implements Serializable, IBuy {

	private static Sprite texture = Assets.getSprite("fence-post");
	private List<Item> costs = new CopyOnWriteArrayList<>();
	
	public FencePost(float x, float y) {
		super(x, y);

		this.w = 16;
		this.h = 16;

		this.sortHeight = 25f;

		this.id = ID.FencePost;
		this.name = "Fence Post";
	}

	@Override
	public void update(float d) {
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x + 16, y + 31);
	}

	@Override
	public Polygon getHitBox() {
		return new Polygon(new int[] { (int) x + 30, (int) x + 30, (int) x + 34, (int) x + 34 },
				new int[] { (int) y + 31, (int) y + 55, (int) y + 55, (int) y + 31 }, 4);
	}

	@Override
	public RCObject save(RCObject object) {
		return object;
	}

	@Override
	public void load(RCObject object) {
		Main.inst.world.objects.add(this);
	}

	@Override
	public Sprite getMenuSprite() {
		return texture;
	}

	@Override
	public String getMenuName() {
		return "Fence Post";
	}

	@Override
	public String getMenuDescription() {
		return "The first workshop for your craftsman";
	}

	@Override
	public GameObject getCopy(float x, float y) {
		return new FencePost(x, y);
	}

	@Override
	public boolean conintues() {
		return false;
	}

	@Override
	public boolean available() {
		return true;
	}

	@Override
	public void onPlace(Cell cell) {
		CellGraph c = Main.inst.world.cellGraph;
		c.removeConnections(cell);
	}
	
	@Override
	public void onDestroy() {
		CellGraph c = Main.inst.world.cellGraph;
		
		Cell[] neighBours = cell.getCrossedNeighbours();
		for(int i = 0; i < neighBours.length; i++) {
			if(neighBours[i] != null) {
				c.connectCells(cell, neighBours[i], cell.tile.getCost());
			}
		}
	}

	@Override
	public List<Item> costs() {
		return costs;
	}
}
