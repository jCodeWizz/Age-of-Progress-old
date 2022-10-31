package dev.codewizz.gfx.gui.menus;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;

import dev.codewizz.gfx.gui.UIButton;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIMenu;
import dev.codewizz.main.Main;

public class PauseMenu extends UIMenu{

	public PauseMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h, layer);
	}

	@Override
	public void setup() {
		elements.add(new UIButton("continue-button", Gdx.graphics.getWidth()/2 - (66/2)*UILayer.SCALE, Gdx.graphics.getHeight()/2 - (24/2)*UILayer.SCALE + 120*UILayer.SCALE, 66, 24, "Continue") {
			@Override
			protected void onDeClick() {
				close();
			}
		});
		
		elements.add(new UIButton("main-menu-button", Gdx.graphics.getWidth()/2 - (66/2)*UILayer.SCALE, Gdx.graphics.getHeight()/2 - (24/2)*UILayer.SCALE + 60*UILayer.SCALE, 66, 24, "Main Menu") {
			@Override
			protected void onDeClick() {
				Main.inst.closeWorld();
			}
		});
		
		elements.add(new UIButton("settings-button", Gdx.graphics.getWidth()/2 - (66/2)*UILayer.SCALE, Gdx.graphics.getHeight()/2 - (24/2)*UILayer.SCALE, 66, 24, "settings") {
			@Override
			protected void onDeClick() {
				layer.getElement("pauseMenu").disable();
				layer.getElement("settingsMenu").enable();
			}
		});
		
		elements.add(new UIButton("close-button", Gdx.graphics.getWidth()/2 - (66/2)*UILayer.SCALE, Gdx.graphics.getHeight()/2 - (24/2)*UILayer.SCALE - 60*UILayer.SCALE, 66, 24, "Quit") {
			@Override
			protected void onDeClick() {
				Main.exit();
			}
		});
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
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
