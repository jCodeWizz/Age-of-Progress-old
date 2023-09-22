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
	private float counter = 0;
	
	public Nature(World world) {
		this.world = world;
	}
	
	public void spawnHerd() {
		Herd herd = new Herd();
		
		int size = Utils.getRandom(3, 11);
		
		for(int i = 0; i < size; i++) {
			Cow cow = new Cow(0, 0);
			addAnimal(cow);
			herd.addMember(cow);
		}
		
		herd.setLeader();
		
		addHerd(herd);
	}
	
	public void update(float dt) {
		if(counter < 0) {
			
			if(animals.size() < ANIMAL_CAP) {
				spawnHerd();
			}
			
			counter = Utils.getRandom(100, 200);
		} else {
			counter -= dt;
		} 
	}
	
	public Animal addAnimal(Animal a) {
		world.objects.add(a);
		animals.add(a);
		return a;
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