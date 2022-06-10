package dev.codewizz.engine.gameobject;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

public class GameObject {

	private String name;
	private List<Component> components;
	public Transform transform;
	
	public GameObject(String name) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = new Transform();
	}
	
	public GameObject(String name, Transform transform) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = transform;
	}
	
	public GameObject(String name, Vector2f loc) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = new Transform(loc);
	}
	
	public <T extends Component> T getComponent(Class<T> componentClass) {
		for(Component c : components) {
			if(componentClass.isAssignableFrom(c.getClass())) {
				try {
					return componentClass.cast(c);
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public <T extends Component> void removeComponent(Class<T> componentClass) {
		for(int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if(componentClass.isAssignableFrom(c.getClass())) {
				components.remove(i);
			}
		}
	}
	
	public void addComponent(Component c) {
		this.components.add(c);
		c.gameObject = this;
	}
	
	public void update(float dt) {
		for(int i = 0; i < components.size(); i++) {
			components.get(i).update(dt);
		}
	}
	
	public void start() {
		for(int i = 0; i < components.size(); i++) {
			components.get(i).start();
		}
	}
	
	public String getName() {
		return name;
	}
}
