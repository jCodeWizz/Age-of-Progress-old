package dev.codewizz.aop.component;

import java.awt.event.KeyEvent;
import java.util.Random;

import dev.codewizz.engine.Window;
import dev.codewizz.engine.input.KeyListener;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.renderer.Camera;

public class CameraController extends Component {

	public static final float SPEED = 100f, ZOOM_SPEED = 5f;
	public Camera sceneCamera;
	public float zoom = 200f;
	
	private float shakeCounter = 0;
	private float shakeEffect = 0;
	private boolean shaking = false;
	private Random r;
	
	@Override
	public void start() {
		sceneCamera = Window.getScene().getCamera();
		sceneCamera.setZoom(200);
		
		r = new Random();
	}

	@Override
	public void update(float dt) {
		if(shaking) {
			if(shakeCounter > 0f) {
				shakeCounter -= 1*dt;
			} else {
				shaking = false;
			}
			
			if(shakeCounter > 15) {
				sceneCamera.position.x += (r.nextFloat() * 2 - 1) * shakeEffect;
				sceneCamera.position.y += (r.nextFloat() * 2 - 1) * shakeEffect;
			} else if (shakeCounter > 10){
				sceneCamera.position.x += (r.nextFloat() * 2 - 1) * shakeEffect;
				sceneCamera.position.y += (r.nextFloat() * 2 - 1) * shakeEffect;
			} else if(shakeCounter > 5) {
				sceneCamera.position.x += (r.nextFloat() * 2 - 1) * shakeEffect;
				sceneCamera.position.y += (r.nextFloat() * 2 - 1) * shakeEffect;
			} else {
				sceneCamera.position.x += (r.nextFloat() * 2 - 1) * shakeEffect;
				sceneCamera.position.y += (r.nextFloat() * 2 - 1) * shakeEffect;
			}
		}
		
		if(!isEnabled())
			return;
		
		if(KeyListener.isKeyPressed(KeyEvent.VK_A)) {
			sceneCamera.position.x -= SPEED * dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_D)) {
			sceneCamera.position.x += SPEED * dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_W)) {
			sceneCamera.position.y += SPEED * dt;
		}
		if(KeyListener.isKeyPressed(KeyEvent.VK_S)) {
			sceneCamera.position.y -= SPEED * dt;
		}
		
		if(MouseListener.getScrollY() != 0) {
			zoom += MouseListener.getScrollY() * ZOOM_SPEED;
		}
		
		sceneCamera.setZoom(zoom);
	}
	
	public void shake(float duration, float i) {
		shakeCounter = duration;
		shakeEffect = i;
		shaking = true;
	}
	
	public boolean isShaking() {
		return shaking;
	}
}
