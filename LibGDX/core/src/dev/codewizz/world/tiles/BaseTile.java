package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;

public class BaseTile extends Tile {

	public BaseTile(Cell cell) {
		super(cell);

		this.texture = Assets.getSprite("base-tile");
	}

}
