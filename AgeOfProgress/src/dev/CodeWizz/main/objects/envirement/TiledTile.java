package dev.CodeWizz.main.objects.envirement;

import dev.CodeWizz.engine.util.Textures;

public class TiledTile extends PathTile {

	public TiledTile(int x, int y, Cell cell) {
		super(x, y, cell);
		
		this.type = TileType.Tiled;
		this.d = Textures.get("tiled-tile");
	}
}
