package dev.codewizz.engine.object.component;

import dev.codewizz.engine.util.FloatMath;

public class CircleCollider extends Collider {

	public float offsetX = 0, offsetY = 0;
	public int r;
	
	public CircleCollider(int r) {
		this.r = r;
	}
	
	public CircleCollider(float offsetX, float offsetY, int r) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.r = r;
	}

	@Override
	public boolean contains(float x, float y) {
		return (FloatMath.distance(gameObject.transform.position.x + offsetX, gameObject.transform.position.y + offsetY, x, y) <= r);
	}

	@Override
	public boolean intersects(BoxCollider c) {
		
		float x = FloatMath.clamp(c.gameObject.transform.position.x + c.offsetX, c.gameObject.transform.position.x + offsetX + c.width, gameObject.transform.position.x + offsetX);
		float y = FloatMath.clamp(c.gameObject.transform.position.y + c.offsetY, c.gameObject.transform.position.y + offsetY + c.height, gameObject.transform.position.y + offsetY);
		
		return(FloatMath.distance(gameObject.transform.position.x + offsetX, gameObject.transform.position.y + offsetY, x, y) > r);
	}

	@Override
	public boolean intersects(CircleCollider c) {
		return(FloatMath.distance(gameObject.transform.position.x + offsetX, gameObject.transform.position.y + offsetY, c.gameObject.transform.position.x + c.offsetX, c.gameObject.transform.position.y + offsetY) <= c.r + r);
	}

	@Override
	public void start() {
		
	}

	@Override
	public void update(float dt) {
		
	}
}
