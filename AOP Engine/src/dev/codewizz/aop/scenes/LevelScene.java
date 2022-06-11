package dev.codewizz.aop.scenes;

import dev.codewizz.aop.components.TileType;
import dev.codewizz.aop.world.World;
import dev.codewizz.engine.scene.Scene;

public class LevelScene extends Scene {

	private World world;
	
	public LevelScene() {
		world = new World();
	}
	
	@Override
	public void update(float dt) {

		super.update(dt);
	}

	@Override
	public void init() {
		super.init();
		
		world.setTile(TileType.GrassTile);
	}
}
