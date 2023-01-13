package dev.codewizz.gfx.gui.menus;

import java.awt.Rectangle;

import dev.codewizz.gfx.gui.UIIcon;
import dev.codewizz.gfx.gui.UIImage;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIMenu;
import dev.codewizz.gfx.gui.UIText;

public class SelectMenu extends UIMenu {

	public SelectMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h, layer);
	}

	@Override
	public void setup() {
		
		elements.add(new UIText("name", (6 + 3) * UILayer.SCALE,  (6+98) * UILayer.SCALE, 10, 10, "Name", 8));
		elements.add(new UIText("health", (6 + 3) * UILayer.SCALE,  (6+50) * UILayer.SCALE, 10, 10, "Health", 8));
		elements.add(new UIText("description", (6 + 3) * UILayer.SCALE,  (6+40) * UILayer.SCALE, 10, 10, "Task: cutting down tree", 4));

		
		elements.add(new UIIcon("close-button", (6 + 142) * UILayer.SCALE - 1, (6+87) * UILayer.SCALE + 1, 14, 15,
				"close-icon") {
			@Override
			protected void onDeClick() {
				close();
			}
		});

		elements.add(new UIImage("selected-background", x, y, w, h, "selected-background"));
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x * UILayer.SCALE, y * UILayer.SCALE, w * UILayer.SCALE, h * UILayer.SCALE);
	}

	@Override
	public void onOpen() {

	}

	@Override
	public void onClose() {

	}
}
