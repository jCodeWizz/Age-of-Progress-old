package dev.codewizz.input;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.codewizz.world.Cell;

public class AreaSelector {

	public boolean selected = false;
	public List<Cell> area = new CopyOnWriteArrayList<>();
	public Cell start, stop;
	
	
	/*
	 * 
	 * Unfinished, might not be used.
	 * 
	 */
	
	
	public void setArea(Cell[][] grid) {
		if(start.indexX != stop.indexX && start.indexY != stop.indexY) {
			int dirX = Math.abs(stop.indexX - start.indexX)/(stop.indexX - start.indexX);
			int dirY = Math.abs(stop.indexY - start.indexY)/(stop.indexY - start.indexY);
			
			for(int i = start.indexX; i != stop.indexX; i+=dirX) {
				for(int j = start.indexY; j != stop.indexY; j+=dirY) {
					area.add(grid[i][j]);
				}
			}
		}
	}
}
