package dev.codewizz.gfx.gui.menus;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;

import dev.codewizz.gfx.gui.UIButton;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIMenu;
import dev.codewizz.main.Main;
import dev.codewizz.world.World;

public class MainMenu extends UIMenu {

	public MainMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h, layer);

	}

	@Override
	public void setup() {
		
		int w = Gdx.graphics.getWidth() - Gdx.graphics.getHeight();
		int startX = Gdx.graphics.getHeight();
		
		
		
		elements.add(new UIButton("start-button", startX + w/2 - (99*UILayer.SCALE)/2, Gdx.graphics.getHeight()/2 - (36/2)*UILayer.SCALE + 120*UILayer.SCALE, 99, 36, "Create World") {
			@Override
			protected void onDeClick() {
				Main.inst.openWorld(new World());
			}
		});
		
		elements.add(new UIButton("settings-button", startX + w/2 - (99*UILayer.SCALE)/2, Gdx.graphics.getHeight()/2 - (36/2)*UILayer.SCALE + 60*UILayer.SCALE, 99, 36, "Settings") {
			@Override
			protected void onDeClick() {
				layer.getElement("main-menu-menu").disable();
				layer.getElement("settings-menu-menu").enable();
			}
		});
		
		elements.add(new UIButton("quit-button", startX + w/2 - (99*UILayer.SCALE)/2, Gdx.graphics.getHeight()/2 - (36/2)*UILayer.SCALE, 99, 36, "Quit Game") {
			@Override
			protected void onDeClick() {
				Main.exit();
			}
		});
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 1920, 1080);
	}

	@Override
	public void onOpen() {
		
	}

	@Override
	public void onClose() {
		
	}
}
