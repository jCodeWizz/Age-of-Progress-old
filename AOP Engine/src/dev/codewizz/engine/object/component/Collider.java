package dev.codewizz.engine.object.component;

import dev.codewizz.engine.object.Component;

public abstract class Collider extends Component {
	public abstract boolean contains(float x, float y);
	public abstract boolean intersects(BoxCollider c);
	public abstract boolean intersects(CircleCollider c);

}
