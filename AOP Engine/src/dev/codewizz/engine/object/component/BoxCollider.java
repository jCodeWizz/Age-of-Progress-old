package dev.codewizz.engine.object.component;

public class BoxCollider extends Collider {

	public float offsetX, offsetY, width, height;
	
	public BoxCollider(float offsetX, float offsetY, float width, float height) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}
	
	public BoxCollider(float width, float height) {
		this.offsetX = 0;
		this.offsetY = 0;
		this.width = width;
		this.height = height;
	}
	
	public BoxCollider(SpriteRenderer r) {
		this.width = r.getTexture().getWidth();
		this.height = r.getTexture().getHeight();
		
		this.offsetX = -(width/2);
		this.offsetY = -(height/2);
	}
	
	@Override
	public boolean contains(float x, float y) {
		return(x < gameObject.transform.position.x + offsetX + width && x > gameObject.transform.position.x + offsetX && y < gameObject.transform.position.y + offsetY + height && y > gameObject.transform.position.y + offsetY);
	}

	@Override
	public boolean intersects(BoxCollider c) {
		return false;
	}

	@Override
	public boolean intersects(CircleCollider c) {
		return c.intersects(this);
	}

	@Override
	public void start() {
		
	}

	@Override
	public void update(float dt) {
		
	}
}
