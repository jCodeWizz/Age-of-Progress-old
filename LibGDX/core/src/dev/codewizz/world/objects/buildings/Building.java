package dev.codewizz.world.objects.buildings;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import box2dLight.PointLight;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Serializable;
import dev.codewizz.world.items.Item;
import dev.codewizz.world.items.ItemType;
import dev.codewizz.world.objects.IBuy;
import dev.codewizz.world.objects.ID;
import dev.codewizz.world.objects.hermits.Hermit;

public class Building extends GameObject implements IBuy, Serializable {
	
	private static Sprite texture = Assets.getSprite("tent");
	private static Sprite icon = Assets.getSprite("tent");

	private List<Item> costs = new CopyOnWriteArrayList<>();
	
	private List<Hermit> inside = new CopyOnWriteArrayList<>();
	public ArrayList<Hermit> owned = new ArrayList<>();
	
	private final float sleepConst = 1f;
	
	public int size = 2;
	private PointLight light;
	
	public Building(float x, float y) {
		super(x, y);

		this.id = ID.Building;
		
		if(Main.inst.world.settlement != null) 
			Main.inst.world.settlement.homes.add(this);
		
		this.w = 64;
		this.h = 48;
		
		this.sortHeight = 8;
		
		this.costs.add(new Item(0, 0, ItemType.Wood, 15));
	}
	
	public void enter(Hermit hermit) {
		inside.add(hermit);
		Main.inst.world.objects.remove(hermit);
	}
	
	public void leave(Hermit hermit) {
		inside.remove(hermit);
		Main.inst.world.objects.add(hermit);
		hermit.getCurrentTask().finish();
	}

	@Override
	public void update(float d) {
		for(Hermit h : inside) {
			
			if(h.getSleepNeed() <= 0f) {
				leave(h);
			}
			
			h.setSleepNeed(h.getSleepNeed() - sleepConst * d);
		}
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x - 15, y + 16);
	}
	
	@Override
	public void onDestroy() {
		Main.inst.renderer.removeLight(light);
		for(Hermit hermit : owned) {
			hermit.setHome(null);
		}
		
		for(Hermit hermit : inside) {
			leave(hermit);
		}
	}
	
	@Override
	public Polygon getHitBox() {
		return new Polygon( new int[] {(int)x + 36, (int)x - 11, (int)x + 6, (int)x + 49, (int)x + 65}, 
							new int[] {(int)y + 16, (int)y + 40, (int)y + 80, (int)y + 64, (int)y + 32}, 5);
	}
	
	@Override
	public Sprite getMenuSprite() {
		return icon;
	}

	@Override
	public String getMenuName() {
		return "Building";
	}

	@Override
	public String getMenuDescription() {
		return "A nice house.";
	}

	@Override
	public GameObject getCopy(float x, float y) {
		return new Building(x, y);
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
		this.light = Main.inst.renderer.addLight(x + 48, y + 32, 150);
	}
	
	@Override
	public RCObject save(RCObject object) {
		return object;
	}

	@Override
	public void load(RCObject object) {
		Main.inst.world.objects.add(this);
	}
	
	public boolean isFull() {
		return owned.size() >= size;
	}

	@Override
	public List<Item> costs() {
		return costs;
	}
}
