package dev.CodeWizz.main.ui;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.objects.environment.World;
import dev.CodeWizz.main.ui.menu.Menu;
import dev.CodeWizz.main.ui.menu.PathMenu;

public class UIManager {

	private Menu pathMenu;
	
	private List<Menu> menus = new CopyOnWriteArrayList<>();
	private List<Button> buttons = new CopyOnWriteArrayList<>();
	private MousePreview mousepreview;
	public static boolean mouseOnUI;
	
	public UIManager() {
		mousepreview = new MousePreview();
		
		pathMenu = new PathMenu();
		menus.add(pathMenu);
	}
	
	public void init(GameContainer gc) {
		buttons.add(new Button(gc.getWidth()/2-134, gc.getHeight()-60, 22, 24, Textures.get("icon-button"), Textures.get("icon-button-pressed"), Textures.get("manage-icon"), 2));
		buttons.add(new Button(gc.getWidth()/2-78, gc.getHeight()-60, 22, 24, Textures.get("icon-button"), Textures.get("icon-button-pressed"), Textures.get("build-icon"), 2));
		buttons.add(new Button(gc.getWidth()/2-22, gc.getHeight()-60, 22, 24, Textures.get("icon-button"), Textures.get("icon-button-pressed"), Textures.get("path-icon"), 2) {
			
			@Override
			public void onDeclick(GameContainer gc) {
				if(!pathMenu.isOpen()) {
					pathMenu.open(gc);
				}
			}
		} );
		buttons.add(new Button(gc.getWidth()/2+34, gc.getHeight()-60, 22, 24, Textures.get("icon-button"), Textures.get("icon-button-pressed"), Textures.get("people-icon"), 2));
		buttons.add(new Button(gc.getWidth()/2+90, gc.getHeight()-60, 22, 24, Textures.get("icon-button"), Textures.get("icon-button-pressed"), Textures.get("tool-icon"), 2));
	
	
	
		for(Menu menu : menus) {
			menu.init(gc);
		}
	}
	
	public void update(GameContainer gc, World world) {
		mouseOnUI = new Rectangle(gc.getWidth()/2-160, gc.getHeight()-75, 320, 75).contains(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY());

		for(Menu menu : menus) {
			menu.update(gc);
		}
		
		mousepreview.update(gc, world, this);
	}
	
	public void render(GameContainer gc, Renderer r, World world) {
		mousepreview.render(gc, r, world, this);
	}
	
	public void renderUI(GameContainer gc, Renderer r, World world) {
		r.drawImageUI(Textures.get("icon-board"), gc.getWidth()/2-146, gc.getHeight()-60, 2);
		
		for(Menu menu : menus) {
			menu.renderUI(gc, r);
		}
		
		
		for(Button b : buttons) {
			b.renderUI(gc, r);
		}
	}
	
	public List<Menu> getMenus() {
		return menus;
	}
	
	public List<Button> getButtons() {
		return buttons;
	}
	
	public void addButton(Button button) {
		buttons.add(button);
	}
	
	public void removeButton(Button button) {
		buttons.remove(button);
	}
}
