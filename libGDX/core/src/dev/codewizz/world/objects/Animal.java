package dev.codewizz.world.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dongbat.jbump.util.MathUtils;

import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.tasks.MoveTask;

public abstract class Animal extends TaskableObject {

	private final static Random RANDOM = new Random();
	private Herd herd;
	private boolean inHerd = false;
	
	
	public Animal(float x, float y, Herd herd) {
		super(x, y);
		
		this.herd = herd;
		inHerd = true;
	}
	
	public Animal(float x, float y) {
		super(x, y);
		
		inHerd = false;
	}
	
	@Override
	public void update(float d) {
		super.update(d);
		
		if(!agent.moving) {
			if(RANDOM.nextInt(1000) == 0) {
				if(inHerd) {
					if(herd.getLeader().equals(this)) {
						Cell currentCell = Main.inst.world.getCell(x, y);
						
						int offX = RANDOM.nextInt(12) - 6 + currentCell.indexX;
						int offY = RANDOM.nextInt(12) - 6 + currentCell.indexY;
						
						offX = MathUtils.clamp(offX, 0, World.WORLD_SIZE_W-1);
						offY = MathUtils.clamp(offY, 0, World.WORLD_SIZE_H-1);
						
						Cell goalCell = Main.inst.world.grid[offX][offY];
					
						tree.addLast(new MoveTask(goalCell));
						herd.leaderMoved();
					} else {
						Cell cell = herd.newPath();
						tree.addLast(new MoveTask(cell));
					}
				} else {
					Cell currentCell = Main.inst.world.getCell(x, y);
					
					int offX = RANDOM.nextInt(12) - 6 + currentCell.indexX;
					int offY = RANDOM.nextInt(12) - 6 + currentCell.indexY;
					
					offX = MathUtils.clamp(offX, 0, World.WORLD_SIZE_W-1);
					offY = MathUtils.clamp(offY, 0, World.WORLD_SIZE_H-1);
					
					Cell goalCell = Main.inst.world.grid[offX][offY];
				
					tree.addLast(new MoveTask(goalCell));
				}
			}
		}
	}
	
	public abstract void render(SpriteBatch b);

	public boolean isInHerd() {
		return inHerd;
	}
	
	public Herd getHerd() {
		return herd;
	}
}
