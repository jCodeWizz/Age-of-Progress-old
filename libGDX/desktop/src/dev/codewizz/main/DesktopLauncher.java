package dev.codewizz.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		
		config.setTitle("Age of Progress | HPG");
		config.setForegroundFPS(60);
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.useVsync(true);
		
		setupTextures();
		
		new Lwjgl3Application(new Main(), config);
	}
	
	private static void setupTextures() {
		Settings settings = new Settings();
		settings.maxWidth = 512;
		settings.maxHeight = 512;
		TexturePacker.process(settings, "../assets/textures/tiles", "../assets/packs", "tiles");
	}
}
