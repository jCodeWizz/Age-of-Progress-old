package dev.codewizz.engine.renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;

public class Renderer {

	private final int MAX_BATCH_SIZE = 10000;
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
				
				
				if(tex == null || batch.hasTexture(sprite.getTexture()) || batch.hasTextureRoom()) {
					batch.addSprite(sprite);
					Collections.sort(batch.sprites);
					added = true;
					break;
				}
			}
		}
		
		if(!added) {
			RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.getZIndex(), this);
			newBatch.start();
			batches.add(newBatch);
			newBatch.addSprite(sprite);
			Collections.sort(batches);
		}
	}
	
	public void destroyGameObject(GameObject go) {
        if (go.getComponent(SpriteRenderer.class) == null) return;
        for (RenderBatch batch : batches) {
            if (batch.destroyIfExists(go)) {
                return;
            }
        }
    }
	
	public void render() {
		for(RenderBatch batch : batches) {
			batch.render();
		}
	}
}
