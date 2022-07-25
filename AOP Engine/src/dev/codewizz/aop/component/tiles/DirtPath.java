package dev.codewizz.aop.component.tiles;

import java.awt.image.BufferedImage;

import dev.codewizz.aop.world.Cell;
import dev.codewizz.aop.world.TileID;
import dev.codewizz.engine.util.AssetPool;

public class DirtPath extends PathTile {

	public DirtPath(int i, int j, Cell cell) {
		super(i, j, cell);

		this.id = TileID.DirtPath;
		this.texturePath = "assets/textures/environment/grasstile.png";
	}

	@Override
	public BufferedImage getFullImage() {
		return AssetPool.getBufferedImage("assets/textures/environment/dirt-path-tile.png");
	}
}
