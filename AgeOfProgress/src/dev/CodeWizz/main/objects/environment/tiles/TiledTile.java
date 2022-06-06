package dev.CodeWizz.main.objects.environment.tiles;

import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.objects.environment.Cell;
import dev.CodeWizz.main.objects.environment.Tile;
import dev.CodeWizz.main.objects.environment.TileType;

public class TiledTile extends PathTile {

	public TiledTile(int x, int y, Cell cell) {
		super(x, y, cell);
		
		this.type = TileType.Tiled;
		this.d = Textures.get("tiled-tile");
	}

	public static Tile getNew(int x, int y, Cell cell) {
		return new TiledTile(x, y, cell);
	}
}
