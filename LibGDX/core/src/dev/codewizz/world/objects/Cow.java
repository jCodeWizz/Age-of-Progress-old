package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;

public class Cow extends Animal {
	
	/*
	 * pTR1600:
	 * 
	 * jELMER cOw
	 * 
	 *  * HAppy FacE *
	 * 
	 */
	
	public Cow(float x, float y) {
		super(x, y);
		
		speed = 10f;
	}
	
	public Cow(float x, float y, Herd herd) {
		super(x, y, herd);
		
		speed = 10f;
	}
	
	@Override
	public void update(float d) {
		super.update(d);
		
		if(vel.x > 0) {
			facingRight = true;
		} else if(vel.x < 0){
			facingRight = false;
		}
	}

	@Override
	public void render(SpriteBatch b) {
		if(facingRight) {
			b.draw(Assets.getSprite("cow-idle"), x, y, 32, 32);
		} else {
			b.draw(Assets.getSprite("cow-idle"), x + 32, y, -32, 32);
		}
	}
}
