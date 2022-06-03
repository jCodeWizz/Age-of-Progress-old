package dev.CodeWizz.main.objects;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;

public class Tree extends GameObject {

	public Tree(float x, float y) {
		super(x, y);
		
		
	}
	
	

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect(0, 0, 20, 20, 0xffff00ff, Light.NONE);
	}
}
