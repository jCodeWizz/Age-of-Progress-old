package dev.codewizz.world.pathfinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;

public class Agent {

	private Cell previousCell;
	public Queue<Cell> path = new Queue<Cell>();
	public boolean moving = false;
	private CellGraph graph;
	private Vector2 dir;

	public Agent() {
		dir = new Vector2();
		graph = Main.inst.world.cellGraph;
	}

	public void update(float d, float x, float y) {
		checkDistance(x, y);
	}

	private void checkDistance(float x, float y) {
		if (path.size > 0) {
			Vector2 target = path.first().getMiddlePoint();
			if (Vector2.dst(x, y, target.x, target.y) < 30) {
				reachGoal(x, y);
			}
		}
	}
	
	public Vector2 getMiddlePoint() {
		return new Vector2();
	}

	private void reachGoal(float x, float y) {
		Cell nextCell = path.first();

		this.previousCell = nextCell;
		path.removeFirst();

		if (path.size == 0) {
			reach();
		} else {
			setSpeedToNextCell(x, y);
		}
	}
	
	public Vector2 getDir() {
		return dir;
	}
	
	private void reach() {
		path.clear();
		dir.x = 0;
		dir.y = 0;
		moving = false;
		onReach();
	}
	
	public void stop() {
		path.clear();
		dir.x = 0;
		dir.y = 0;
		moving = false;
	}
	
	public void onReach() {
		
	}
	
	public void setGoal(Cell goal, float x, float y) {
		Cell[][] grid = Main.inst.world.grid;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].tile.getHitbox().contains(x, y)) {
					previousCell = grid[i][j];
				}
			}
		}
	    GraphPath<Cell> graphPath = graph.findPath(previousCell, goal);
	    for (int i = 1; i < graphPath.getCount(); i++) {
	      path.addLast(graphPath.get(i));
	    }
	    if(!path.isEmpty()) {
	    	setSpeedToNextCell(x, y);
		    moving = true;
	    }
	  }

	
	private void setSpeedToNextCell(float x, float y) {
		Cell nextCell = path.first();
	    float angle = MathUtils.atan2(nextCell.getMiddlePoint().y - y, nextCell.getMiddlePoint().x - x);
	    dir.x = MathUtils.cos(angle);
	    dir.y = MathUtils.sin(angle);
	}
}
