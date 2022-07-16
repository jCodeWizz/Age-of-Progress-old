package dev.codewizz.aop.components;

import dev.codewizz.engine.gameobject.Component;
import dev.codewizz.engine.gameobject.components.Sprite;
import dev.codewizz.engine.gameobject.components.SpriteRenderer;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.util.AssetPool;

public class Controller extends Component {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		this.gameObject.transform.position.x += dt*20;
		
		if(MouseListener.mouseButtonDown(1)) {
			this.gameObject.getComponent(SpriteRenderer.class).setSprite(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/dirt-tile.png")));
		} else if(MouseListener.mouseButtonDown(0)) {
			this.gameObject.getComponent(SpriteRenderer.class).setSprite(new Sprite(AssetPool.getTexture(".//res/assets/textures/environment/tiled-tile.png")));
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
