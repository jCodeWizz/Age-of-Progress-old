package dev.codewizz.aop.component;

public class DirtTile extends Tile {

	public DirtTile(int i, int j) {
		super(i, j);
		
		this.texturePath = "assets/textures/environment/dirttile.png";
	}

	@Override
	public void update(float dt) {
		
	}
}
