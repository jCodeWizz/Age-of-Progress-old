package dev.codewizz.aop.components;

import java.awt.event.KeyEvent;

import dev.codewizz.engine.Window;
import dev.codewizz.engine.gameobject.Component;
import dev.codewizz.engine.input.KeyListener;
import dev.codewizz.engine.input.MouseListener;

public class CameraComponent extends Component {

	public static final float speed = 500f;
	public static final float zoomSpeed = 50f;
	
	
	@Override
	public void start() {
		
	}

	@Override
	public void update(float dt) {
		
		if (MouseListener.getScrollY() != 0.0f) {
            Window.getScene().camera().adjustZoom(zoomSpeed * MouseListener.getScrollY());
		}
		
		if(KeyListener.isKeyPressed(KeyEvent.VK_A)) {
			this.gameObject.transform.position.x -= speed*dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_D)) {
			this.gameObject.transform.position.x += speed*dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_S)) {
			this.gameObject.transform.position.y -= speed*dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_W)) {
			this.gameObject.transform.position.y += speed*dt;
		}
		
		Window.getScene().camera().position.x = this.gameObject.transform.position.x;
		Window.getScene().camera().position.y = this.gameObject.transform.position.y;
	}
}
