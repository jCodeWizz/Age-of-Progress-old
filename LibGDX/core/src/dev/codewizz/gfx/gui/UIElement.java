package dev.codewizz.gfx.gui;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class UIElement {

	protected int x, y, w, h;
	protected boolean hovering = false, pressed = false;
	
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
	
	
}
