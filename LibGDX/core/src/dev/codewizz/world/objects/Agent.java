package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import dev.codewizz.input.MouseInput;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.tiles.DirtTile;

public class Agent extends GameObject {

	private GraphPath<Cell> path;
	private float dx, dy = 0;
	private Cell dest;
	
	public Agent(float x, float y) {
		super(x, y);
		
		this.w = 32;
		this.h = 32;
	}

	@Override
	public void update(float d) {
		if(MouseInput.dragging[0] && MouseInput.hoveringOverCell != null && Gdx.input.isKeyPressed(Input.Keys.TAB)) {
			Cell toCell = MouseInput.hoveringOverCell;
			Cell fromCell = null;
			
			Cell[][] grid = Main.inst.world.grid;
			
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid[i].length; j++) {
					if(grid[i][j].tile.getHitbox().contains(x, y)) {
						fromCell = grid[i][j];
					}
				}
			}
			
			path = Main.inst.world.cellGraph.findPath(toCell, fromCell);
			dest = path.get(path.getCount()-1);
			
			
			
			toCell.setTile(new DirtTile(toCell));
			fromCell.setTile(new DirtTile(fromCell));
			
			MouseInput.dragging[0] = false;
		}
		
		if(dest != null) {
			Cell last = dest;
			float angle = MathUtils.atan2(last.y - y, last.x - x);
		    dx = MathUtils.cos(angle) * 5;
		    dy = MathUtils.sin(angle) * 5;
			dest = null;
		}
		
		x+=dx;
		y+=dy;
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(Assets.getSprite("construction-tile"), x, y, w, h);
	}
}
