package dev.codewizz.aop.world;

import org.joml.Vector2f;

import dev.codewizz.aop.components.Tile;
import dev.codewizz.aop.components.TileType;
import dev.codewizz.aop.components.tiles.EmptyTile;
import dev.codewizz.aop.components.tiles.GrassTile;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.Component;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.util.AssetPool;

public class World extends Component {
	
	public static final int WIDTH = 32, HEIGHT = 64;
	
	public Cell[][] grid;
	
	public World() {
		grid = new Cell[WIDTH][HEIGHT];
	}
	
	@Override
	public void start() {
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (j % 2 == 0) {
					grid[j][i] = new Cell(i, j, (i-(WIDTH/2)) * 64, (j-(HEIGHT/2)) * 16, false, this, TileType.EmptyTile);
				} else {
					grid[j][i] = new Cell(i, j, (i-(WIDTH/2)) * 64 + 32, (j-(HEIGHT/2)) * 16, true, this, TileType.GrassTile);
				}
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
		} else if(tile == TileType.EmptyTile) {
			if(tile == TileType.GrassTile) {
				GameObject object = new GameObject("EmptyTile", new Transform(new Vector2f(x, y), new Vector2f(Tile.WIDTH, Tile.HEIGHT)));
				object.addComponent(new EmptyTile(cellX, cellY));
				object.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/empty-tile.png"))));
				Window.getScene().addGameObjectToScene(object);
					
				return object;
			}
		}
		
		return null;
	}
	
	
	

	@Override
	public void update(float dt) {
	}
}
