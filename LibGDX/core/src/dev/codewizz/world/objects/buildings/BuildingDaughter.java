package dev.codewizz.world.objects.buildings;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.world.GameObject;

public class BuildingDaughter extends GameObject {

	/*
	 * This will be a placeholder
	 * So that other cells know that
	 * they are taken up by a multi-cell building
	 */
	
	private Building mother;
	
	public BuildingDaughter(float x, float y, Building mother) {
		super(x, y);
		
		this.mother = mother;
	}
	
	public Building getMother() {
		return mother;
	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public void render(SpriteBatch b) {
		
	}
}
