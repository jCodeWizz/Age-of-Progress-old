package dev.codewizz.aop.components.tiles;

import dev.codewizz.aop.components.Tile;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.util.AssetPool;

public class GrassTile extends Tile {

	
	public GrassTile() {
		this.sprite = new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/base-tile.png"));
	}
	
	@Override
	public void update(float dt) {

	}
}
