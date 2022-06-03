package dev.CodeWizz.engine.object;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.particles.Particle;
import dev.CodeWizz.engine.util.Vector;

public abstract class GameObject {

	protected float w = 16, h = 16;
	protected ID id;
	protected float gravity = 9.81f/20f, friction = -0.1f, bounce = -0.5f, mass = 20, airFrictionY = 0.2f, airFrictionX = 0.05f;
	protected boolean falling, jumping;
	protected float health;
	
	protected Vector position, speed, acc;
	protected List<Vector> forces = new CopyOnWriteArrayList<>();
	
	protected boolean hurt;
	protected int hurtTime,  offsetHitboxes = 2;
	
	protected ArrayList<ID> gameObjectCollisionID = new ArrayList<>();
	protected ArrayList<String> tags = new ArrayList<>();
	protected boolean canMove;


	public GameObject(float x, float y) {
		position = new Vector(x, y);
		speed = new Vector();
		acc = new Vector();
	}
	
	public void destroy(GameContainer gc) {
		gc.handler.removeObject(this);
	}
	
	public void die(GameContainer gc) {
		destroy(gc);
	}
	
	public void damage(GameContainer gc, float damage) {
		if(health - damage > 0) {
			health-=damage;
			hurt = true;
			hurtTime = 5;
		} else
			die(gc);
	}
	
	public void bloodParticles(int x, int y) {
		Particle.add(new Particle(x, y, 0xff913f3f, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xffc96161, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xff802222, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xffab2e2e, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xffa64141, 3, 60, 1));
	}
	
	public void collided(GameContainer gc, GameObject object) {
		
	}
	
	public boolean hasTag(String tag) {
		if(tags.contains(tag))
			return true;
		
		return false;
	}
	
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	public boolean removeTag(String tag) {
		if(tags.remove(tag)) {
			return true;
		}
		
		return false;
	}
	
	public void tick(GameContainer gc) {
		if(hurtTime > 0) {
			hurtTime--;
		} else {
			hurt = false;
		}
		
		if(canMove) {
			
			acc.clear();
			
			if(!forces.isEmpty()) {
				for(Vector vec : forces) {
					acc.add(vec);
				}
				forces.clear();
			}
			
			if(falling) {
				forces.add(new Vector(0, mass * gravity));
			} else {
				forces.add(new Vector(speed.x*friction*mass, 0));
			}
			
			// LUCHT WEERSTAND
			
			if(speed.y > 0) {
				forces.add(new Vector(0, 0.5f*-airFrictionY*speed.y*speed.y));
			} else {
				forces.add(new Vector(0, 0.5f*airFrictionY*speed.y*speed.y));
			}
			
			if(speed.x > 0) {
				forces.add(new Vector(0.5f*-airFrictionX*speed.x*speed.x, 0));
			} else {
				forces.add(new Vector(0.5f*airFrictionX*speed.x*speed.x, 0));
			}
			
			acc.devide(mass);
			speed.add(acc);
			
			collisionX(gc);
			collisionY(gc);
		
			if(speed.x > -0.1f && speed.x < 0.1f)
				speed.x = 0;
			
			if(speed.y > -0.1f && speed.y < 0.1f)
				speed.y = 0;
		}
	}
	
	private void collisionX(GameContainer gc) {
		boolean collided = false;
		if (speed.x > 0) {
			for (int i = 0; i < (int)speed.x; i++) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId()) && !object.equals(this)) {
						if (new Rectangle((int) position.x + 1, (int) position.y, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							collided(gc, object);
							continue;
						}
					}
				}

				if (collided) {
					
					forces.add(new Vector(speed.x * bounce * mass, 0));
					speed.x = 0;
					continue;
				} else {
					position.x++;
				}
			}
		} else if (speed.x < 0) {
			for (int i = 0; i > (int)speed.x; i--) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId()) && !object.equals(this)) {
						if (new Rectangle((int) position.x - 1, (int) position.y, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							collided(gc, object);
							continue;
						}
					}
				}

				if (collided) {
					forces.add(new Vector(speed.x * bounce * mass, 0));
					speed.x = 0;
					continue;
				} else {
					position.x--;
				}
			}
		}
	}
	
	private void collisionY(GameContainer gc) {
		boolean collided = false;

		if (speed.y >= 1) {
			for (int i = 0; i < (int)speed.y; i++) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId()) && !object.equals(this)) {
						if (new Rectangle((int) position.x, (int) position.y + 1, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							collided(gc, object);
							continue;
						}
					}
				}

				if (collided) {
					forces.add(new Vector(0, speed.y * bounce * mass));
					speed.y = 0;
					falling = false;
					continue;
				} else {
					position.y++;
					falling = true;
				}
			}
		} else if (speed.y <= -1) {
			for (int i = 0; i > (int)speed.y; i--) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId()) && !object.equals(this)) {
						if (new Rectangle((int) position.x, (int) position.y - 1, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							collided(gc, object);
							continue;
						}
					}
				}

				if (collided) {
					forces.add(new Vector(0, speed.y * bounce * mass));
					speed.y = 0;
					falling = false;
					continue;
				} else {
					position.y--;
					falling = true;
				}
			}
		} else if(speed.y > - 1 && speed.y < 1 && gravity != 0) {
			for (GameObject object : gc.handler.object) {
				if (gameObjectCollisionID.contains(object.getId()) && !object.equals(this)) {
					if (new Rectangle((int) position.x, (int) position.y + 1, (int) w, (int) h).intersects(object.getBounds())) {
						collided = true;
						collided(gc, object);
						continue;
					}
				}
			}

			if (collided) {
				speed.y = 0;
				falling = false;
				return;
			} else {
				position.y++;
				falling = true;
			}
		}
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)position.x, (int)position.y+offsetHitboxes, (int) (w/2), (int) h - offsetHitboxes*2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int)position.x + (int) (w/2), (int)position.y+offsetHitboxes, (int) (w/2), (int) h - offsetHitboxes*2);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int)position.x + offsetHitboxes, (int)position.y + (int) (h/2), (int) w - offsetHitboxes*2, (int) (h/2));
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int)position.x + offsetHitboxes, (int)position.y, (int) w - offsetHitboxes*2, (int) (h/2));
	}

	public abstract void render(GameContainer gc, Renderer r);

	public Rectangle getBounds() {
		return new Rectangle((int) position.x, (int) position.y, (int) w, (int) h);
	}

	public Vector getPosition() {
		return position;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public Vector getSpeed() {
		return speed;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public ArrayList<ID> getGameObjectCollisionID() {
		return gameObjectCollisionID;
	}

	public void setGameObjectCollisionID(ArrayList<ID> gameObjectCollisionID) {
		this.gameObjectCollisionID = gameObjectCollisionID;
	}

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public float getBounce() {
		return bounce;
	}

	public void setBounce(float bounce) {
		this.bounce = bounce;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getAirFrictionY() {
		return airFrictionY;
	}

	public void setAirFrictionY(float airFrictionY) {
		this.airFrictionY = airFrictionY;
	}

	public float getAirFrictionX() {
		return airFrictionX;
	}

	public void setAirFrictionX(float airFrictionX) {
		this.airFrictionX = airFrictionX;
	}

	public List<Vector> getForces() {
		return forces;
	}

	public void setForces(List<Vector> forces) {
		this.forces = forces;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}
}
