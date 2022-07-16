package dev.codewizz.engine.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import dev.codewizz.engine.gameobject.components.SpriteSheet;
import dev.codewizz.engine.renderer.Shader;
import dev.codewizz.engine.renderer.Texture;

public class AssetPool {
	
	private static Map<String, Shader> shaders = new HashMap<>();
	private static Map<String, Texture> textures = new HashMap<>();
	private static Map<String, SpriteSheet> spriteSheets = new HashMap<>();
	
	public static Shader getShader(String resourceName) {
		File file = new File(resourceName);
		if(shaders.containsKey(file.getAbsolutePath())) {
			return shaders.get(file.getAbsolutePath());
		} else {
			Shader shader = new Shader(resourceName);
			shader.compile();
			shaders.put(file.getAbsolutePath(), shader);
			return shader;
		}
	}
	
	public static Texture getTexture(String resourceName) {
		File file = new File(resourceName);
		if(textures.containsKey(file.getAbsolutePath())) {
			return textures.get(file.getAbsolutePath());
		} else {
			Texture texture = new Texture(resourceName);
			textures.put(file.getAbsolutePath(), texture);
			return texture;
		}
	}
	
	public static void addSpriteSheet(String resourceName, SpriteSheet spriteSheet) {
		File file = new File(resourceName);
		if(!spriteSheets.containsKey(file.getAbsolutePath())) {
			spriteSheets.put(file.getAbsolutePath(), spriteSheet);
		}
	}
	
	public static SpriteSheet addSpriteSheet(String resourceName) {
		File file = new File(resourceName);
		if(!spriteSheets.containsKey(file.getAbsolutePath())) {
			assert false : "Error Tried to acces spriteSheet: " + resourceName + " but wasn't found";
		}
		
		return spriteSheets.getOrDefault(file.getAbsolutePath(), null);
	}
}
