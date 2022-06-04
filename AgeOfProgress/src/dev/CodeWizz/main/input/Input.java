package dev.CodeWizz.main.input;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.main.AgeOfProgress;
import dev.CodeWizz.main.objects.envirement.Tile;
import dev.CodeWizz.main.objects.envirement.TileType;
import dev.CodeWizz.main.objects.envirement.World;

public class Input {

	public Input() {
		
	}
	
	public void update(GameContainer gc) {
		World world = AgeOfProgress.inst.world;
		
		
		if(gc.getInput().isButtonDown(0)) {
			System.out.println(" E");
			for(int i = 0; i < world.grid.length; i++) {
				for(int j = 0; j < world.grid[i].length; j++) {
					if(world.grid[i][j].getHitbox().contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
						world.grid[i][j] = new Tile(i*64 + 32, j*16, TileType.Up);
					}
				}
			}
		}
	}
}
