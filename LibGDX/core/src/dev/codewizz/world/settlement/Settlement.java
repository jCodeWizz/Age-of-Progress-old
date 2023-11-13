package dev.codewizz.world.settlement;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import dev.codewizz.gfx.Renderable;
import dev.codewizz.gfx.gui.UINotification;
import dev.codewizz.gfx.gui.menus.NotificationMenu;
import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.items.Inventory;
import dev.codewizz.world.items.Item;
import dev.codewizz.world.objects.hermits.Hermit;
import dev.codewizz.world.objects.hermits.Jobs;
import dev.codewizz.world.objects.tasks.GrowCropTask;
import dev.codewizz.world.objects.tasks.HaulTask;
import dev.codewizz.world.objects.tasks.Task;

public class Settlement {

	private float x, y;
	private float timer = 0, max_timer = 5f;
	private Cell cell;
	
	public List<Hermit> members = new CopyOnWriteArrayList<>();
	public Queue<Task> taskTree = new Queue<>();
	public List<GameObject> buildings = new CopyOnWriteArrayList<>();
	public List<Crop> crops = new CopyOnWriteArrayList<>();
	
	public Inventory inventory;

	public Settlement(Cell cell) {
		this.x = cell.x + 32;
		this.y = cell.y + 32;
		this.cell = cell;
		
		this.inventory = new Inventory(-1);
	}

	public void update(float dt) {

		if (timer < max_timer)
			timer += dt;
		else {
			timer = 0f;
			checkHaulTask();
		}
		
		if(Main.inst.world.nature.day) {
			for(Crop crop : crops) {
				if(crop.isReady()) {
					if(!crop.tasked) {
						crop.tasked = true;
						addTask(new GrowCropTask(crop, Jobs.Farmer), true);
					}
				} else {
					crop.counter += dt;
				}
			}
		}
	}

	public Hermit addHermit(float x, float y) {

		Hermit hermit = new Hermit(x, y);

		hermit.setSettlement(this);
		members.add(hermit);
		Main.inst.world.objects.add(hermit);

		((NotificationMenu) Main.inst.renderer.ui.getElement("notification-menu")).addNotification(new UINotification(
				"A new Hermit arrived!", "Give " + hermit.getName() + " a warm welcome! (And a meal!)", "people-icon"));
		;
		return hermit;
	}

	private void checkHaulTask() {
		List<Item> items = new CopyOnWriteArrayList<>();

		for (Renderable r : Main.inst.world.objects) {
			if (r instanceof Item) {
				Item i = (Item) r;
				if (!i.isHauled()) {
					items.add(i);
				}
			}
		}

		List<Item> t = new CopyOnWriteArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			t.add(items.get(i));

			if (t.size() > 5) {
				HaulTask task = new HaulTask(t);
				taskTree.addLast(task);
				t = new CopyOnWriteArrayList<>();
			}
		}

		if (!t.isEmpty()) {
			HaulTask task = new HaulTask(t);
			taskTree.addLast(task);
		}
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

		if (prio) {
			taskTree.addFirst(task);
		} else {
			taskTree.addLast(task);
		}
	}

	public Cell getCell() {
		return cell;
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
	
	public Inventory getInventory() {
		return this.inventory;
	}

}
