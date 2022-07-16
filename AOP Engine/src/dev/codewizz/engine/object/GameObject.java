package dev.codewizz.engine.object;


import java.util.ArrayList;
import java.util.List;

import dev.codewizz.engine.object.component.Transform;

public class GameObject {

    public String name;
    private List<Component> components;
    public Transform transform;
    private int zIndex;

    public GameObject(String name) {
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = new Transform();
        this.zIndex = 0;
    }

    public GameObject(String name, Transform transform, int zIndex) {
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component GameObject: " + this.name;
                }
            }
        }

        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for (int i=0; i < components.size(); i++) {
            Component c = components.get(i);
            if (componentClass.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public GameObject addComponent(Component c) {
        this.components.add(c);
        c.gameObject = this;
        return this;
    }

    public void update(float dt) {
        for (int i=0; i < components.size(); i++) {
            components.get(i).update(dt);
        }
    }

    public void start() {
        for (int i=0; i < components.size(); i++) {
            components.get(i).start();
        }
    }

    public int zIndex() {
        return this.zIndex;
    }
    
    @Override
    public String toString() {
    	String a = "Name: " + this.name + "\nPosition: { " + this.transform.position.x + " ; " + this.transform.position.y + " }\nScale: { " + this.transform.scale.x + " ; " + this.transform.scale.y + " }\nComponents:";
    	
    	for(Component c : this.components) {
    		a += "\n - " + c.getClass().getSimpleName();
    	}
    	return a;
    }
}