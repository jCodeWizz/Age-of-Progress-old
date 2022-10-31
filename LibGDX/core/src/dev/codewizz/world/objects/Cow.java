package dev.codewizz.world.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dongbat.jbump.util.MathUtils;

import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.tasks.MoveTask;

public class Cow extends TaskableObject {
	
	/*
	 * pTR1600:
	 * 
	 * jELMER cOw
	 * 
	 *  * HAppy FacE *
	 * 
	 */
	
	private final static Random RANDOM = new Random();
	
	public Cow(float x, float y) {
		super(x, y);
		
		speed = 10f;
	}
	
	@Override
	public void update(float d) {
		super.update(d);

		if(!agent.moving) {
			if(RANDOM.nextInt(1000) == 0) {
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

	@Override
	public void render(SpriteBatch b) {
		b.draw(Assets.getSprite("cow-idle"), x, y);
	}
}
