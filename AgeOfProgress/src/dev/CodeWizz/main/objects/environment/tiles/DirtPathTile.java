package dev.CodeWizz.main.objects.environment.tiles;

import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.objects.environment.Cell;
import dev.CodeWizz.main.objects.environment.Tile;
import dev.CodeWizz.main.objects.environment.TileType;

public class DirtPathTile extends PathTile {

	public DirtPathTile(int x, int y, Cell cell) {
		super(x, y, cell);
		
		this.type = TileType.DirtPath;
		this.d = Textures.get("dirt-path-tile");
	}

	public static Tile getNew(int x, int y, Cell cell) {
		return new DirtPathTile(x, y, cell);
	}
}
