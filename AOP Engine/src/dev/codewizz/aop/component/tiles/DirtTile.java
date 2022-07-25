package dev.codewizz.aop.component.tiles;

import java.awt.image.BufferedImage;

import dev.codewizz.aop.world.Cell;
import dev.codewizz.aop.world.TileID;

public class DirtTile extends PathTile {

	public DirtTile(int i, int j, Cell cell) {
		super(i, j, cell);
		
		this.id = TileID.Dirt;
		this.texturePath = "assets/textures/environment/dirttile.png";
	}
	
	@Override
	public void updateSprite() {
		super.updateSprite();
	}

	@Override
	public void update(float dt) {
		
	}

	@Override
	public BufferedImage getFullImage() {
		return null;
	}
}
