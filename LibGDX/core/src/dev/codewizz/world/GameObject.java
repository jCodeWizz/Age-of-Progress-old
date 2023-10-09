package dev.codewizz.world;

import java.awt.Polygon;
import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import dev.codewizz.gfx.Renderer;
import dev.codewizz.gfx.gui.layers.GameLayer;
import dev.codewizz.gfx.gui.menus.SelectMenu;
import dev.codewizz.main.Main;
import dev.codewizz.world.objects.ID;

public abstract class GameObject implements Comparable<GameObject> {

	protected float x, y, sortHeight;
	protected int w, h;
	protected ID id;
	
	protected boolean selected = false;
	protected String name = "Object";
	
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
	public void renderUICard(SelectMenu m) {}
	public void updateUICard() {}
	

	public void renderDebug() {
		Polygon g = this.getHitBox();
		
		for(int i = 0; i < g.npoints; i++) {
			Renderer.drawDebugLine(new Vector2(g.xpoints[i % g.npoints], g.ypoints[i % g.npoints]), new Vector2(g.xpoints[(i+1) % g.npoints], g.ypoints[(i+1) % g.npoints]));
		}
	}
	
	public void onDestroy() {};
	
	public void destroy() {
		onDestroy();
		Main.inst.world.objects.remove(this);
	}
	
	public void select() {
		GameLayer.selectedObject = this;
		this.renderUICard((SelectMenu)Main.inst.renderer.ui.getElement("selectMenu"));
		Main.inst.renderer.ui.getElement("selectMenu").enable();
		selected = true;
	}
	
	public void deselect() {
		GameLayer.selectedObject = null;
		Main.inst.renderer.ui.getElement("selectMenu").disable();
		selected = false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, w, h);
	}
	
	public Polygon getHitBox() {
		return new Polygon( new int[] {(int)x + w/2, (int)x, (int)x + w/2, (int)x + w}, new int[] {(int)y, (int)y + h/2, (int)y + h, (int)y + h/2}, 4) ;
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
		if(other.y + other.sortHeight < this.y + this.sortHeight) {
			return -1;
		} else if(other.y + other.sortHeight > this.y + this.sortHeight){
			return 1;
		} else {
			return 0;
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
