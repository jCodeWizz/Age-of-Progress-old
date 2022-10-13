package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.tasks.MoveTask;
import dev.codewizz.world.objects.tasks.Task;
import dev.codewizz.world.pathfinding.Agent;

public class Hermit extends GameObject {

	private Queue<Task> tree = new Queue<>();
	private Task currentTask;
	
	private float speed = 400f;
	public Agent agent;
	
	public Hermit(float x, float y) {
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
		
		this.w = 32;
		this.h = 32;
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
		
		if(Gdx.input.isKeyPressed(Input.Keys.TAB)) {
			Cell cell = MouseInput.hoveringOverCell;
			if(cell != null) {
				tree.addLast(new MoveTask(cell));
			}
		}
		
		x += agent.getDir().x * d * speed;
		y += agent.getDir().y * d * speed;
	}
	
	public void finishCurrentTask() {
		tree.removeFirst();
		currentTask = null;
	}
	
	public Vector2 getCenter() {
		return new Vector2(x+w/2, y+h/2);
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(Assets.getSprite("construction-tile"), x, y);
	}
}
