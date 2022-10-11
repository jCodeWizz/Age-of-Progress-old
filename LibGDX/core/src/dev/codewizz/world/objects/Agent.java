package dev.codewizz.world.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.pathfinding.CellGraph;

public abstract class Agent extends GameObject {

	private Cell previousCell;
	private Queue<Cell> path = new Queue<Cell>();
	private CellGraph graph;
	private Vector2 dir;

	protected float speed = 4f;

	public Agent(float x, float y) {
		super(x, y);

		dir = new Vector2();

		graph = Main.inst.world.cellGraph;

	}

	@Override
	public void update(float d) {
		x += dir.x * d;
		y += dir.y * d;

		checkDistance();
	}

	private void checkDistance() {
		if (path.size > 0) {
			Cell target = path.first();
			if (Vector2.dst(x, y, target.x, target.y) < 5) {
				reachGoal();
			}
		}
	}

	private void reachGoal() {
		Cell nextCell = path.first();

		// Set the position to keep the Agent in the middle of the path
		this.x = nextCell.x;
		this.y = nextCell.y;

		this.previousCell = nextCell;
		path.removeFirst();

		if (path.size == 0) {
			reach();
		} else {
			setSpeedToNextCell();
		}
	}
	
	private void reach() {
		Cell nextCell = path.first();
	    float angle = MathUtils.atan2(nextCell.y - previousCell.y, nextCell.x - previousCell.x);
	    dir.x = MathUtils.cos(angle) * speed;
	    dir.y = MathUtils.sin(angle) * speed;
	}
	
	private void setSpeedToNextCell() {
		
	}
}
