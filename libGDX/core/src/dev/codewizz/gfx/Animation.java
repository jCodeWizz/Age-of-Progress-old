package dev.codewizz.gfx;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Animation {

	public Sprite[] sprites;
	
	public int currentIndex = 0;
	
	private float speed = 1f;
	private float time = 0f;
	
	public Animation(float frameTime) {
	}
	
	public Sprite getCurrentSprite(float dt) {
		
		
		
		
		return sprites[currentIndex];
	}
	
	
	
	
}
