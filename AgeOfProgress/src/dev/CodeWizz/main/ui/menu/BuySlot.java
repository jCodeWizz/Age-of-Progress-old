package dev.CodeWizz.main.ui.menu;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Font;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.ui.Button;

public class BuySlot extends Button {

	private static Image tileBackground = Textures.get("tile-background-buyslot");
	
	private String title;
	private int cost;
	private Rectangle space;
	private Image tile;

	public BuySlot(int x, int y, int w, int h, Image background, Image tile, Image c, int scale, String title,
			int cost, Rectangle space) {
		super(x, y, w, h, background, tile, c, scale);

		this.tile = tile;
		
		this.w = tileBackground.getW();
		this.h = tileBackground.getH();
		
		this.title = title;
		this.cost = cost;
		
		this.space = space;
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		if (activated) {
			if (pressed) {
			} else {
				r.drawImageUI(tileBackground, x, y, space);
				r.drawImageUI(tile, x+5, y-5-tile.getH()*scale+tileBackground.getH(), space);
				
				Renderer.font = Font.DETAILED;
				
				r.drawText(title, x+w-5-r.getFont().getWidth(title), y+32);
				r.fillRectUI(x-10+w-r.getFont().getWidth(title), y+32+17, r.getFont().getWidth(title)+10, 2, 0xff2f1d1d, Light.NONE);
				
				r.drawText("" + cost, x+w-5-r.getFont().getWidth(title), y+55);
				r.fillRectUI(x-10+w-r.getFont().getWidth(title), y+55+17, r.getFont().getWidth(title)+10, 2, 0xff2f1d1d, Light.NONE);
			}
		} else {
		}
	}

	@Override
	public void unpress(GameContainer gc, boolean wasOnIt) {
		if (pressed && wasOnIt && canBuy()) {
			deductResources();
			onDeclick(gc);
		}

		pressed = false;
	}

	private boolean canBuy() {
		return true;
	}

	private void deductResources() {
	}
}
