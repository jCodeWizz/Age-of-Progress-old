package dev.codewizz.world.pathfinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import dev.codewizz.main.Main;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.Animal;
import dev.codewizz.world.objects.ID;

public class Agent {

	private Cell previousCell;
	public Queue<Cell> path = new Queue<Cell>();
	public boolean moving = false;
	private CellGraph graph;
	private Vector2 dir;

	public Cell goal;

	private GameObject object;

	public Agent(GameObject object) {
		dir = new Vector2();
		this.object = object;
	}

	public void update(float d, float x, float y) {

		if (graph == null)
			graph = Main.inst.world.cellGraph;

		checkDistance(x, y);
	}

	private void checkDistance(float x, float y) {
		if (path.size > 0) {
			Vector2 target = path.first().getMiddlePoint();
			if (Vector2.dst(x, y, target.x, target.y) < 5) {
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

	/**
	 * Returns the current direction towards next tile.
	 * @return Vector2 
	 */
	public Vector2 getDir() {
		return dir;
	}

	/**
	 * Clearing path, but should be (almost) empty, resetting dir, and moving = false
	 * Also call onReach() to notify GameObject of reaching objective.
	 */
	private void reach() {
		path.clear();
		dir.x = 0;
		dir.y = 0;
		moving = false;
		onReach();
	}
	
	/**
	 * Will force stop (without reaching) the agent.
	 * Clearing the path so there is no next tiles, setting dir to 0 on both axis and moving = false
	 */
	public void stop() {
		path.clear();
		dir.x = 0;
		dir.y = 0;
		moving = false;
	}

	public void onReach() {

	}

	/**
	 * Set the new goal for this agent. First generating a sequence of tiles (the path) and then setting the speed towards the first tile on this path.
	 * @param goal The final cell on the path.
	 * @param x The current x coordinate of the agent. This will be the starting point for path seaching.
	 * @param y The current y coordinate of the agent.
	 * @return false when a path couldn't be found. True if the agent has started moving towards goal.
	 */
	public boolean setGoal(Cell goal, float x, float y) {
		this.goal = goal;

		try {
			previousCell = Main.inst.world.getCell(x, y);

			
			GraphPath<Cell> graphPath = graph.findPath(previousCell, goal);
			for (int i = 1; i < graphPath.getCount(); i++) {

				if (object instanceof Animal) {
					if (graphPath.get(i).getObject() != null) {
						if (graphPath.get(i).getObject().getID() == ID.FenceGate) {
							stop();
							return false;
						}
					}
				}

				path.addLast(graphPath.get(i));
			}

			int s = graph.getConnections(goal).size;

			if (!path.isEmpty() && s > 0) {
				setSpeedToNextCell(x, y);
				moving = true;

				return true;
			}
		} catch (Exception e) {
			Logger.error("Path finding exception: ");
			Logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Will take the current x,y and calculate a velocity (for both directions) for the agent to move with. These will be pointed towards
	 * the next cell on the path. The GameObject.class should get this direction and set the objects velocity with correct speed.
	 * @param x current x coordinate of the agent.
	 * @param y current y coordinate of the agent.
	 */
	private void setSpeedToNextCell(float x, float y) {
		Cell nextCell = path.first();
		float angle = MathUtils.atan2(nextCell.getMiddlePoint().y - y, nextCell.getMiddlePoint().x - x);
		dir.x = MathUtils.cos(angle);
		dir.y = MathUtils.sin(angle);
		dir.nor();
	}
}
