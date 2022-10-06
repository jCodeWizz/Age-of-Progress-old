package dev.codewizz.utils;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Assets {

	public static HashMap<String, Sprite> sprites = new HashMap<>();
	public static HashMap<String, TextureAtlas> atlasses = new HashMap<>();
	
	public static void create() {
		atlasses.put("tiles", new TextureAtlas(Gdx.files.internal("packs/tiles.atlas")));

		sprites.put("base-tile", atlasses.get("tiles").createSprite("base-tile"));
		sprites.put("dirt-path-tile", atlasses.get("tiles").createSprite("dirt-path-tile"));
		sprites.put("dirt-tile", atlasses.get("tiles").createSprite("dirt-tile"));
		sprites.put("empty-tile", atlasses.get("tiles").createSprite("empty-tile"));
		sprites.put("tiled-tile", atlasses.get("tiles").createSprite("tiled-tile"));
		
		
	}
	
	
	
	
	
	
	
	public static void addSprite(String s, Sprite sprite) {
		sprites.put(s, sprite);
	}
	
	public static Sprite getSprite(String s) {
		if(sprites.containsKey(s)) {
			return sprites.get(s);
		} else {
			return null;
		}
	}
	
	public static boolean containsSprite(String s) {
		return sprites.containsKey(s);
	}
	
	public static void dispose() {
		for(Sprite s : sprites.values()) {
			s.getTexture().dispose();
		}
		for(TextureAtlas ta : atlasses.values()) {
			ta.dispose();
		}
	}
}
