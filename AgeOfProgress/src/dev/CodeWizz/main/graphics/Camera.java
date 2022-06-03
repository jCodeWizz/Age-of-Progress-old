package dev.CodeWizz.main.graphics;

import dev.CodeWizz.engine.util.Vector;

public class Camera {

	private Vector pos;
	
	public Camera() {
		pos = new Vector();
	}
	
	
	
	public void setX(float x) {
		this.pos.x = x;
	}
	
	public void setY(float y) {
		this.pos.y = y;
	}
	
	public Vector getPosition() {
		return pos;
	}
}
