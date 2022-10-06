package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;

public class TiledTile extends Tile {

	public TiledTile(Cell cell) {
		super(cell);

		this.texture = Assets.getSprite("tiled-tile");
	}

}
