package dev.codewizz.aop.components;

import dev.codewizz.engine.gameobject.Component;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;

public abstract class Tile extends Component {

	protected Sprite sprite;
	protected TileType type;
	
	public Tile() {
		
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
}
