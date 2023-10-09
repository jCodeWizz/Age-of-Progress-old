package dev.codewizz.world.objects;

import java.awt.Polygon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.gfx.Particle;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Utils;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Serializable;

public class Tree extends GameObject implements Serializable, IGatherable {

	private static Sprite texture = Assets.getSprite("tree");
	
	public Tree(float x, float y) {
		super(x, y);

		this.w = 64;
		this.h = 64;
		
		this.sortHeight = 25f;
		
		this.id = ID.Tree;
	}

	@Override
	public void update(float d) {
		
	}
                                   
	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x - 32, y + 25);
	}
	
	@Override
	public Polygon getHitBox() {
		return new Polygon( new int[] {(int)x + 27, (int)x + 27, (int)x + 37, (int)x + 37},
							new int[] {(int)y + 32, (int)y + 100, (int)y + 100, (int)y + 32}, 4) ;
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
	public int duration() {
		return 10;
	}

	@Override
	public void gather() {
		for(int i = 0; i < 30; i++) {
			Particle p = new Particle(x + 30 + Utils.getRandom(0, 30), y + 50 + Utils.getRandom(0, 50), 2, 2)
					.color(Color.BROWN)
					.gravity(-1f, 20);
			Main.inst.world.particles.add(p);
		}
		
		
		destroy();
	}
}
