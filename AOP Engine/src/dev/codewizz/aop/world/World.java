package dev.codewizz.aop.world;

import org.joml.Vector2f;

import dev.codewizz.aop.components.TileType;
import dev.codewizz.aop.components.tiles.GrassTile;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.GameObject;
import dev.codewizz.engine.gameobject.Transform;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.util.AssetPool;

public class World {
	
	public World() {
		
	}
	
	public void setTile(TileType tile) {
		if(tile == TileType.GrassTile) {
			GameObject object = new GameObject("GrassTile", new Transform(new Vector2f(100, 100), new Vector2f(120, 90)));
			object.addComponent(new GrassTile());
			object.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/base-tile.png"))));
			Window.getScene().addGameObjectToScene(object);
			System.out.println("E");
		}
	}
}
