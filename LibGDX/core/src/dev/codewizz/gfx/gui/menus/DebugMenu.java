package dev.codewizz.gfx.gui.menus;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIMenu;
import dev.codewizz.gfx.gui.UIText;
import dev.codewizz.main.Main;
import dev.codewizz.world.Nature;

public class DebugMenu extends UIMenu {

	UIText nature;
	UIText settlement;
	UIText rendering;
	
	public DebugMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h, layer);

	}

	@Override
	public void setup() {
		
		elements.add(new UIText("debug-text", 20, UILayer.HEIGHT - 20, 0, 0, "DEBUG MODE", 10));

		rendering = new UIText("debug-text-rendering", 20, UILayer.HEIGHT - 60, 0, 0, "", 8);
		elements.add(rendering);
		
		nature = new UIText("debug-text-nature", 20, UILayer.HEIGHT - 85, 0, 0, "", 8);
		elements.add(nature);
		
		settlement = new UIText("debug-text-settlement", 20, UILayer.HEIGHT - 110, 0, 0, "", 8);
		elements.add(settlement);
	}
	
	public void updateData() {
		
		rendering.setText("Rendering>> FPS: " + Gdx.graphics.getFramesPerSecond());
		
		nature.setText("Nature>> Timer: " + (int)Main.inst.world.nature.counter + " | Cap: " + Main.inst.world.nature.animals.size() + " / " + Nature.ANIMAL_CAP);
		
		if(Main.inst.world.settlement != null) {
			settlement.setText("Settlement>> Loc: { " + (int)Main.inst.world.settlement.getX() + " ; " + (int)Main.inst.world.settlement.getY() + " } | Size: " + Main.inst.world.settlement.members.size());
		} else {
			settlement.setText("Settlement>> No Settlement");
		}
	}
	
	@Override
	public void render(SpriteBatch b) {
		updateData();
	}
	
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
	}

	@Override
	public void onOpen() {
		
	}

	@Override
	public void onClose() {
		this.enable();
	}
	

}
