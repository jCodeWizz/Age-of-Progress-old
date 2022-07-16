package dev.codewizz.engine.object.component;

import org.joml.Vector2f;
import org.joml.Vector4f;

import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.renderer.Sprite;
import dev.codewizz.engine.renderer.Texture;

public class SpriteRenderer extends Component implements Comparable<SpriteRenderer> {

    private Vector4f color;
    private Sprite sprite;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite(null);
    }

    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
    }

    @Override
    public void start() {
    }

    @Override
    public void update(float dt) {
    }

    public Vector4f getColor() {
        return this.color;
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setColor(Vector4f color) {
        if (!this.color.equals(color)) {
            this.color.set(color);
        }
    }

	@Override
	public int compareTo(SpriteRenderer o) {
		return Integer.compare((int)o.gameObject.transform.position.y, (int)this.gameObject.transform.position.y);	
	}
}