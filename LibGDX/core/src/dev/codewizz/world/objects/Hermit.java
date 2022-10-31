package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Cell;
import dev.codewizz.world.objects.tasks.MoveTask;

public class Hermit extends TaskableObject {

	public Hermit(float x, float y) {
		super(x, y);

		this.w = 32;
		this.h = 32;
	}
	
	@Override
	public void update(float d) {
		super.update(d);
		
		if(Gdx.input.isKeyPressed(Input.Keys.TAB)) {
			Cell cell = MouseInput.hoveringOverCell;
			if(cell != null) {
				tree.addLast(new MoveTask(cell));
			}
		}
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(Assets.getSprite("construction-tile"), x, y);
	}
}
