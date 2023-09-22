package dev.codewizz.input;

import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import dev.codewizz.gfx.gui.UIElement;
import dev.codewizz.main.Camera;
import dev.codewizz.main.Main;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Tile;
import dev.codewizz.world.TileType;
import dev.codewizz.world.objects.Entity;
import dev.codewizz.world.objects.IBuy;

public class MouseInput implements InputProcessor {

	public static boolean object = true;
	
	public static boolean[] dragging = new boolean[5];
	public static Vector3 coords = new Vector3();
	public static Cell hoveringOverCell;
	public static TileType currentlyDrawingType = TileType.Water;
	public static GameObject currentlyDrawingObject = null;
	public static AreaSelector area = new AreaSelector();
	public static boolean clear = false;
	public static UIElement lastClickedUIElement;

	public void update(float d) {
		if (Main.PLAYING && !Main.PAUSED) {
			Cell[][] grid = Main.inst.world.grid;
			coords = Main.inst.camera.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

			/*
			 * 
			 * SELECTING CURRENT TILE AND CHECKING IF IT'S CLEAR OR NOT.
			 * 
			 */

			hoveringOverCell = null;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j].tile.getHitbox().contains(coords.x, coords.y)) {
						hoveringOverCell = grid[i][j];
						clear = true;

						Tile tile = grid[i][j].tile;

						if (tile.getType() == currentlyDrawingType || tile.getType() == TileType.Empty) {
							clear = false;
						}
					}
				}
			}

			/*
			 * 
			 * WHILE BUTTING IS DOWN, PLACE CURRENTLYDRAWINGTYPE TILE
			 * 
			 */

			if (dragging[0]) {

				if (hoveringOverCell != null) {
					
					if(object) {
						if(currentlyDrawingObject != null) {
							IBuy object = (IBuy) currentlyDrawingObject;
							
							Main.inst.world.objects.add(object.getCopy(hoveringOverCell.x, hoveringOverCell.y));
							object.onPlace(hoveringOverCell);
							
							
							
							dragging[0] = object.conintues();
							lastClickedUIElement.setAvailable(object.available());
						}
					} else {
						Tile tile;
						try {
							tile = Tile.getTileFromType(currentlyDrawingType, hoveringOverCell);
							hoveringOverCell.setTile(tile);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					
					
					
					if (Main.DEBUG) {
						Cell.printDebugInfo(hoveringOverCell);
					}
				}
			}

			/*
			 * SCROLL BUTTON CAMERA MOVEMENT (DOESNT FULLY WORK, BUT GOOD ENOUGH)
			 */

			if (dragging[2]) {
				Main.inst.camera.move(-Gdx.input.getDeltaX(), 0);
				Main.inst.camera.move(0, Gdx.input.getDeltaY());
			}
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		dragging[button] = true;

		/*
		 * 
		 * Object selector
		 * 
		 */
		
		if(Main.inst.world != null) {
			
			Collections.sort(Main.inst.world.objects);
			Collections.reverse(Main.inst.world.objects);
			for (GameObject obj : Main.inst.world.objects) {
				if (obj instanceof Entity) {
					Entity entity = (Entity) obj;
					if(entity.isSelected())
						entity.deselect();
				}
			}
			
			boolean hit = false;
			for (GameObject obj : Main.inst.world.objects) {
				if (obj instanceof Entity) {
					Entity entity = (Entity) obj;
					if (entity.getBounds().contains(coords.x, coords.y) && !entity.isSelected() && !hit) {
						entity.select();
						hit = true;
						dragging[button] = false;
					}
				}
			}
		}

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
		if (Main.PLAYING && !Main.PAUSED) {
			if (Main.inst.camera != null) {
				Main.inst.camera.zoom(Camera.zoomSpeed * amountY, Gdx.input.getX(), Gdx.input.getY());
				return true;
			}
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
