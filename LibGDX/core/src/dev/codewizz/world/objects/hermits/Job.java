package dev.codewizz.world.objects.hermits;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Job {

	protected Hermit hermit;
	protected Jobs job;
	
	public abstract void update(float dt);
	public abstract void render(SpriteBatch b);
	
	public Hermit getHermit() {
		return hermit;
	}
	
	public void setHermit(Hermit hermit) {
		this.hermit = hermit;
	}
	
	public Jobs getJob() {
		return job;
	}
	
	public abstract Sprite getIcon();
}
