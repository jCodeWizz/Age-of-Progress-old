package dev.codewizz.gfx.gui;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class UIElement {

	protected int x, y, w, h;
	protected boolean hovering = false, pressed = false, enabled = true, available = true;
	
	public UIElement(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void onClick() {
		
	}
	
	public void onDeClick() {
		
	}
	
	public void click() {
		pressed = true;
		onClick();
	}
	
	public void deClick() {
		pressed = false;
		onDeClick();
	}
	
	
	public abstract void render(SpriteBatch b);
	
	public Rectangle getBounds() {
		return new Rectangle(x, UILayer.HEIGHT - y - (h * UILayer.SCALE), w * UILayer.SCALE, h * UILayer.SCALE);
	}
	
	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	public boolean isHovering() {
		return hovering;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean isPressed() {
		return pressed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}
