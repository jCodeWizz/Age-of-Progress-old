package dev.codewizz.aop.component;

public class BaseTile extends Tile {

	public BaseTile(int i, int j) {
		super(i, j);
		
		this.texturePath = "assets/textures/environment/grasstile.png";
	}

	@Override
	public void update(float dt) {
		
	}
}
