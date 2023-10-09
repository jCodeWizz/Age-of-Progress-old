package dev.codewizz.world.objects;

import java.awt.Polygon;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.gfx.Animation;
import dev.codewizz.gfx.gui.UILayer;
import dev.codewizz.gfx.gui.UIText;
import dev.codewizz.gfx.gui.menus.SelectMenu;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Direction;
import dev.codewizz.utils.Utils;
import dev.codewizz.utils.serialization.RCField;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.world.Serializable;
import dev.codewizz.world.objects.buildings.Building;
import dev.codewizz.world.settlement.Settlement;

public class Hermit extends TaskableObject implements Serializable {

	private float walkAnimSpeed = 0.1f;
	
	private HashMap<Direction, Animation> animations = new HashMap<>();
	private HashMap<Direction, Sprite> directions = new HashMap<>();
	private Direction dir = Direction.South;

	private Animation taskAnimation;
	private Animation currentAnimation;
	private Sprite currentDirection;
	
	private Settlement s;
	private Building home;
	
	private float intelligence = 1f;
	private float strength = 1f;
	//private float speed = 1f;
	private float willpower = 1f;
	private float sleep = 1f;
	private float healthy = Utils.RANDOM.nextFloat();
	private float body = Utils.RANDOM.nextFloat();
	private float social = 1f;
	private float hunger = 1f;
	private float thirst = 1f;
	
	private float sleepNeed = 10f;
	
	private int age = 10;
	
	public Hermit(float x, float y) {
		super(x, y);
		this.id = ID.Hermit;
		this.name= Utils.getRandomName();
		
		this.w = 24;
		this.h = 36;
		this.health = 10f;
		
		this.speed = 20f;
		
		createAnimations();
		
		setMaxHealth(0f);
		setSleepNeed();
	}
	
	@Override
	public void update(float d) {
		super.update(d);
		
		if(this.currentTask == null && s.getTasks().notEmpty()) {
			this.addTask(s.getTasks().removeFirst(), false);
		}
		
		if(currentAnimation != null) {
			currentAnimation.tick(d);
		}
		
		if(taskAnimation != null) {
			taskAnimation.tick(d);
		}
		
		if(this.getAgent().moving) {
			dir = Utils.getDirFromVector(vel);	
			currentAnimation = animations.get(dir);
		} else {
			currentDirection = directions.get(dir);
		}
	}
	
	

	@Override
	public void render(SpriteBatch b) {
		
		if(this.taskAnimation != null) {
			b.draw(this.taskAnimation.getFrame(), x + this.taskAnimation.getX(), y + this.taskAnimation.getY(), this.taskAnimation.getFrame().getWidth()*0.6f, this.taskAnimation.getFrame().getHeight()*0.6f);
		} else {
			if(this.getAgent().moving) {
				if(currentAnimation != null) {
					b.draw(currentAnimation.getFrame(), x, y, w, h);
				}
			} else {
				if(currentDirection != null) {
					b.draw(currentDirection, x, y, w, h);
				}
			}
		}
	}
	
	@Override
	public Polygon getHitBox() {
		return new Polygon( new int[] {(int)x+7, (int)x+7, (int)x + 17, (int)x + 17}, 
							new int[] {(int)y + 3, (int)y + 24, (int)y + 24, (int)y + 3}, 4) ;
	}

	private SelectMenu m;
	private UIText nameText;
	private UIText taskText = new UIText("task-text", (6 + 3) * UILayer.SCALE,  (6+50) * UILayer.SCALE, 10, 10, "", 8);
	private UIText moodText = new UIText("mood-text", (6 + 3) * UILayer.SCALE,  (6+80) * UILayer.SCALE, 10, 10, "", 8);
	
	
	@Override
	public void renderUICard(SelectMenu m) {
		this.m = m;
		nameText = (UIText)m.layer.getElement("name-text");
		m.elements.add(moodText);
		m.elements.add(taskText);
	}
	
	@Override
	public void updateUICard() {
		if(nameText != null) nameText.setText(name + "   ( " + age + " )");
		else nameText = (UIText) m.layer.getElement("name-text");
		moodText.setText("mood");
		
		if(this.getCurrentTask() == null) {
			taskText.setText("Task >> Idle");
		} else {
			taskText.setText("Task >> " + this.getCurrentTask().getName());
		}
	}

	public Settlement getSettlement() {
		return s;
	}

	public void setSettlement(Settlement s) {
		this.s = s;
	}

	@Override
	public RCObject save(RCObject object) {
		object.addField(RCField.Float("health", health));
		
		return object;
	}

	@Override
	public void load(RCObject object) {
		this.health = object.findField("health").getFloat();
		
		Main.inst.world.objects.add(this);
		Main.inst.world.settlement.addHermit(this);
	}
	
	@Override
	public void setMaxHealth(float maxHealth) {
		float a = this.getMaxHealth();
		
		this.maxHealth = 50f + ((healthy+0.5f) * 20f) + ((body + 0.1f) * 50f);
		
		float d = this.getMaxHealth() / a;
		this.setHealth(this.getHealth() * d);
	}
	
	public void setSleepNeed() {
		this.sleepNeed = sleep * 100f;
	}

	public float getSleepNeed() {
		return sleepNeed;
	}

	public void setSleepNeed(float sleepNeed) {
		this.sleepNeed = sleepNeed;
	}

	public Building getHome() {
		
		if(this.home == null) {
			
			for(Building b : s.homes) {
				if(!b.isFull()) {
					b.owned.add(this);
					this.home = b;
					return b;
				}
			}
			return null;
		} else {
			return home;
		}
	}

	public void setHome(Building home) {
		this.home = home;
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
	
	public void setFacing(Direction dir) {
		this.dir = dir;
	}

	public Animation getTaskAnimation() {
		return taskAnimation;
	}

	public void setTaskAnimation(Animation taskAnimation) {
		this.taskAnimation = taskAnimation;
	}
}
