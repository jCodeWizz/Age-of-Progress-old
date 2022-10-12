package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.tiles.DirtTile;

public class Hermit extends GameObject {

	private float speed = 400f;
	
	private Agent agent;
	
	public Hermit(float x, float y) {
		super(x, y);

		agent = new Agent();
		
		this.w = 32;
		this.h = 32;
	}
	
	@Override
	public void update(float d) {
		
		agent.update(d, x, y);
		
		if(Gdx.input.isButtonJustPressed(0) && Gdx.input.isKeyPressed(Input.Keys.TAB)) {
			Cell cell = MouseInput.hoveringOverCell;
			if(cell != null) {
				agent.setGoal(cell, x, y);
			
				for(int i = 0; i < agent.path.size; i++) {
					agent.path.get(i).setTile(new DirtTile(agent.path.get(i)));
				}
			}
			
			Cell[] e = cell.getCrossedNeighbours();
			for(int i = 0; i < e.length; i++) {
				if(e[i] != null) {
					e[i].setTile(new DirtTile(e[i]));
				}
			}
		}
		
		x += agent.getDir().x * d * speed;
		y += agent.getDir().y * d * speed;
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(Assets.getSprite("construction-tile"), x, y);
	}
}
