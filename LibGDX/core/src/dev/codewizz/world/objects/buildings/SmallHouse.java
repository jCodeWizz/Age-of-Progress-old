package dev.codewizz.world.objects.buildings;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.world.objects.Hermit;

public class SmallHouse extends Building {

	private static Sprite sprite;
	private ArrayList<Hermit> hermitsInside;
	
	public SmallHouse(float x, float y) {
		super(x, y);

		hermitsInside = new ArrayList<>();
	}

	@Override
	public void update(float d) {
		
	}
	
	public List<Hermit> getHermitsInside() {
		return hermitsInside;
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(sprite, x, y, w ,h);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
	}
}
