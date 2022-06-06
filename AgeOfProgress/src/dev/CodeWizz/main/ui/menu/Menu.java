package dev.CodeWizz.main.ui.menu;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.hud.HudManager;
import dev.CodeWizz.engine.hud.IHudComponent;
import dev.CodeWizz.main.AgeOfProgress;
import dev.CodeWizz.main.ui.Button;

public abstract class Menu {
	
	protected MenuID id;
	protected int x, y, w, h;
	protected Button[] buttons;
	protected List<IHudComponent> components = new CopyOnWriteArrayList<>();
	protected boolean open;
	
	public Menu() {
		
	}
	
	public void update(GameContainer gc) {
		
	}
	
	public void renderUI(GameContainer gc, Renderer r) {
		
	}
	
	public void open(GameContainer gc) {
		HudManager.comps.addAll(components);

		for(int i = 0; i < buttons.length; i++) {
			AgeOfProgress.inst.uiManager.addButton(buttons[i]);
		}
		
		open = true;
		
		onOpen(gc);
	}
	
	public void close(GameContainer gc) {
		HudManager.comps.removeAll(components);
		for(int i = 0; i < buttons.length; i++) {
			AgeOfProgress.inst.uiManager.removeButton(buttons[i]);
		}
		
		open = false;
		
		onClose(gc);
	}
	
	public MenuID getId() {
		return id;
	}

	public void setId(MenuID id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Button[] getButtons() {
		return buttons;
	}

	public void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}

	public List<IHudComponent> getComponents() {
		return components;
	}

	public void setComponents(List<IHudComponent> components) {
		this.components = components;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public abstract void onOpen(GameContainer gc);
	
	public abstract void onClose(GameContainer gc);
	
	
}
