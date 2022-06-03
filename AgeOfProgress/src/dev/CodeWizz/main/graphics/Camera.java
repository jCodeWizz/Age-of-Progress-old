package dev.CodeWizz.main.graphics;

import java.awt.event.KeyEvent;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.util.Vector;

public class Camera {

	private float _acc = 0.5f;
	private float _dcc = 0.5f;
	private final float maxMovementSpeed = 5f;
	
	private Vector pos;
	private Vector vel;
	
	public Camera() {
		pos = new Vector();
		vel = new Vector();
	}
	
	public void update(GameContainer gc) {
		handleInput(gc);
		pos.add(vel);
	}
	
	private void handleInput(GameContainer gc) {
		if (vel.x <= 0.25f && vel.x >= -0.25f) {
			vel.x = 0;
		}
		if (gc.getInput().isKey(KeyEvent.VK_D)) {
			if (!gc.getInput().isKey(KeyEvent.VK_A) && vel.x < maxMovementSpeed) {
				vel.x += _acc;
			} else {
				if (vel.x > 0)
					vel.x -= _dcc;
				else if (vel.x < 0)
					vel.x += _dcc;
			}
		} else if (gc.getInput().isKey(KeyEvent.VK_A)) {
			if (!gc.getInput().isKey(KeyEvent.VK_D) && vel.x > -maxMovementSpeed) {
				vel.x -= _acc;
			} else {
				if (vel.x > 0)
					vel.x -= _dcc;
				else if (vel.x < 0)
					vel.x += _dcc;
			}
		} else if (!gc.getInput().isKey(KeyEvent.VK_D) && !gc.getInput().isKey(KeyEvent.VK_A)) {

			if (vel.x > 0)
				vel.x -= _dcc;
			else if (vel.x < 0)
				vel.x += _dcc;

		}
		
		if (gc.getInput().isKey(KeyEvent.VK_S)) {
			if (!gc.getInput().isKey(KeyEvent.VK_W) && vel.y < maxMovementSpeed) {
				vel.y += _acc;
			} else {
				if (vel.y > 0)
					vel.y -= _dcc;
				else if (vel.y < 0)
					vel.y += _dcc;
			}
		} else if (gc.getInput().isKey(KeyEvent.VK_W)) {
			if (!gc.getInput().isKey(KeyEvent.VK_S) && vel.y > -maxMovementSpeed) {
				vel.y -= _acc;
			} else {
				if (vel.y > 0)
					vel.y -= _dcc;
				else if (vel.y < 0)
					vel.y += _dcc;
			}
		} else if (!gc.getInput().isKey(KeyEvent.VK_S) && !gc.getInput().isKey(KeyEvent.VK_W)) {

			if (vel.y > 0)
				vel.y -= _dcc;
			else if (vel.y < 0)
				vel.y += _dcc;

		}
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
