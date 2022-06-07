package dev.CodeWizz.main.ui.menu;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Font;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.AgeOfProgress;
import dev.CodeWizz.main.input.Input;
import dev.CodeWizz.main.objects.environment.TileType;
import dev.CodeWizz.main.ui.Button;

public class PathMenu extends Menu {

	private Menu m;
	private List<BuySlot> tab1 = new CopyOnWriteArrayList<>();
	private int scrollY = 0;
	
	public PathMenu() {
		this.buttons = new Button[1];
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
		
		tab1.add(new BuySlot(13, 61, 0, 0, null, Textures.get("base-tile"), null, 1, "Base Tile", 10, this.getBoundsBuySlotSpace()) {
			@Override
			public void onDeclick(GameContainer gc) {
				Input.placing = TileType.Grass;
			}
		});
		
		tab1.add(new BuySlot(13, 141, 0, 0, null, Textures.get("tiled-tile"), null, 1, "Tiles", 10, this.getBoundsBuySlotSpace()) {
			@Override
			public void onDeclick(GameContainer gc) {
				Input.placing = TileType.Tiled;
			}
		});
		
		tab1.add(new BuySlot(13, 221, 0, 0, null, Textures.get("dirt-path-tile"), null, 1, "Dirt Path", 10, this.getBoundsBuySlotSpace()) {
			@Override
			public void onDeclick(GameContainer gc) {
				Input.placing = TileType.DirtPath;
			}
		});
		
		tab1.add(new BuySlot(13, 301, 0, 0, null, Textures.get("dirt-tile"), null, 1, "Dirt", 10, this.getBoundsBuySlotSpace()) {
			@Override
			public void onDeclick(GameContainer gc) {
				Input.placing = TileType.Dirt;
			}
		});
	}
	
	@Override
	public void scroll(int value) {
		value*= Input.SCROLL_SPEED;
		
		if(scrollY + value >= -500 && scrollY + value <= 0) {
			for(BuySlot b : tab1) {
				b.y+= value;
			}
			
			scrollY += value;
		}
	}
	
	@Override
	public void update(GameContainer gc) {
		
		// TODO: rework this shit so it work for everything
		
	}
	
	@Override
	public Rectangle getBoundsTop() {
		return new Rectangle(x, y, w-24, 26);
	}
	
	public Rectangle getBoundsBuySlotSpace() {
		return new Rectangle(x, y+49, w, h-49);
	}
	
	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		if(open) {
			r.drawImageUI(Textures.get("path-menu"), x, y);
			
			Renderer.font = Font.DETAILED;
			
			r.drawText("Pathing Menu", x+59, y + 5, 1, 0xffffffff);
			
			Renderer.font = Font.STANDARD;
			
			r.drawRectUI(x, y+49, w, h-49, 0xffffff00, Light.NONE);
			
		}
	}
	
	@Override
	public void onOpen(GameContainer gc) {
		for(Button b : tab1) {
			AgeOfProgress.inst.uiManager.addButton(b);
		}
	}

	@Override
	public void onClose(GameContainer gc) {
		for(Button b : tab1) {
			AgeOfProgress.inst.uiManager.removeButton(b);
		}
	}
}
