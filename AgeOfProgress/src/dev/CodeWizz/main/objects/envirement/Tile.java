package dev.CodeWizz.main.objects.envirement;

import java.awt.Polygon;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.util.Textures;

public class Tile extends GameObject {

	private TileType type;
	
	
	public Tile(float x, float y, TileType type) {
		super(x, y);
		
		this.type = type;
		
		w = 64;
		h = 48;
		
	}

	@Override
	public void tick(GameContainer gc) { }
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(Textures.get(type.getTexture()), (int) (position.x-w/2), (int) (position.y-h/2));
	}
	
	public void render2(GameContainer gc, Renderer r) {
		r.drawPolygon(0xffffffff, getHitbox());
		r.fillRect((int)position.x, (int)position.y, 1, 1, 0xffff0000, Light.NONE);
	}
	
	public Polygon getHitbox() {
		return new Polygon(new int[] { (int)position.x-33, (int) (position.x-1), (int) (position.x + 31), (int)(position.x-1) }, new int[] { (int) (position.y-8), (int)position.y-24, (int) (position.y-8), (int) (position.y + 8)}, 4);
	}
}
