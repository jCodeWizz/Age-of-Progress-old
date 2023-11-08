package dev.codewizz.world.objects.hermits;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;

public class Worker extends Job {

	private static Sprite icon = Assets.getSprite("worker-icon");
	
	public Worker() {
		this.job = Jobs.Worker;
	}
	
	@Override
	public void update(float dt) {
		
	}

	@Override
	public void render(SpriteBatch b) {
		
	}

	@Override
	public Sprite getIcon() {
		return icon;
	}
}
