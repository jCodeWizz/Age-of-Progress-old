package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.gfx.gui.UIText;
import dev.codewizz.gfx.gui.layers.GameLayer;
import dev.codewizz.main.Main;
import dev.codewizz.world.GameObject;

public abstract class Entity extends GameObject {

	protected boolean selected = false;
	protected String name = "Object";
	protected float health = 10f, damageCoolDown = 0.0f;
	
	public Entity(float x, float y) {
		super(x, y);
		
	}
	
	public abstract void renderUICard(SpriteBatch b);
	
	public void select() {
		GameLayer.selectedEntity = this;
		Main.inst.renderer.ui.getElement("selectMenu").enable();
		UIText nameText = (UIText) Main.inst.renderer.ui.getElement("name");
		nameText.setText(name);
		selected = true;
	}
	
	public void deselect() {
		GameLayer.selectedEntity = null;
		Main.inst.renderer.ui.getElement("selectMenu").disable();
		selected = false;
	}
	
	@Override
	public void update(float d) {
		if(damageCoolDown >= 0f) {
			damageCoolDown -= 1 * Gdx.graphics.getDeltaTime();
		}
	}
	
	public void damage(float damage) {
		if(damageCoolDown <= 0f) {
			health -= damage;
			damageCoolDown = 0.2f;
			
			if(health <= 0f)
				destroy();
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
}
