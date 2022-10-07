package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;
import dev.codewizz.world.TileType;

public class DirtPathTile extends Tile {

	public DirtPathTile(Cell cell) {
		super(cell);

		this.type = TileType.DirtPath;
		this.texture = Assets.getSprite("dirt-path-tile");
	}

}
