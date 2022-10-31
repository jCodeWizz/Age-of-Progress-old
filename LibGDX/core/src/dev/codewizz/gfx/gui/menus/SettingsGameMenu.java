package dev.codewizz.gfx.gui.menus;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;

import dev.codewizz.gfx.gui.UIButton;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIMenu;
import dev.codewizz.main.Main;

public class SettingsGameMenu extends UIMenu {

	public SettingsGameMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h, layer);

	}

	@Override
	public void setup() {
		elements.add(new UIButton("back-button", Gdx.graphics.getWidth()-(66+5)*UILayer.SCALE, 5*UILayer.SCALE, 66, 24, "Back") {
			@Override
			protected void onDeClick() {
				layer.getElement("settingsMenu").disable();
				layer.getElement("pauseMenu").enable();
			}
		});
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 1920, 1080);
	}

	@Override
	public void onOpen() {
		Main.PAUSED = true;
		UILayer.FADE = true;
		layer.getElement("build-icon").setAvailable(false);
		layer.getElement("tool-icon").setAvailable(false);
		layer.getElement("people-icon").setAvailable(false);
		layer.getElement("manage-icon").setAvailable(false);
		layer.getElement("path-icon").setAvailable(false);
	}

	@Override
	public void onClose() {
		Main.PAUSED = false;
		UILayer.FADE = false;
		layer.getElement("build-icon").setAvailable(true);
		layer.getElement("tool-icon").setAvailable(true);
		layer.getElement("people-icon").setAvailable(true);
		layer.getElement("manage-icon").setAvailable(true);
		layer.getElement("path-icon").setAvailable(true);
	}
}
