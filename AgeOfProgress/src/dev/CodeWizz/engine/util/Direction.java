package dev.CodeWizz.engine.util;

public enum Direction {

	Left(-1),
	Right(1),
	Down(1),
	Up(-1);
	
	private int i;
	
	Direction(int i) {
		this.i = i;
	}
	
	public int getDirection() {
		return i;
	}
}
