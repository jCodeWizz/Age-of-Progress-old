package dev.codewizz.engine.object;

public abstract class Component {

	private boolean enabled = true;
	
    public GameObject gameObject = null;

    public abstract void start();

    public abstract void update(float dt);

    public boolean isEnabled() {
    	return enabled;
    }
    
    public void renderUI() {
    	
    }
    
    public void enable() {
    	this.enabled = true;
    }
    
    public void disable() {
    	this.enabled = false;
    }
}