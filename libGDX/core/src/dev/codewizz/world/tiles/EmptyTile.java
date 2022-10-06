package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;

public class EmptyTile extends Tile {

	public EmptyTile(Cell cell) {
		super(cell);

		this.texture = Assets.getSprite("empty-tile");
	}

}
