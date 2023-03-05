package dev.codewizz.world.objects;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.gfx.Animation;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Direction;
import dev.codewizz.utils.Utils;
import dev.codewizz.world.Cell;
import dev.codewizz.world.objects.tasks.MoveTask;

public class Hermit extends TaskableObject {

	private float walkAnimSpeed = 0.1f;
	
	private HashMap<Direction, Animation> animations = new HashMap<>();
	private HashMap<Direction, Sprite> directions = new HashMap<>();
	private Direction dir = Direction.South;

	private Animation currentAnimation;
	private Sprite currentDirection;
	
	
	public Hermit(float x, float y) {
		super(x, y);

		this.id = ID.Hermit;
		
		this.w = 32;
		this.h = 32;
		this.health = 10f;
		
		this.speed = 20f;
		
		createAnimations();
	}
	
	@Override
	public void update(float d) {
		super.update(d);
		
		if(currentAnimation != null) {
			currentAnimation.tick(d);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.TAB)) {
			Cell cell = MouseInput.hoveringOverCell;
			if(cell != null) {
				tree.addLast(new MoveTask(cell));
			}
		}
		
		if(this.getAgent().moving) {
			dir = Utils.getDirFromVector(vel);	
			currentAnimation = animations.get(dir);
		} else {
			currentDirection = directions.get(dir);
		}
		
	}
	
	private void createAnimations() {
		Sprite[] S = new Sprite[3];
		S[0] = Assets.getSprite("down-hermit-1");
		S[1] = Assets.getSprite("down-hermit-2");
		S[2] = Assets.getSprite("down-hermit-3");
		
		Sprite[] SW = new Sprite[3];
		SW[0] = Assets.getSprite("down-left-hermit-1");
		SW[1] = Assets.getSprite("down-left-hermit-2");
		SW[2] = Assets.getSprite("down-left-hermit-3");
		
		Sprite[] W = new Sprite[3];
		W[0] = Assets.getSprite("left-hermit-1");
		W[1] = Assets.getSprite("left-hermit-2");
		W[2] = Assets.getSprite("left-hermit-3");
		
		Sprite[] NW = new Sprite[3];
		NW[0] = Assets.getSprite("left-up-hermit-1");
		NW[1] = Assets.getSprite("left-up-hermit-2");
		NW[2] = Assets.getSprite("left-up-hermit-3");
		
		Sprite[] N = new Sprite[3];
		N[0] = Assets.getSprite("up-hermit-1");
		N[1] = Assets.getSprite("up-hermit-2");
		N[2] = Assets.getSprite("up-hermit-3");
		
		Sprite[] NE = new Sprite[3];
		NE[0] = Assets.getSprite("up-right-hermit-1");
		NE[1] = Assets.getSprite("up-right-hermit-2");
		NE[2] = Assets.getSprite("up-right-hermit-3");
		
		Sprite[] E = new Sprite[3];
		E[0] = Assets.getSprite("right-hermit-1");
		E[1] = Assets.getSprite("right-hermit-2");
		E[2] = Assets.getSprite("right-hermit-3");
		
		Sprite[] SE = new Sprite[3];
		SE[0] = Assets.getSprite("right-down-hermit-1");
		SE[1] = Assets.getSprite("right-down-hermit-2");
		SE[2] = Assets.getSprite("right-down-hermit-3");
		
		animations.put(Direction.South, new Animation(walkAnimSpeed, S));
		animations.put(Direction.SouthWest, new Animation(walkAnimSpeed, SW));
		animations.put(Direction.West, new Animation(walkAnimSpeed, W));
		animations.put(Direction.NorthWest, new Animation(walkAnimSpeed, NW));
		animations.put(Direction.North, new Animation(walkAnimSpeed, N));
		animations.put(Direction.NorthEast, new Animation(walkAnimSpeed, NE));
		animations.put(Direction.East, new Animation(walkAnimSpeed, E));
		animations.put(Direction.SouthEast, new Animation(walkAnimSpeed, SE));
		
		directions.put(Direction.South, Assets.getSprite("down-hermit-2"));
		directions.put(Direction.SouthWest, Assets.getSprite("down-left-hermit-2"));
		directions.put(Direction.West, Assets.getSprite("left-hermit-2"));
		directions.put(Direction.NorthWest, Assets.getSprite("left-up-hermit-2"));
		directions.put(Direction.North, Assets.getSprite("up-hermit-2"));
		directions.put(Direction.NorthEast, Assets.getSprite("up-right-hermit-2"));
		directions.put(Direction.East, Assets.getSprite("right-hermit-2"));
		directions.put(Direction.SouthEast, Assets.getSprite("right-down-hermit-2"));
	}

	@Override
	public void render(SpriteBatch b) {
		if(this.getAgent().moving) {
			if(currentAnimation != null) {
				b.draw(currentAnimation.getFrame(), x, y, 30, 45);
			}
		} else {
			if(currentDirection != null) {
				b.draw(currentDirection, x, y, 30, 45);
			}
		}
			
	}

	@Override
	public void renderUICard(SpriteBatch b) {
		
	}
}
