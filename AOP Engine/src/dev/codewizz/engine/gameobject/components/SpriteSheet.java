package dev.codewizz.engine.gameobject.components;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import dev.codewizz.engine.renderer.Texture;

public class SpriteSheet {
	
	private Texture texture;
	private List<Sprite> sprites;
	
	public SpriteSheet(Texture texture, int spriteWidth, int spriteHeight, int numSprites, int spacing) {
		this.sprites = new ArrayList<>();
		
		this.texture = texture;
		int currentX = 0;
		int currentY = texture.getHeight() - spriteHeight;
		
		for(int i = 0; i < numSprites; i++) {
			float topY = 0;
			float rightX = 0;
			float leftX = currentX / (float)texture.getWidth();
			float bottomY = currentY / (float)texture.getHeight();
			
			Vector2f[] texCoords = {
					new Vector2f(rightX, topY),
					new Vector2f(rightX, bottomY),
					new Vector2f(leftX, bottomY),
					new Vector2f(leftX, topY)
			};
			
			Sprite sprite = new Sprite(this.texture, texCoords);
			this.sprites.add(sprite);
			
			currentX += spriteWidth + spacing;
			if(currentX >= texture.getWidth()) {
				currentX = 0;
				currentY -= spriteHeight + spacing;
			}
		}
	}
	
	public Sprite getSprite(int index) {
		return this.sprites.get(index);
	}
}
