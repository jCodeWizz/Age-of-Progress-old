package dev.codewizz.aop.component;

public class EmptyTile extends Tile {

	public EmptyTile(int i, int j) {
		super(i, j);
		
		this.texturePath = "assets/textures/environment/empty-tile.png";
	}

	@Override
	public void update(float dt) {
		
	}
}
