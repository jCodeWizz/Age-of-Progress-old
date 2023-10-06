package dev.codewizz.gfx.gui.menus;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.gfx.gui.UIIcon;
import dev.codewizz.gfx.gui.UIImage;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIMenu;
import dev.codewizz.gfx.gui.UIText;
import dev.codewizz.gfx.gui.layers.GameLayer;
import dev.codewizz.world.objects.Animal;
import dev.codewizz.world.objects.Entity;
import dev.codewizz.world.objects.Hermit;

public class SelectMenu extends UIMenu {

	public Entity entity;
	
	private UIText nameText;
	private UIText healthText;
	private UIText descriptionText;
	
	
	public SelectMenu(String id, int x, int y, int w, int h, UILayer layer) {
		super(id, x, y, w, h, layer);
	}

	@Override
	public void setup() {
		
		nameText = new UIText("name", (6 + 3) * UILayer.SCALE,  (6+98) * UILayer.SCALE, 10, 10, "", 8);
		descriptionText = new UIText("description", (6 + 3) * UILayer.SCALE,  (6+40) * UILayer.SCALE, 10, 10, "", 8);
		healthText = new UIText("health", (6 + 3) * UILayer.SCALE,  (6 + 8) * UILayer.SCALE, 10, 10, "", 8);

		
		
		elements.add(nameText);
		elements.add(healthText);
		elements.add(descriptionText);
		
		
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
	
	public void updateData() {
		nameText.setText("" + entity.getName());
		healthText.setText((int)entity.getHealth() + " / " + (int)entity.getMaxHealth() + " HP");
		
		descriptionText.setText("Idle");
		
		if(entity instanceof Animal) {
			Animal a = (Animal) entity;
			if(a.getCurrentTask() != null) {
				descriptionText.setText(a.getCurrentTask().getName());
			} 
		} else if (entity instanceof Hermit) {
			Hermit a = (Hermit) entity;
			if(a.getCurrentTask() != null) {
				descriptionText.setText(a.getCurrentTask().getName());
			} 
		}
	}

	@Override
	public void onOpen() {
		entity = GameLayer.selectedEntity;
		updateData();
	}

	@Override
	public void onClose() {
		entity = null;
	}
	
	@Override
	public void render(SpriteBatch b) {
		updateData();
	}
}
