package dev.codewizz.aop.component.tiles;

import dev.codewizz.aop.world.Cell;
import dev.codewizz.aop.world.TileID;

public class BaseTile extends Tile {

	public BaseTile(int i, int j, Cell cell) {
		super(i, j, cell);
		
		this.id = TileID.Grass;
		this.texturePath = "assets/textures/environment/grasstile.png";
	}

	@Override
	public void update(float dt) {
		
	}
}
