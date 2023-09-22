package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;

public interface IBuy {

	public Sprite getMenuSprite();
	public String getMenuName();
	public String getMenuDescription();
	public GameObject getCopy(float x, float y);
	public boolean conintues();
	public boolean available();
	public void onPlace(Cell cell);
	
}
