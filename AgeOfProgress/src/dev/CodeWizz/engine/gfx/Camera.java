package dev.CodeWizz.engine.gfx;

import java.util.Random;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.main.AgeOfProgress;

public class Camera {

	private int x, y;
	private static int counter = 0;
	private static boolean shake = false;
	private static int effect;
	private Random r;
	
	public Camera() {
		r = new Random();
	}
	
	public void update(GameContainer gc) {
		x = (int) AgeOfProgress.inst.cam.getPosition().getX();
		y = (int) AgeOfProgress.inst.cam.getPosition().getY();
		
		if(shake) {
			if(counter > 0)
				counter--;
			else
				shake = false;
			
			if(counter > 15) {
				x += r.nextInt(effect*2) - effect;
				y += r.nextInt(effect*2) - effect;
			} else if (counter > 10){
				x += r.nextInt(effect) - effect/2;
				y += r.nextInt(effect) - effect/2;
			} else if(counter > 5) {
				x += r.nextInt(effect/2) - effect/4;
				y += r.nextInt(effect/2) - effect/4;
			}

			
		}
		
		
		
		gc.getRenderer().setCamX(x);
		gc.getRenderer().setCamY(y);
	}
	
	public static void shake(int duration, int effect) {
		counter = duration;
		Camera.effect = effect;
		Camera.shake = true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
