package dev.codewizz.aop.component.tiles;

import dev.codewizz.aop.world.Cell;
import dev.codewizz.aop.world.TileID;

public class EmptyTile extends Tile {

	public EmptyTile(int i, int j, Cell cell) {
		super(i, j, cell);
		
		
		this.id = TileID.Empty;
		this.texturePath = "assets/textures/environment/empty-tile.png";
	}

	@Override
	public void update(float dt) {
		
	}
}
