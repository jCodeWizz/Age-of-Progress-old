package dev.codewizz.world.settlement;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import dev.codewizz.main.Main;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.tasks.Task;

public class Settlement {

	private float x, y;
	
	public List<Hermit> members = new CopyOnWriteArrayList<>();
	public Queue<Task> taskTree = new Queue<>();
	
	public Settlement(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update(float dt) {
	}
	
	public Hermit addHermit(float x, float y) {
		
		Hermit hermit = new Hermit(x, y);
		
		hermit.setSettlement(this);
		members.add(hermit);
		Main.inst.world.objects.add(hermit);
		
		return hermit;
	}
	
	public Hermit addHermit(Hermit hermit) {
		
		hermit.setSettlement(this);
		members.add(hermit);
		
		return hermit;
	}
	
	public Queue<Task> getTasks() {
		return taskTree;
	}
	
	public void addTask(Task task, boolean prio) {
		
		if(prio) {
			taskTree.addFirst(task);
		} else {
			taskTree.addLast(task);
		}
	}
	
	public void removeTask(Task task) {
		taskTree.removeValue(task, false);
	}
	

	public Vector2 getLocation() {
		return new Vector2(x, y);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	

}
