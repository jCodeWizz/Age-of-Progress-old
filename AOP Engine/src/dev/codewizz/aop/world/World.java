package dev.codewizz.aop.world;

import org.joml.Vector2f;

import dev.codewizz.aop.components.Tile;
import dev.codewizz.aop.components.TileType;
import dev.codewizz.aop.components.tiles.GrassTile;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.Component;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.util.AssetPool;

public class World extends Component {
	
	public static final int WIDTH = 64, HEIGHT = 64;
	
	public GameObject[][] grid;
	
	public World() {
		grid = new GameObject[WIDTH][HEIGHT];
	}
	
	@Override
	public void start() {
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				grid[i][j] = setTile(i * Tile.WIDTH, j * Tile.HEIGHT, i, j, TileType.GrassTile);
			}
		}
	}
	
	public GameObject setTile(float x, float y, int cellX, int cellY, TileType tile) {
		if(tile == TileType.GrassTile) {
			GameObject object = new GameObject("GrassTile", new Transform(new Vector2f(x, y), new Vector2f(Tile.WIDTH, Tile.HEIGHT)));
			object.addComponent(new GrassTile(cellX, cellY));
			object.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/base-tile.png"))));
			Window.getScene().addGameObjectToScene(object);
																									
			return object;
		}
		
		return null;
	}
	

	@Override
	public void update(float dt) {
	}
}
