package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;

public class DirtTile extends Tile {

	public DirtTile(Cell cell) {
		super(cell);

		this.texture = Assets.getSprite("dirt-tile");
	}

}
