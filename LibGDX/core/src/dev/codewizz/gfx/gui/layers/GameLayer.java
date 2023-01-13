package dev.codewizz.gfx.gui.layers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.gfx.gui.UIElement;
import dev.codewizz.gfx.gui.UIIcon;
import dev.codewizz.gfx.gui.UIImage;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.menus.PathingMenu;
import dev.codewizz.gfx.gui.menus.PauseMenu;
import dev.codewizz.gfx.gui.menus.SelectMenu;
import dev.codewizz.gfx.gui.menus.SettingsGameMenu;
import dev.codewizz.main.Main;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Entity;

public class GameLayer extends UILayer {

	private PathingMenu pathingMenu;
	private PauseMenu pauseMenu;
	private SettingsGameMenu settingsMenu;
	private SelectMenu selectMenu;
	
	public static Entity selectedEntity = null;

	@Override
	public void setup() {
		// MANAGE ICON
		elements.add(new UIIcon("manage-icon", (WIDTH / 2) - (134 * SCALE) / 2, 6 * SCALE, 22, 24, "manage-icon"));

		// PATH ICON
		elements.add(new UIIcon("path-icon", (WIDTH / 2) - (78 * SCALE) / 2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "path-icon") {
			@Override
			protected void onDeClick() {
				if (!pauseMenu.isEnabled()) {
					UIElement e = pathingMenu;
					if (e.isEnabled())
						e.disable();
					else
						e.enable();
				}
			}
		});

		// BUILD ICON
		elements.add(new UIIcon("build-icon", (WIDTH / 2) - (22 * SCALE) / 2, 6 * SCALE, 22, 24, "build-icon") {
			@Override
			protected void onDeClick() {
				Main.inst.openWorld(new World());
			}
		});

		// PEOPLE ICON
		elements.add(new UIIcon("people-icon", (WIDTH / 2) - (-34 * SCALE) / 2, 6 * SCALE, 22, 24, "people-icon"));

		// TOOL ICON
		elements.add(new UIIcon("tool-icon", (WIDTH / 2) - (-90 * SCALE) / 2, 6 * SCALE, 22, 24, "icon", "icon-pressed", "icon-unavailable", "tool-icon"));

		// BACKGROUND
		elements.add(new UIImage("icon-background", (WIDTH / 2) - (146 * SCALE) / 2, 0, 146, 30, "icon-board"));

		// PATH MENU
		pathingMenu = new PathingMenu("pathingMenu", 0, 0, 128, 260, this);
		pathingMenu.disable();
		elements.add(pathingMenu);

		// PAUSE MENU
		pauseMenu = new PauseMenu("pauseMenu", 0, 0, 100, 100, this);
		pauseMenu.disable();
		elements.add(pauseMenu);
		
		// SETTINGS MENU
		settingsMenu = new SettingsGameMenu("settingsMenu", 0, 0, 100, 100, this);
		settingsMenu.disable();
		elements.add(settingsMenu);
		
		// SELECTMENU
		selectMenu = new SelectMenu("selectMenu", 6, 6, 160, 107, this);
		selectMenu.disable();
		elements.add(selectMenu);
	}
	
	@Override
	public void render(SpriteBatch b) {
		if(selectedEntity != null) {
			selectedEntity.renderUICard(b);
		}
		super.render(b);
	}
}
