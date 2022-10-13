package dev.codewizz.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import dev.codewizz.main.Camera;
import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.Tile;
import dev.codewizz.world.TileType;

public class MouseInput implements InputProcessor {
	
	public static boolean[] dragging = new boolean[5];
	public static Vector3 coords = new Vector3();
	public static Cell hoveringOverCell;
	public static TileType currentlyDrawingType = TileType.Tiled;
	public static AreaSelector area = new AreaSelector();
	
	public void update(float d) {
		Cell[][] grid = Main.inst.world.grid;
		coords = Main.inst.camera.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		
		hoveringOverCell = null;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].tile.getHitbox().contains(coords.x, coords.y)) {
					hoveringOverCell = grid[i][j];
				}
			}
		}
		
		
		if(dragging[0]) {
			if(hoveringOverCell != null) {
				hoveringOverCell.setTile(Tile.getTileFromType(currentlyDrawingType, hoveringOverCell));
				//if(area.selected) {
				//	area.start = null;
				//	area.stop = null;
				//	area.area.clear();
				//	area.selected = false;
				//} else if(area.start == null){
				//	area.start = hoveringOverCell;
				//}
			}
		} else if(area.start != null && area.stop == null) {
			//if(area.start.index != hoveringOverCell.index) {
				//area.stop = hoveringOverCell;
				//area.selected = true;
				//area.setArea(grid);
				
				//for(Cell cell : area.area) {
				//	cell.setTile(Tile.getTileFromType(currentlyDrawingType, cell));
				//}
			//}
		}
		
		if(dragging[2]) {
			Main.inst.camera.move(-Gdx.input.getDeltaX(), 0);
			Main.inst.camera.move(0, Gdx.input.getDeltaY());		
		}
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		dragging[button] = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		dragging[button] = false;
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
			Main.inst.camera.zoom(Camera.zoomSpeed * amountY, Gdx.input.getX(), Gdx.input.getY());
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
