package dev.codewizz.aop.components.tiles;

import dev.codewizz.aop.components.Tile;
import dev.codewizz.aop.components.TileType;
import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.util.AssetPool;

public class GrassTile extends Tile {

	
	public GrassTile() {
		this.sprite = new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/base-tile.png"));
		this.type = TileType.GrassTile;
	}
	
	@Override
	public void update(float dt) {
		 if (MouseListener.getScrollY() != 0.0f) {
	            float addValue = (float)Math.pow(Math.abs(MouseListener.getScrollY() * Camera.zoomConst), 1 / Window.getScene().camera().getZoom());
	            addValue *= -Math.signum(MouseListener.getScrollY());
	            Window.getScene().camera().adjustZoom(addValue);
		 }
	}
}
