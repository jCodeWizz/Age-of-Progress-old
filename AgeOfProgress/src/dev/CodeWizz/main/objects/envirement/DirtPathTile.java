package dev.CodeWizz.main.objects.envirement;

import dev.CodeWizz.engine.util.Textures;

public class DirtPathTile extends PathTile {

	public DirtPathTile(int x, int y, Cell cell) {
		super(x, y, cell);
		
		this.type = TileType.DirtPath;
		this.d = Textures.get("dirt-path-tile");
	}
}
