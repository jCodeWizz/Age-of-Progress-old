package dev.codewizz.engine.gameobject;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

public class GameObject {

	private String name;
	private List<Component> components;
	public Transform transform;
	private int zIndex;
	
	public GameObject(String name, int zIndex) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = new Transform();
		this.zIndex = zIndex;
	}
	
	public GameObject(String name) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = new Transform();
		this.zIndex = 0;
	}
	
	public GameObject(String name, Transform transform) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = transform;
		this.zIndex = 0;
	}
	
	public GameObject(String name, Transform transform, int zIndex) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = transform;
		this.zIndex = zIndex;
	}
	
	public GameObject(String name, Vector2f loc) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = new Transform(loc);
		this.zIndex = 0;
	}
	
	public GameObject(String name, Vector2f loc, int zIndex) {
		this.name = name;
		components = new ArrayList<>();
		this.transform = new Transform(loc);
		this.zIndex = zIndex;
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
	
	public void clearComponents() {
		components.clear();
	}
	
	public <T extends Component> void removeComponent(Class<T> componentClass) {
		for(int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if(componentClass.isAssignableFrom(c.getClass())) {
				components.get(i).remove();
				components.remove(i);
			}
		}
	}
	
	public GameObject addComponent(Component c) {
		this.components.add(c);
		c.gameObject = this;
	
		return this;
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
	
	public int getZIndex() {
		return this.zIndex;
	}
}
