package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.TileType;

public class TiledTile extends PathTile {

	public TiledTile(Cell cell) {
		super(cell);

		this.type = TileType.Tiled;
		this.texture = Assets.getSprite("tiled-tile");
		this.template = Assets.getSprite("tiled-tile");
		this.templateGround = Assets.getSprite("base-tile");
	}

}
