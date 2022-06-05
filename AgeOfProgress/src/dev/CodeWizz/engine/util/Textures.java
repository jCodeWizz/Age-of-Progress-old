package dev.CodeWizz.engine.util;

import java.util.HashMap;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.ImageTile;

public class Textures {

	private static HashMap<String, Image> list = new HashMap<>();
	private static HashMap<String, ImageTile> listT = new HashMap<>();
	
	public void load() {
		WDebug.log("[System]: Loading textures...");
		
		list.put("tile-highlight1", new Image("/assets/textures/ui/tile-highlight.png"));
		list.put("tile-highlight2", new Image("/assets/textures/ui/tile-highlight2.png"));
		
		list.put("base-tile", new Image("/assets/textures/envirement/base-tile.png"));
		list.put("dirt-tile", new Image("/assets/textures/envirement/dirt-tile.png"));
		list.put("empty-tile", new Image("/assets/textures/envirement/empty-tile.png"));
		list.put("tiled-tile", new Image("/assets/textures/envirement/tiled-tile.png"));
		list.put("dirt-path-tile", new Image("/assets/textures/envirement/dirt-path-tile.png"));

		list.put("path-tile", new Image("/assets/textures/envirement/path-tile.png"));
		list.put("path-tile-tl", new Image("/assets/textures/envirement/path-tile-TL.png"));
		list.put("path-tile-tr", new Image("/assets/textures/envirement/path-tile-TR.png"));
		list.put("path-tile-br", new Image("/assets/textures/envirement/path-tile-BR.png"));
		list.put("path-tile-bl", new Image("/assets/textures/envirement/path-tile-BL.png"));

		list.put("tile-up", new Image("/assets/textures/envirement/tile-up.png"));
		list.put("tile-down", new Image("/assets/textures/envirement/tile-down.png"));
		list.put("tile-left", new Image("/assets/textures/envirement/tile-left.png"));
		list.put("tile-right", new Image("/assets/textures/envirement/tile-right.png"));
		
		WDebug.log("[System]: Loaded in " + list.size() + " textures!");
		WDebug.log("[System]: Loaded in " + listT.size() + " texture tiles!");
	}
	
	public static Image get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			WDebug.log("[ERROR]: Texture requested for name: " + name + " but wasn't found!");
			WDebug.log("[ERROR]: List Size: " + list.size());
			return null;
		}
	}
	
	public static Image get(String name, int x, int y) {
		if(listT.containsKey(name))
			return listT.get(name).getTileImage(x, y);
		else {
			WDebug.log("[ERROR]: Texture tile requested for name: " + name + " on coords {" + x + " ; " + y + "} but wasn't found!");
			return null;
		}
	}
	
}
