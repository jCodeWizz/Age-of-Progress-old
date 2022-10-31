package dev.codewizz.world.objects.tasks;

import dev.codewizz.world.Cell;
import dev.codewizz.world.objects.TaskableObject;

public class MoveTask extends Task {

	private Cell cell;
	private TaskableObject object;
	
	public MoveTask(Cell cell) {
		this.cell = cell;
	}
	
	@Override
	public void reach() {
		finish();
	}
	
	@Override
	public void finish() {
		object.finishCurrentTask();
	}

	@Override
	public void stop() {
		
		// TODO: re-add the task back to the pool (Tree would not be handy ig)
		
		object.getAgent().stop();
		object.finishCurrentTask();
	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public void start(TaskableObject object) {
		this.object = object;
		object.getAgent().setGoal(cell, object.getX(), object.getY());
		if(object.getAgent().path.isEmpty())
			reach();
		started = true;
		
	}
}
