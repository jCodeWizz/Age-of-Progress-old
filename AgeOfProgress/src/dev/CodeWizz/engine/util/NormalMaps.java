package dev.CodeWizz.engine.util;

import java.util.HashMap;

import dev.CodeWizz.engine.gfx.Image;

public class NormalMaps {

	private static HashMap<String, Image> list = new HashMap<>();
	
	public void load() {
		WDebug.log("[System]: Loading normal maps...");
		
		WDebug.log("[System]: Loaded in " + list.size() + " normal map!");
	}
	
	public static Image get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			WDebug.log("[ERROR]: NormalMap was requested for name: " + name + " but wasn't found!");
			return null;
		}
		
	}
}
