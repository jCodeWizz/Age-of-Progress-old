package dev.codewizz.world.objects;

import java.awt.Polygon;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Utils;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Serializable;
import dev.codewizz.world.items.Item;
import dev.codewizz.world.items.ItemType;

public class Mushrooms extends GameObject implements Serializable, IGatherable {

	private static Sprite texture = Assets.getSprite("mushrooms");
	
	private boolean tasked = false;
	
	public Mushrooms(float x, float y) {
		super(x, y);
		
		this.w = 16;
		this.h = 16;
		
		this.name = "Mushrooms";
		this.id = ID.Mushrooms;
		
		this.sortHeight = 28;
	}
	
	@Override
	public Polygon getHitBox() {
		return new Polygon( new int[] {(int)x + w/2 + 25, (int)x + 25, (int)x + w/2 + 25, (int)x + w + 25}, new int[] {(int)y + 28, (int)y + h/2 + 28, (int)y + h + 28, (int)y + h/2 + 28}, 4) ;
	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x + 25, y + 28, w, h);
	}

	@Override
	public int duration() {
		return 10;
	}

	@Override
	public void gather() {
		
		int amount = Utils.getRandom(1, 3);
		for(int i = 0; i < amount; i++) {
			Item item = new Item(x + 25 + Utils.getRandom(-15, 15), y + 30 + Utils.getRandom(-5, 5), ItemType.Mushrooms);
			Main.inst.world.objects.add(item);
		}
		
		destroy();
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
	public boolean ready() {
		if(tasked) { return false; }
		else { tasked = true; return true; }
	}
}
