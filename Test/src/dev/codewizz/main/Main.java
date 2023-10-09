package dev.codewizz.main;

public class Main {

	public static void main(String[] args) {

		for(int i = -64; i < 64; i++) {
			System.out.println(i + " - " + roundHalfUp(i, 16));
		}
		
		
		
		
		
		
	}
	
	public static int roundHalfUp(int value, int multiplier) {
	    return (value + (value < 0 ? multiplier / -2 : multiplier / 2)) / multiplier * multiplier;
	}
}
