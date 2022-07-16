package dev.codewizz.engine.util;

public class FloatMath {

	public static float clamp(float min, float max, float value) {
		if(value < min) {
			return min;
		} else if(value > max) {
			return max;
		} else {
			return value;
		}
	}
	
	public static float distance(float x1, float y1, float x2, float y2) {
		return (float) Math.abs(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
	}
	
	public static float distance(float a, float b) {
		return (float) Math.abs(a - b);
	}
	
	public static float remap(float value, float oldmin, float oldmax, float newmin, float newmax) {
		return((value - oldmin) / (oldmax - oldmin)) * (newmax - newmin) + newmin;
	}
}
