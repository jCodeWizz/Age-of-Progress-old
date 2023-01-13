package dev.codewizz.world.objects.buildings;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.world.objects.Hermit;

public class SmallHouse extends Building {

	private Sprite sprite;
	private ArrayList<Hermit> hermitsInside;
	
	public SmallHouse(float x, float y) {
		super(x, y);

		hermitsInside = new ArrayList<>();
	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public void render(SpriteBatch b) {
		
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
	}
}
