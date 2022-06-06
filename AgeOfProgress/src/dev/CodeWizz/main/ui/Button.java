package dev.CodeWizz.main.ui;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;

public class Button {

	public int x, y, w, h;
	public boolean pressed;
	public boolean activated = true;
	public int scale;
	
	public Image texture, texturePressed, icon;
	
	public Button(int x, int y, int w, int h, Image a, Image b, Image c, int scale) {
		this.x = x;
		this.y = y;
		this.w = w * scale;
		this.h = h * scale;
		
		this.texture = a;
		this.texturePressed = b;
		this.icon = c;
		
		this.scale = scale;
	}
	
	public void press(GameContainer gc) {
		pressed = true;
		onClick(gc);
	}
	
	public void unpress(GameContainer gc, boolean wasOnIt) {
		if(pressed && wasOnIt)
			onDeclick(gc);
		
		pressed = false;
	}
	
	public void onClick(GameContainer gc) {
		
	}
	
	public void onDeclick(GameContainer gc) {
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	
	public void renderUI(GameContainer gc, Renderer r) {
		if(activated) {
			if(pressed) {
				r.drawImageUI(texturePressed, x, y, scale);
				if(icon != null) {
					r.drawImageUI(icon, x, y+2*scale, scale);
				}
			} else {
				r.drawImageUI(texture, x, y, scale);
				if(icon != null) {
					r.drawImageUI(icon, x, y, scale);
				}
			}
			
			
		} else {
			r.drawImageUI(texturePressed, x, y, scale);
		}
	}
}
