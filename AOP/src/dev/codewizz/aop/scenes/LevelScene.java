package dev.codewizz.aop.scenes;

import org.joml.Vector2f;

import dev.codewizz.aop.components.CameraComponent;
import dev.codewizz.aop.components.Controller;
import dev.codewizz.aop.components.Tile;
import dev.codewizz.aop.components.TileType;
import dev.codewizz.aop.world.World;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.scene.Scene;
import dev.codewizz.engine.util.AssetPool;

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

		GameObject object = new GameObject("Test", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)), -1).addComponent(new Controller()).addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/base-tile.png"))));
		this.addGameObjectToScene(object);
		
		super.init();
		
		
		
	}
}
