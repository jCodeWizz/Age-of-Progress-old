package dev.codewizz.world.objects.tasks;

import dev.codewizz.world.objects.TaskableObject;

public abstract class Task {
	
	protected boolean tasking = false;
	protected boolean started = false;
	
	public Task() {
		
	}
	
	// called when task is finished
	public abstract void finish();
	
	// called when task is stopped withouit finishing
	public abstract void stop();
	
	// called when task is started
	public abstract void start(TaskableObject object);
	
	// called when cell of task is reached
	public abstract void reach();
	
	// called every frame
	public abstract void update(float d);
	
	public abstract String getName();

	public boolean isTasking() {
		return tasking;
	}

	public void setTasking(boolean tasking) {
		this.tasking = tasking;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	
	
}
