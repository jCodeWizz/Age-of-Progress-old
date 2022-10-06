package dev.codewizz.utils;

import com.badlogic.gdx.math.Vector2;

public class Utils {

	public static float distance(float x1, float y1, float x2, float y2) {
		return (float) Math.abs(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
	}
	
	public static float distance(Vector2 a, Vector2 b) {
		return (float) Math.abs(Math.sqrt((b.x - a.x)* (b.x - a.x) + (b.y - a.y) * (b.y - a.y)));
	}
	
	public static float distance(float a, float b) {
		return (float) Math.abs(a - b);
	}
	
	public static float remap(float value, float oldmin, float oldmax, float newmin, float newmax) {
		return((value - oldmin) / (oldmax - oldmin)) * (newmax - newmin) + newmin;
	}
}
