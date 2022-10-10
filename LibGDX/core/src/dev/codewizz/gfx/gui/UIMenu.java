package dev.codewizz.gfx.gui;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import dev.codewizz.world.tiles.BaseTile;
import dev.codewizz.world.tiles.DirtPathTile;
import dev.codewizz.world.tiles.DirtTile;
import dev.codewizz.world.tiles.TiledTile;

public class UIMenu extends UIElement {

	public List<UIElement> elements = new CopyOnWriteArrayList<>();
	public UILayer layer;
	
	public List<UIBuyslot> slots = new CopyOnWriteArrayList<>();
	public Rectangle slotPort;
	
	public UIMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h);
		
		slotPort = new Rectangle( (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (326 * UILayer.SCALE), 160*UILayer.SCALE, 700 * UILayer.SCALE);
		
		this.layer = layer;
		
		elements.add(new UIIcon("close-button", (6 + 160 - 13 - 1) * UILayer.SCALE - 1, Gdx.graphics.getHeight() - (7 + 15) * UILayer.SCALE + 1, 14, 15, "close-icon") {
			@Override
			protected void onDeClick() {
				close();
			}
		});
		elements.add(new UIText("text", (6 + 6) * UILayer.SCALE, Gdx.graphics.getHeight() - (6 + 5) * UILayer.SCALE + 1, 10, 10, "Pathing Menu", 8));
		
		this.wantsClick = false;
		
		slots.add(new UIBuyslot("slot", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (90 * UILayer.SCALE), 160 - 8, 52, new BaseTile(null)));
		slots.add(new UIBuyslot("slot-2", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (140 * UILayer.SCALE), 160 - 8, 52, new TiledTile(null)));
		slots.add(new UIBuyslot("slot-3", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (190 * UILayer.SCALE), 160 - 8, 52, new DirtPathTile(null)));
		slots.add(new UIBuyslot("slot-4", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (240 * UILayer.SCALE), 160 - 8, 52, new DirtTile(null)));
		slots.add(new UIBuyslot("slot-5", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (290 * UILayer.SCALE), 160 - 8, 52, new BaseTile(null)));
		slots.add(new UIBuyslot("slot-6", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (340 * UILayer.SCALE), 160 - 8, 52, new BaseTile(null)));
		slots.add(new UIBuyslot("slot-7", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (390 * UILayer.SCALE), 160 - 8, 52, new BaseTile(null)));
		slots.add(new UIBuyslot("slot-8", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (440 * UILayer.SCALE), 160 - 8, 52, new BaseTile(null)));

		
		elements.addAll(slots);
		
		elements.add(new UIImage("background", 6 * UILayer.SCALE, Gdx.graphics.getHeight() - (331 * UILayer.SCALE), 160, 325, "path-menu"));
	}
	
	public void close() {
		disable();
	}
	
	public Rectangle getSlotArea() {
		return slotPort;
	}
	
	@Override
	protected void onEnable() {
		for(UIElement e : elements) {
			layer.elements.add(e);
		}
	}
	
	@Override
	protected void onDisable() {
		for(UIElement e : elements) {
			layer.elements.remove(e);
		}
	}
	
	@Override
	public void render(SpriteBatch b) {
	}
}
