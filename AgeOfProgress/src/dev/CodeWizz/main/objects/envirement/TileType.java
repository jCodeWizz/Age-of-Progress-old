package dev.CodeWizz.main.objects.envirement;

public enum TileType {

	Up("tile-up"),
	Down("tile-down"),
	Left("tile-left"),
	Right("tile-right"),
	Base("base-tile");
	
	private String texture;
	
	public String getTexture() {
		return texture;
	}
	
	TileType(String texture) {
		this.texture = texture;
	}
}
