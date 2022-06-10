package dev.codewizz.engine.gameobject.components;

import org.joml.Vector4f;

import dev.codewizz.engine.gameobject.Component;

public class SpriteRenderer extends Component {

	Vector4f color;
	
	public SpriteRenderer(Vector4f color) {
		this.color = color;
	}
	
	
	@Override
	public void start() {
	}
	@Override
	public void update(float dt) {
	}
	
	public Vector4f getColor() {
		return color;
	}
}
