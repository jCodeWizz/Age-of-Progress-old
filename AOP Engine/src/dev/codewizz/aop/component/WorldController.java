package dev.codewizz.aop.component;

import java.awt.event.KeyEvent;

import org.joml.Vector2f;

import dev.codewizz.aop.world.Cell;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.input.KeyListener;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.object.GameObject;
import dev.codewizz.engine.object.component.Collider;
import dev.codewizz.engine.object.component.PolyCollider;
import dev.codewizz.engine.object.component.SpriteRenderer;
import dev.codewizz.engine.object.component.Transform;
import dev.codewizz.engine.renderer.Sprite;
import dev.codewizz.engine.util.AssetPool;

public class WorldController extends Component {

	public static final int WIDTH = 32, HEIGHT = 64;
	
	public Cell[][] grid = new Cell[WIDTH][HEIGHT];
	
	@Override
	public void start() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 2 == 0) {
					grid[i][j] = new Cell(i, j, (i-(WIDTH/2)) * 64, (j-(HEIGHT/2)) * 16, false);
				} else {
					grid[i][j] = new Cell(i, j, (i-(WIDTH/2)) * 64 + 32, (j-(HEIGHT/2)) * 16, true);
				}
			}
		}
		
		cellInit();
	}
	
	public void cellInit() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				GameObject object = new GameObject("Tile" + i + j, new Transform(new Vector2f(grid[i][j].tileX, grid[i][j].tileY), new Vector2f(Tile.WIDTH, Tile.HEIGHT)), -2);
				object.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/textures/environment/emptytile.png"))));
				object.addComponent(new PolyCollider(new float[] { -32, 0, 32, 0 }, new float[] { 0, -16, 0, 16}, 4));
				object.addComponent(new BaseTile(i, j));
				grid[i][j].tile = object;
				Window.getScene().addGameObject(object);
			}
		}
	}

	@Override
	public void update(float dt) {
		if(KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
			Vector2f coords = MouseListener.getWorld();
			
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid[i].length; j++) {
					if(grid[i][j].tile.getComponent(Collider.class).contains(coords.x, coords.y)) {
						grid[i][j].setTile(new DirtTile(i, j));
					}
				}
			}
		}
	}
}
