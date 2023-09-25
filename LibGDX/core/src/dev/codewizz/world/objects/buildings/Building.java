package dev.codewizz.world.objects.buildings;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.IBuy;
import dev.codewizz.world.objects.ID;

public class Building extends GameObject implements IBuy {
	
	private static Sprite texture = Assets.getSprite("building");
	private static Sprite icon = Assets.getSprite("building");

	public Building(float x, float y) {
		super(x, y);

		this.id = ID.Building;
		
	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x, y);
	}

	@Override
	public Sprite getMenuSprite() {
		return icon;
	}

	@Override
	public String getMenuName() {
		return "Building";
	}

	@Override
	public String getMenuDescription() {
		return "A nice house.";
	}

	@Override
	public GameObject getCopy(float x, float y) {
		return new Building(x, y);
	}

	@Override
	public boolean conintues() {
		return false;
	}

	@Override
	public boolean available() {
		return true;
	}

	@Override
	public void onPlace(Cell cell) {
		
	}
}
