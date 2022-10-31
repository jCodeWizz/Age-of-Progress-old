package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import dev.codewizz.gfx.gui.UIBuyslot;
import dev.codewizz.gfx.gui.UIIcon;
import dev.codewizz.gfx.gui.UIImage;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIMenu;
import dev.codewizz.gfx.gui.UIScrollList;
import dev.codewizz.gfx.gui.UIText;
import dev.codewizz.world.tiles.BaseTile;
import dev.codewizz.world.tiles.DirtPathTile;
import dev.codewizz.world.tiles.DirtTile;
import dev.codewizz.world.tiles.EmptyTile;
import dev.codewizz.world.tiles.TiledTile;

public class PathingMenu extends UIMenu {

	private UIScrollList list;
	
	public PathingMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h, layer);
	}

	@Override
	public void setup() {
		list = new UIScrollList("list", x, y, w, h);
		elements.add(list);
		
		list.slotPort = new Rectangle((8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (329 * UILayer.SCALE), 160 * UILayer.SCALE, 291 * UILayer.SCALE);

		elements.add(new UIIcon("close-button", (6 + 160 - 13 - 1) * UILayer.SCALE - 1,
				Gdx.graphics.getHeight() - (7 + 15) * UILayer.SCALE + 1, 14, 15, "close-icon") {
			@Override
			protected void onDeClick() {
				close();
			}
		});
		elements.add(new UIText("text", (6 + 6) * UILayer.SCALE, Gdx.graphics.getHeight() - (6 + 5) * UILayer.SCALE + 1,
				10, 10, "Pathing Menu", 8));

		list.slots.add(new UIBuyslot("slot", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (90 * UILayer.SCALE),
				160 - 8, 52, new BaseTile(null)));
		list.slots.add(new UIBuyslot("slot-2", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (140 * UILayer.SCALE),
				160 - 8, 52, new TiledTile(null)));
		list.slots.add(new UIBuyslot("slot-3", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (190 * UILayer.SCALE),
				160 - 8, 52, new DirtPathTile(null)));
		list.slots.add(new UIBuyslot("slot-4", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (240 * UILayer.SCALE),
				160 - 8, 52, new DirtTile(null)));
		list.slots.add(new UIBuyslot("slot-5", (8) * UILayer.SCALE + 4, Gdx.graphics.getHeight() - (290 * UILayer.SCALE),
				160 - 8, 52, new EmptyTile(null)));

		list.maxScroll = (list.slots.size()) * 52;

		elements.addAll(list.slots);

		elements.add(new UIImage("background", 6 * UILayer.SCALE, Gdx.graphics.getHeight() - (331 * UILayer.SCALE), 160,
				325, "path-menu"));
	}

	@Override
	public java.awt.Rectangle getBounds() {
		return new java.awt.Rectangle(6 * UILayer.SCALE, Gdx.graphics.getHeight() - (331 * UILayer.SCALE),
				160 * UILayer.SCALE, 325 * UILayer.SCALE);
	}

	@Override
	public void onOpen() {
	}

	@Override
	public void onClose() {
	}
}
