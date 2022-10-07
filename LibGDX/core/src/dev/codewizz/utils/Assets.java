package dev.codewizz.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Assets {

	public static HashMap<String, Sprite> sprites = new HashMap<>();
	public static HashMap<String, TextureAtlas> atlasses = new HashMap<>();
	public static HashMap<String, BufferedImage> images = new HashMap<>();
	public static HashMap<String, Texture> procuderal = new HashMap<>();

	public static void create() {
		atlasses.put("tiles", new TextureAtlas(Gdx.files.internal("packs/tiles.atlas")));
		atlasses.put("ui", new TextureAtlas(Gdx.files.internal("packs/ui.atlas")));
		atlasses.put("paths", new TextureAtlas());

		sprites.put("base-tile", atlasses.get("tiles").createSprite("base-tile"));
		sprites.put("dirt-path-tile", atlasses.get("tiles").createSprite("dirt-path-tile"));
		sprites.put("dirt-tile", atlasses.get("tiles").createSprite("dirt-tile"));
		sprites.put("empty-tile", atlasses.get("tiles").createSprite("empty-tile"));
		sprites.put("tiled-tile", atlasses.get("tiles").createSprite("tiled-tile"));
		
		sprites.put("icon", atlasses.get("ui").createSprite("icon"));
		sprites.put("icon-pressed", atlasses.get("ui").createSprite("icon-pressed"));
		sprites.put("icon-unavailable", atlasses.get("ui").createSprite("icon-unavailable"));
		sprites.put("build-icon", atlasses.get("ui").createSprite("build-icon"));
		sprites.put("close-icon", atlasses.get("ui").createSprite("close-icon"));
		sprites.put("icon-board", atlasses.get("ui").createSprite("icon-board"));
		sprites.put("manage-icon", atlasses.get("ui").createSprite("manage-icon"));
		sprites.put("path-icon", atlasses.get("ui").createSprite("path-icon"));
		sprites.put("people-icon", atlasses.get("ui").createSprite("people-icon"));
		sprites.put("tool-icon", atlasses.get("ui").createSprite("tool-icon"));
		
		addImage("../assets/textures/procuderal/path-tile.png", "t");
		addImage("../assets/textures/procuderal/path-tile-TL.png", "tTL");
		addImage("../assets/textures/procuderal/path-tile-TR.png", "tTR");
		addImage("../assets/textures/procuderal/path-tile-BR.png", "tBR");
		addImage("../assets/textures/procuderal/path-tile-BL.png", "tBL");
		addImage("../assets/textures/tiles/tiled-tile.png", "tiled");
		addImage("../assets/textures/tiles/base-tile.png", "grass");
		
		procuderal.put("grass", new Texture(Gdx.files.internal("../assets/textures/tiles/base-tile.png")));
		procuderal.put("tiled", new Texture(Gdx.files.internal("../assets/textures/tiles/tiled-tile.png")));
	}
	
	private static void addImage(String path, String name) {
		BufferedImage image = null;
		try {
			FileHandle s = Gdx.files.internal(path);
			image = ImageIO.read(s.file());
		} catch (IOException e) {
			e.printStackTrace();
		}
		images.put(name, image);
	}
	
	public static BufferedImage getImage(String name) {
		return images.get(name);
	}
	
	
	
	
	public static TextureAtlas getAtlas(String s) {
		if(atlasses.containsKey(s)) {
			return atlasses.get(s);
		} else {
			return null;
		}
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
		for(TextureAtlas ta : atlasses.values()) {
			ta.dispose();
		}
	}
}
