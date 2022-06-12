package dev.codewizz.aop.components.tiles;

import dev.codewizz.aop.components.Tile;
import dev.codewizz.aop.components.TileType;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.util.AssetPool;

public class EmptyTile extends Tile {

	public EmptyTile(int cellX, int cellY) {
		super(cellX, cellY);
		
		this.sprite = new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/empty-tile.png"));
		this.type = TileType.EmptyTile;
	}

	@Override
	public void start() {
		
	}

	@Override
	public void update(float dt) {
		
	}
}
