package dev.codewizz.aop.scenes;

import dev.codewizz.aop.components.CameraComponent;
import dev.codewizz.aop.components.Tile;
import dev.codewizz.aop.components.TileType;
import dev.codewizz.aop.world.World;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.scene.Scene;

public class LevelScene extends Scene {

	private GameObject worldObject;
	private GameObject cameraObject;
	
	public LevelScene() {
	}
	
	@Override
	public void update(float dt) {
		if(MouseListener.mouseButtonDown(0)) {
			worldObject.getComponent(World.class).grid[0][0].setTile(TileType.DirtTile);

			World world = worldObject.getComponent(World.class);

			for(int i = 0; i < world.grid.length; i++) {
				for(int j = 0; j < world.grid[i].length; j++) {
					if(world.grid[i][j].getBounds().contains(MouseListener.getOrthoX(), MouseListener.getOrthoY())) {
						System.out.println(world.grid[i][j].getTile().getComponent(Tile.class).getType());
						world.grid[i][j].setTile(TileType.EmptyTile);
					}
				}
			}
		}
		super.update(dt);
	}

	@Override
	public void init() {
		

		worldObject = new GameObject("World");
		worldObject.addComponent(new World());
		this.addGameObjectToScene(worldObject);
		
		cameraObject = new GameObject("Camera");
		cameraObject.addComponent(new CameraComponent());
		this.addGameObjectToScene(cameraObject);
		
		super.init();
		
		
		
	}
}
