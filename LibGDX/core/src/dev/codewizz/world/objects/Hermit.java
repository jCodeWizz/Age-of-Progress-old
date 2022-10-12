package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.pathfinding.Agent;

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
		
		if(Gdx.input.isButtonPressed(0) && Gdx.input.isKeyPressed(Input.Keys.TAB) && !agent.moving) {
			Cell cell = MouseInput.hoveringOverCell;
			if(cell != null) {
				agent.setGoal(cell, x, y);
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
