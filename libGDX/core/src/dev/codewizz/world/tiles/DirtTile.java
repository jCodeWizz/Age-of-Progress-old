package dev.codewizz.world.tiles;

import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;
import dev.codewizz.world.TileType;

public class DirtTile extends Tile {

	public DirtTile(Cell cell) {
		super(cell);

		this.type = TileType.Dirt;
		this.texture = Assets.getSprite("dirt-tile");
	
		this.name = "Dirt Tile";
		
		this.cost = 5;
	}

}
