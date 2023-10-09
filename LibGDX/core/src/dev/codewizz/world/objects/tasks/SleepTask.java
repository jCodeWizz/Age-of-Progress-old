package dev.codewizz.world.objects.tasks;

import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.TaskableObject;

public class SleepTask extends Task {

	private Hermit hermit;
	
	@Override
	public void finish() {
		hermit.finishCurrentTask();
	}

	@Override
	public void stop() {
		hermit.getAgent().stop();
		hermit.finishCurrentTask();
	}

	@Override
	public void start(TaskableObject object) {
		hermit = (Hermit) object;
		Cell cell = Main.inst.world.getCell(hermit.getHome().getX(), hermit.getHome().getY() + 32);
		object.getAgent().setGoal(cell, object.getX(), object.getY());
		if(object.getAgent().path.isEmpty())
			stop();
		started = true;
	}

	@Override
	public void reach() {
		hermit.getHome().enter(hermit);
	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public String getName() {
		return "Getting sleep";
	}

	@Override
	public void reset() {
		
	}
}
