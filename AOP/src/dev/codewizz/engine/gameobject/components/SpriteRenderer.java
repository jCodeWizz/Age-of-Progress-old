package dev.codewizz.engine.gameobject.components;

import org.joml.Vector2f;
import org.joml.Vector4f;

import dev.codewizz.engine.gameobject.Component;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.renderer.Texture;

public class SpriteRenderer extends Component implements Comparable<SpriteRenderer> {

	private Vector4f color;
	private Sprite sprite;
	
	public Transform lastTransform;
	private boolean isDirty = false;
	
	public SpriteRenderer(Vector4f color) {
		this.color = color;
		this.sprite = new Sprite(null);
		this.isDirty = true;
	}
	
	public SpriteRenderer(Sprite sprite) {
		this.sprite = sprite;
		this.color = new Vector4f(1, 1, 1, 1);
		this.isDirty = true;
	}
	
	
	@Override
	public void start() {
		this.lastTransform = gameObject.transform.copy();
	}
	@Override
	public void update(float dt) {
		if(lastTransform != null) {
			if(!this.lastTransform.equals(this.gameObject.transform)) {
				isDirty = true;
			}
		}
	}
	
	public Vector4f getColor() {
		return color;
	}
	
	public Texture getTexture() {
		return this.sprite.getTexture();
	}
	
	public Vector2f[] getTexCoords() {
		return this.sprite.getTexCoords();
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		this.isDirty = true;
	}
	
	public void setColor(Vector4f color) {
		if(!this.color.equals(color)) {
			this.color.set(color);
			this.isDirty = true;
		}
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	public void setClean() {
		isDirty = false;
	}
	
	public void setDirty() {
		this.isDirty = true;
	}
	
	@Override
	public int compareTo(SpriteRenderer o) {
		return Integer.compare((int)o.gameObject.transform.position.y, (int)this.gameObject.transform.position.y);
	}

	@Override
	public void remove() {
		
	}
}
