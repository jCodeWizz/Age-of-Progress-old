package dev.codewizz.aop.component;

import java.awt.event.KeyEvent;

import dev.codewizz.engine.Window;
import dev.codewizz.engine.input.KeyListener;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.renderer.Camera;

public class CameraController extends Component {

	public static final float SPEED = 100000f, ZOOM_SPEED = 5f;
	public Camera sceneCamera;
	public float zoom = 200f;
	
	@Override
	public void start() {
		sceneCamera = Window.getScene().getCamera();
		sceneCamera.setZoom(200);
	}

	@Override
	public void update(float dt) {
		if(KeyListener.isKeyPressed(KeyEvent.VK_A)) {
			sceneCamera.position.x -= SPEED / sceneCamera.getZoom() * dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_D)) {
			sceneCamera.position.x += SPEED / sceneCamera.getZoom() * dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_W)) {
			sceneCamera.position.y += SPEED / sceneCamera.getZoom() * dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_S)) {
			sceneCamera.position.y -= SPEED / sceneCamera.getZoom() * dt;
		}
		
		if(MouseListener.getScrollY() != 0) {
			zoom += MouseListener.getScrollY() * ZOOM_SPEED;
		}
		
		sceneCamera.setZoom(zoom);
	}
}
