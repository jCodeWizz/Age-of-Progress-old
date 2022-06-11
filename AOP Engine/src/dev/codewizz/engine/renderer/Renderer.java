package dev.codewizz.engine.renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;

public class Renderer {

	private final int MAX_BATCH_SIZE = 1000;
	private List<RenderBatch> batches;
	
	public Renderer() {
		this.batches = new ArrayList<>();
	}
	
	public void add(GameObject object) {
		SpriteRenderer spr = object.getComponent(SpriteRenderer.class);
		if(spr != null) {
			add(spr);
		}
	}
	
	private void add(SpriteRenderer sprite) {
		boolean added = false;
		for(RenderBatch batch : batches) {
			if(batch.hasRoom() && batch.getZIndex() == sprite.gameObject.getZIndex()) {
				Texture tex = sprite.getTexture();
				if(tex == null || batch.hasTexture(tex) || batch.hasTextureRoom()) {
					batch.addSprite(sprite);
					added = true;
					break;
				}
			}
		}
		
		if(!added) {
			RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.getZIndex());
			newBatch.start();
			batches.add(newBatch);
			newBatch.addSprite(sprite);
			Collections.sort(batches);
		}
	}
	
	public void render() {
		for(RenderBatch batch : batches) {
			batch.render();
		}
	}
	
}
