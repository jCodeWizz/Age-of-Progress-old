package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;

public class Tree extends GameObject {

	private static Sprite texture = Assets.getSprite("tree");
	
	public Tree(float x, float y) {
		super(x, y);

	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x, y);
	}
}
