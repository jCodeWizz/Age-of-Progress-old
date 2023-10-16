package dev.codewizz.world.objects.hermits;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;

public class Farmer extends Job {

	private static Sprite texture = Assets.getSprite("farmer-hermit");
	
	
	public Farmer() {
		this.job = Jobs.Farmer;
	}

	@Override
	public void update(float dt) {
		
		
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, hermit.getX(), hermit.getY(), hermit.getW(), hermit.getH());
	}
}