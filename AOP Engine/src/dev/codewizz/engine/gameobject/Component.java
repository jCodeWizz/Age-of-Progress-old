package dev.codewizz.engine.gameobject;

public abstract class Component {

	public GameObject gameObject;
	
	public abstract void start();
	public abstract void update(float dt);
	public abstract void remove();
}
