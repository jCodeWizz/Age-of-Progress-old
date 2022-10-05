package dev.codewizz.aop.component;

import org.joml.Vector2f;

import dev.codewizz.aop.component.tiles.DirtTile;
import dev.codewizz.aop.world.Cell;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.object.component.Collider;

public class InputController extends Component {

	private Cell[][] grid;
	private Cell currentCell = null;

	@Override
	public void start() {
		grid = Window.getScene().getGameObject("World").getComponent(WorldController.class).grid;
		currentCell = grid[0][0];
	}

	@Override
	public void update(float dt) {
		Vector2f coords = MouseListener.getWorld();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].tile.getComponent(Collider.class).contains(coords.x, coords.y)) {
					gameObject.transform.position.x = grid[i][j].tileX;
					gameObject.transform.position.y = grid[i][j].tileY;
					currentCell = grid[i][j];
					break;
				}
			}
		}
		
		if (!isEnabled())
			return;

		if (MouseListener.mouseButtonDown(0)) {
			currentCell.setTile(new DirtTile(currentCell.gridX, currentCell.gridY, currentCell));
		}
	}
}