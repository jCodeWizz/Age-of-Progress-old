package dev.codewizz.engine.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import dev.codewizz.engine.renderer.Shader;
import dev.codewizz.engine.renderer.Spritesheet;
import dev.codewizz.engine.renderer.Texture;

public class AssetPool {

	public static Map<String, Shader> shaders = new HashMap<>();
	public static Map<String, Texture> textures = new HashMap<>();
	public static Map<String, Spritesheet> spritesheets = new HashMap<>();
	public static Map<String, BufferedImage> bufferedImages = new HashMap<>();

	public static Shader getShader(String r) {
		String resourceName = r;
		File file = new File(resourceName);
		if (AssetPool.shaders.containsKey(file.getAbsolutePath())) {
			return AssetPool.shaders.get(file.getAbsolutePath());
		} else {
			Shader shader = new Shader(resourceName);
			shader.compile();
			AssetPool.shaders.put(file.getAbsolutePath(), shader);
			return shader;
		}
	}

	public static Texture getTextureFromBufferedImage(BufferedImage b) {
		return new Texture(b);
	}

	public static BufferedImage getBufferedImage(String r) {
		File file = new File(r);

		if (AssetPool.bufferedImages.containsKey(file.getAbsolutePath())) {
			return AssetPool.bufferedImages.get(file.getAbsolutePath());
		} else {
			try {
				InputStream s = AssetPool.class.getClassLoader().getResourceAsStream(r);
				BufferedImage image = ImageIO.read(s);
				AssetPool.bufferedImages.put(file.getAbsolutePath(), image);
				return image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Texture getGenTexture(String r) {
		return AssetPool.textures.get(r);
	}

	public static Texture getTexture(String r) {
		String resourceName = r;
		File file = new File(resourceName);
		if (AssetPool.textures.containsKey(file.getAbsolutePath())) {
			return AssetPool.textures.get(file.getAbsolutePath());
		} else {
			Texture texture = new Texture(resourceName);
			AssetPool.textures.put(file.getAbsolutePath(), texture);
			return texture;
		}
	}

	public static void addSpritesheet(String r, Spritesheet spritesheet) {
		String resourceName = r;
		File file = new File(resourceName);
		if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
			AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
		}
	}

	public static Spritesheet getSpritesheet(String r) {
		String resourceName = r;
		File file = new File(resourceName);
		if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
			assert false : "Error: Tried to access spritesheet '" + resourceName
					+ "' and it has not been added to asset pool.";
		}
		return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
	}
}