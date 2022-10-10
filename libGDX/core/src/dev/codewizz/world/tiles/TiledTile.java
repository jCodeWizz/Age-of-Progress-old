package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.TileType;

public class TiledTile extends PathTile {

	public TiledTile(Cell cell) {
		super(cell);

		this.type = TileType.Tiled;
		this.texture = Assets.getSprite("tiled-tile");
		this.template = TileType.Tiled;
		this.templateGround = TileType.Base;
		
		this.name = "Tiled Path Tile";
	}

}
