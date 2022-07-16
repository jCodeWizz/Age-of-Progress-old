package dev.codewizz.aop.component;

import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.object.component.SpriteRenderer;
import dev.codewizz.engine.renderer.Sprite;
import dev.codewizz.engine.util.AssetPool;

public abstract class Tile extends Component {

	public static final int WIDTH = 64, HEIGHT = 48;
	
	protected String texturePath;
	protected int indexX, indexY;
	
	public Tile(int i, int j) {
		this.indexX = i;
		this.indexY = j;
	}
	
	
	@Override
	public void start() {
		updateSprite();
	}
	
	public void updateSprite() {
		this.gameObject.getComponent(SpriteRenderer.class).setSprite(new Sprite(AssetPool.getTexture(texturePath)));
	}
	
	public String getTexturePath() {
		return this.texturePath;
	}
	
}
