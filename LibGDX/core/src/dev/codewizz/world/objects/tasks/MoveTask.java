package dev.codewizz.world.objects.tasks;

import dev.codewizz.world.Cell;
import dev.codewizz.world.objects.Hermit;

public class MoveTask extends Task {

	private Cell cell;
	private Hermit hermit;
	
	public MoveTask(Cell cell) {
		this.cell = cell;
	}
	
	@Override
	public void start(Hermit hermit) {
		this.hermit = hermit;
		hermit.agent.setGoal(cell, hermit.getX(), hermit.getY());
		if(hermit.agent.path.isEmpty())
			reach();
		started = true;
	}
	
	@Override
	public void reach() {
		finish();
	}
	
	@Override
	public void finish() {
		hermit.finishCurrentTask();
	}

	@Override
	public void stop() {
		
		// TODO: re-add the task back to the pool (Tree would not be handy ig)
		
		hermit.agent.stop();
		hermit.finishCurrentTask();
	}

	@Override
	public void update(float d) {
		
	}
}
