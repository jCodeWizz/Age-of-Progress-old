package dev.codewizz.world.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;

public class Herd {

	private final static Random RANDOM = new Random();
	private List<Animal> members = new ArrayList<>();
	private Animal leader;
	private int offset;

	public Herd() {
		this.offset = 1;
	}

	public Herd(Animal... animals) {
		for (int i = 0; i < animals.length; i++) {
			members.add(animals[i]);
		}

		this.leader = members.get(RANDOM.nextInt(members.size()));
	}

	public Herd(Animal leader, Animal... animals) {
		for (int i = 0; i < animals.length; i++) {
			members.add(animals[i]);
		}

		this.leader = leader;
	}

	public Cell newPath() {
		Cell cell = Main.inst.world.getCell(leader.getX(), leader.getY());

		return Main.inst.world.getCellIndex(cell.indexX + RANDOM.nextInt(offset + offset) - offset,
				cell.indexY + RANDOM.nextInt(offset + offset) - offset);
	}

	public void attackHerd() {
		for (Animal a : members) {
			a.agent.setGoal(null, a.getX(), a.getY());
		}
	}

	public List<Animal> getMembers() {
		return members;
	}

	public void addMember(Animal animal) {
		members.add(animal);
	}

	public void setLeader(Animal animal) {
		this.leader = animal;
	}

	public void setLeader() {
		this.leader = members.get(RANDOM.nextInt(members.size()));
	}

	public Animal getLeader() {
		return leader;
	}

	public void leaderMoved() {
		Cell cell = Main.inst.world.getCell(leader.getX(), leader.getY());

		for (Animal a : members) {
			if (!a.equals(leader)) {
				a.agent.setGoal(Main.inst.world.getCellIndex(cell.indexX + RANDOM.nextInt(offset + offset) - offset,
						cell.indexY + RANDOM.nextInt(offset + offset) - offset), a.getX(), a.getY());
			}
		}
	}
}
