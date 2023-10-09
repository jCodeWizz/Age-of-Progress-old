package dev.codewizz.input;

import java.awt.Rectangle;

import com.badlogic.gdx.math.Vector2;

import dev.codewizz.main.Main;
import dev.codewizz.world.GameObject;

public class AreaSelector {

	public Vector2 start;

	public void start(Vector2 start) {
		this.start = start;
	}
	
	

	public void end(Vector2 end) {
		
		if(start.x > end.x) {
			float a = start.x;
			start.x = end.x;
			end.x = a;
		}
		
		if(start.y > end.y) {
			float a = start.y;
			start.y = end.y;
			end.y = a;
		}
		
		int w = (int) (end.x - start.x);
		int h = (int) (end.y - start.y);

		Rectangle rec = new Rectangle((int) start.x, (int) start.y, w, h);

		for (GameObject obj : Main.inst.world.objects) {
			if (obj.getHitBox().intersects(rec)) {
				handle(obj);
			}
		}
	}

	public void handle(GameObject obj) {

	}
}
