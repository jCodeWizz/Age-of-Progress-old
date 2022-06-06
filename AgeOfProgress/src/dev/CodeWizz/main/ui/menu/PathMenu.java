package dev.CodeWizz.main.ui.menu;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Font;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.ui.Button;

public class PathMenu extends Menu {

	private Menu m;
	
	private BuySlot slot;
	
	public PathMenu() {
		this.buttons = new Button[2];
		m = this;
		
		this.x = 10;
		this.y = 10;
		this.w = 256;
		this.h = 256;
		
	}
	
	@Override
	public void init(GameContainer gc) {
		this.h = gc.getHeight()-20;
		buttons[0] = new Button(x + 233, y + 1, 22, 24, Textures.get("icon-button"), Textures.get("icon-button-pressed"), Textures.get("close-icon"), 1) {
			
			@Override
			public void onDeclick(GameContainer gc) {
				m.close(gc);
			}
			
		};
		
		slot = new BuySlot(13, 100, 0, 0, null, Textures.get("base-tile"), null, 1, "Base Tile", 10) {
			
			@Override
			public void onDeclick(GameContainer gc) {
			}
		};
		
		buttons[1] = slot;

	}
	
	@Override
	public void update(GameContainer gc) {
		
		// TODO: rework this shit so it work for everything
		
	}
	
	@Override
	public Rectangle getBoundsTop() {
		return new Rectangle(x, y, w-24, 26);
	}
	
	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		if(open) {
			r.drawImageUI(Textures.get("path-menu"), x, y);
			
			Renderer.font = Font.DETAILED;
			
			r.drawText("Pathing Menu", x+59, y + 5, 1, 0xffffffff);
			
			Renderer.font = Font.STANDARD;
			
			slot.renderUI(gc, r);
		}
	}
	
	@Override
	public void onOpen(GameContainer gc) {

	}

	@Override
	public void onClose(GameContainer gc) {

	}
}
