package dev.codewizz.world;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codewizz.main.Main;
import dev.codewizz.world.objects.ID;

public abstract class GameObject implements Comparable<GameObject> {

	protected float x, y;
	protected int w, h;
	protected ID id;
	
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public GameObject(Tile tile) {
		this.x = tile.cell.x;
		this.y = tile.cell.y;
	}
	
	public abstract void update(float d);
	public abstract void render(SpriteBatch b);
	
	public void onDestroy() {};
	
	public void destroy() {
		onDestroy();
		Main.inst.world.objects.remove(this);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, w, h);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
	public ID getID() {
		return id;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public int compareTo(GameObject other) {
		if(other.y < this.y) {
			return -1;
		} else if(other.y > this.y){
			return 1;
		} else {
			return 0;
		}
	}
	
}
