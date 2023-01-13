package dev.codewizz.world.objects.buildings;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;

public abstract class Building extends GameObject {

	protected boolean multiTile;
	protected int sizeX, sizeY;
	protected List<Cell> daughterCells = new CopyOnWriteArrayList<>();
	
	public Building(float x, float y) {
		super(x, y);

		this.multiTile = false;
		this.sizeX = 1;
		this.sizeY = 1;
	}
	
}
