package dev.codewizz.aop.components;

import dev.codewizz.engine.gameobject.Component;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;

public abstract class Tile extends Component {

	public static final int WIDTH = 64, HEIGHT = 48;
	
	protected Sprite sprite;
	protected TileType type;
	protected int cellX, cellY;
	
	public Tile(int cellX, int cellY) {
		this.cellX = cellX;
		this.cellY = cellY;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
		SpriteRenderer r = this.gameObject.getComponent(SpriteRenderer.class);
		if(r != null)
			r.setSprite(s);
	}
	
	public void onPlace() {
		
	}

	public TileType getType() {
		return type;
	}

	public int getCellX() {
		return cellX;
	}

	public int getCellY() {
		return cellY;
	}
	
}
