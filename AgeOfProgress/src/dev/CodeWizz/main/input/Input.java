package dev.CodeWizz.main.input;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.main.AgeOfProgress;
import dev.CodeWizz.main.objects.envirement.World;

public class Input {

	public Input() {

	}

	public void update(GameContainer gc) {
		World world = AgeOfProgress.inst.world;
		
		if(gc.getInput().isButton(2) || gc.getInput().isButton(1) || gc.getInput().isButton(3)) {
			for (int i = 0; i < world.grid.length; i++) {
				for (int j = 0; j < world.grid[i].length; j++) {
					if (world.grid[i][j].getTile().getHitbox().contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
						
						if(gc.getInput().isButton(2)) {
							world.grid[i][j].setGrass();
						}
						if(gc.getInput().isButton(1)) {
							world.grid[i][j].setDirtPath();
						}
						if(gc.getInput().isButton(3)) {
							System.out.println("E");
							world.grid[i][j].setTiled();
						}
					}
				}
			}
		}

		
	}
}
