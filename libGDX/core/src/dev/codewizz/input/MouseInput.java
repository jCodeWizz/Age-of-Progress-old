package dev.codewizz.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.dongbat.jbump.util.MathUtils;

import dev.codewizz.main.Camera;
import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.tiles.DirtTile;

public class MouseInput implements InputProcessor {

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		Vector3 worldSpace = Main.inst.camera.cam.unproject(new Vector3(screenX, screenY, 0));
		Cell[][] grid = Main.inst.world.grid;
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].tile.getHitbox().contains(worldSpace.x, worldSpace.y)) {
					grid[i][j].setTile(new DirtTile(grid[i][j]));
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		if(Main.inst.camera != null) {
			Main.inst.camera.zoom += amountY*Camera.zoomSpeed;
			Main.inst.camera.zoom = MathUtils.clamp(Main.inst.camera.zoom, 0.3f, 2f);
			return true;
		}
		return false;
	}
	
	/*
	 * 
	 * KEY INPUTS NOT IN THIS CLASS
	 * 
	 */
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}
}
