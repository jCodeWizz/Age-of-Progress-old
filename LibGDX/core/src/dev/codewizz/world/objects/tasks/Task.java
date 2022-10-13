package dev.codewizz.world.objects.tasks;

import dev.codewizz.world.objects.Hermit;

public abstract class Task {
	
	protected boolean tasking = false;
	protected boolean started = false;
	
	public Task() {
		
	}
	
	public abstract void finish();
	public abstract void stop();
	public abstract void start(Hermit hermit);
	public abstract void reach();
	public abstract void update(float d);

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
