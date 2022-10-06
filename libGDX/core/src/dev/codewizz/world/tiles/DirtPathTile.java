package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;

public class DirtPathTile extends Tile {

	public DirtPathTile(Cell cell) {
		super(cell);

		this.texture = Assets.getSprite("dirt-path-tile");
	}

}
