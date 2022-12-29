package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.tasks.Task;
import dev.codewizz.world.pathfinding.Agent;

public abstract class TaskableObject extends GameObject {

	protected Queue<Task> tree = new Queue<>();
	protected Task currentTask;
	protected boolean facingRight = true;
	protected Vector2 vel = new Vector2();
	
	
	protected float speed = 400f;
	protected Agent agent;
	
	public TaskableObject(float x, float y) {
		super(x, y);
		
		agent = new Agent() {
			@Override
			public void onReach() {
				reachCell();
			}
			
			@Override
			public Vector2 getMiddlePoint() {
				return getCenter();
			}
		};
	}
	
	public void finishCurrentTask() {
		tree.removeFirst();
		currentTask = null;
	}
	
	public void reachCell() {
		if(currentTask != null) {
			currentTask.reach();
		}
	}

	@Override
	public void update(float d) {
		if(currentTask == null) {
			if(!tree.isEmpty()) {
				currentTask = tree.first();
			}
		} else {
			// TODO: maybe add a check to see if Hermit can take a task
			if(currentTask.isStarted()) {
				currentTask.update(d);
			} else {
				currentTask.start(this);
			}
		}
		agent.update(d, x, y);
		
		vel.x = agent.getDir().x * d * speed;
		vel.y = agent.getDir().y * d * speed;
		
		x += vel.x;
		y += vel.y;
	}

	public Vector2 getCenter() {
		return new Vector2(x+w/2, y+h/2);
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Queue<Task> getTree() {
		return tree;
	}

	@Override
	public abstract void render(SpriteBatch b);
}
