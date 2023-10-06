package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Serializable;

public class Tree extends GameObject implements Serializable {

	private static Sprite texture = Assets.getSprite("tree");
	
	public Tree(float x, float y) {
		super(x, y);

		this.x -= 32;
		this.y += 25;
		
		this.id = ID.Tree;
	}

	@Override
	public void update(float d) {
		
	}
                                   
	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x, y);
	}

	@Override
	public RCObject save(RCObject object) {
		return object;
	}

	@Override
	public void load(RCObject object) {
		Main.inst.world.objects.add(this);
	}
}
