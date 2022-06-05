package dev.CodeWizz.main.objects.envirement;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class GrassTile extends Tile {

	private static Image texture = Textures.get("base-tile");
	
	public GrassTile(int x, int y, Cell cell) {
		super(x, y, cell);
		
		this.type = TileType.Grass;
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(texture, x - (w / 2), y - (h / 2));
	}
}
