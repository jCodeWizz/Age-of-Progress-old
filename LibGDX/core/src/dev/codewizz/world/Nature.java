package dev.codewizz.world;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.codewizz.utils.Utils;
import dev.codewizz.world.objects.Animal;
import dev.codewizz.world.objects.Cow;
import dev.codewizz.world.objects.Herd;

public class Nature {

	public World world;
	public static final int ANIMAL_CAP = 20;
	
	public List<Animal> animals = new CopyOnWriteArrayList<>();
	public List<Herd> herds = new CopyOnWriteArrayList<>();

	// counter is decreased by 1 every second.
	public float counter = 0;
	
	public Nature(World world) {
		this.world = world;
	}
	
	public void update(float dt) {
		if(counter < 0) {
			
			if(animals.size() < ANIMAL_CAP) {
				for(int i = 0; i < 5; i++) {
					if(spawnHerd()) { 
						break;
					}
				}
			}
			
			counter = Utils.getRandom(100, 200);
		} else {
			counter -= dt;
		} 
	}
	
	public boolean spawnHerd() {
		Herd herd = new Herd();
		
		int size = Utils.getRandom(3, 11);
		Cell cell = world.getRandomCell();
		
		for(int i = 0; i < size; i++) {
			
			
			
			
			Cow cow = new Cow(cell.x + Utils.getRandom(0, 256), cell.y + Utils.getRandom(0, 80));
			if(addAnimal(cow)) {
				herd.addMember(cow);
			}
		}
		
		if(herd.getMembers().size() > 1) {
			herd.setLeader();
			addHerd(herd);
			
			return true;
		} else {
			for(Animal a : herd.getMembers()) {
				this.removeAnimal(a);
			}
			herd.clear();
			
			return false;
		}
	}
	
	public boolean addAnimal(Animal a) {
		
		Cell cell = world.findCell(a.getX(), a.getY(), 4, false, TileType.Base, TileType.Flower);
		
		if(cell == null) { return false; }
		
		a.setX(cell.x);
		a.setY(cell.y);
		
		world.objects.add(a);
		animals.add(a);
		
		return true;
	}
	
	public Herd addHerd(Herd h) {
		herds.add(h);
		return h;
	}
	
	public void removeAnimal(Animal a) {
		animals.remove(a);
		world.objects.remove(a);
	}
	
}