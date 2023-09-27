package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.input.MouseInput;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Serializable;
import dev.codewizz.world.objects.tasks.MoveTask;
import dev.codewizz.world.settlement.Settlement;

public class Flag extends GameObject implements IBuy, Serializable {

	private static Sprite texture = Assets.getSprite("flag");
	
	public Flag(float x, float y) {
		super(x, y);

		this.id = ID.Flag;
		
		this.x -= 6;
		this.y -= 3;
	}

	@Override
	public void update(float d) {
		
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(texture, x, y);
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			Cell cell = MouseInput.hoveringOverCell;
			if(cell != null) {
				Main.inst.world.settlement.addTask(new MoveTask(cell), false);
			}
		}		
	}

	@Override
	public Sprite getMenuSprite() {
		return texture;
	}

	@Override
	public String getMenuName() {
		return "Flag";
	}

	@Override
	public GameObject getCopy(float x, float y) {
		return new Flag(x, y);
	}

	@Override
	public boolean conintues() {
		return false;
	}

	@Override
	public String getMenuDescription() {
		return "The beacon of your Settlement";
	}
	
	@Override
	public void onPlace(Cell cell) {
		Settlement s = new Settlement(cell.x, cell.y);
		Main.inst.world.start(s);
		
		Main.inst.renderer.ui.getElement("manage-icon").setAvailable(true);
		Main.inst.renderer.ui.getElement("people-icon").setAvailable(true);
		Main.inst.renderer.ui.getElement("tool-icon").setAvailable(true);
		Main.inst.renderer.ui.getElement("path-icon").setAvailable(true);
		
	}
	
	@Override
	public RCObject save(RCObject object) {
		return object;	
	}

	@Override
	public void load(RCObject object) {
		Main.inst.world.objects.add(this);		
	}

	@Override
	public boolean available() {
		return false;
	}
}