package dev.codewizz.engine.renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.codewizz.engine.object.GameObject;
import dev.codewizz.engine.object.component.SpriteRenderer;

public class Renderer {
	
	private List<SpriteRenderer> sprites;
	
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;

    public Renderer() {
    	this.sprites = new CopyOnWriteArrayList<>();
        this.batches = new ArrayList<>();
    }

    public void add(GameObject go) {
        SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
        if (spr != null) {
            add(spr);
        }
    }

    private void add(SpriteRenderer sprite) {
    	sprites.add(sprite);
    }
    
    public void destroyGameObject(GameObject go) {
        if (go.getComponent(SpriteRenderer.class) == null) return;
        removeSprite(go.getComponent(SpriteRenderer.class));
    }
    
    public void removeSprite(SpriteRenderer s) {
    	sprites.remove(s);
    }
    
    private void sortBatches() {
    	
    	for(int i = -2; i < 3; i++) {
    		ArrayList<SpriteRenderer> temp = new ArrayList<>();
    		for(SpriteRenderer spriteRenderer : sprites) {
    			if(spriteRenderer.gameObject.zIndex() == i && spriteRenderer.isEnabled()) {
    				temp.add(spriteRenderer);
    			}
    		}
    		
    		Collections.sort(temp);
    		Collections.sort(batches);
    		
    		for(SpriteRenderer r : temp) {
    			
    			boolean added = false;
    			
    			for(RenderBatch batch : batches) {
    				if (batch.hasRoom() && batch.zIndex() == i) {
    	                Texture tex = r.getTexture();
    	                if (tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())) {
    	                    batch.addSprite(r); 
    	                    added = true;
    	                    break;
    	                }
    	            }
    			}
    			
    			if(!added) {
    				RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, i, this);
    				newBatch.start();
    				batches.add(newBatch);
    				newBatch.addSprite(r);
    			}
    		}
    	}
    	
    }
    			
    private void resetBatches( ) {
    	for(RenderBatch b : batches) {
    		b.reset();
    	}
    }
    
    private void renderBatches( ) {
    	for(RenderBatch b : batches) {
    		b.render();
    	}
    }

    public void render() {
    	resetBatches();
    	sortBatches();
    	renderBatches();
    }
}