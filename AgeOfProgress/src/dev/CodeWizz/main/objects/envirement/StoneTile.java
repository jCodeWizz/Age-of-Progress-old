package dev.CodeWizz.main.objects.envirement;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class StoneTile extends Tile {

	private static Image texture = Textures.get("stone-tile");
	private static Image textureTL = Textures.get("stone-tile-tl");
	private static Image textureTR = Textures.get("stone-tile-tr");
	private static Image textureBR = Textures.get("stone-tile-br");
	private static Image textureBL = Textures.get("stone-tile-bl");

	private boolean[] neighbours = new boolean[] { false, false, false, false };

	public StoneTile(int x, int y, Cell cell) {
		super(x, y, cell);

		this.type = TileType.Stone;
	}

	@Override
	public void tick(GameContainer gc) {
	}

	@Override
	public void onPlace() {
		this.neighbours = this.checkNeighbours();
		
		super.onPlace();
	}

	@Override
	public void update() {
		this.neighbours = this.checkNeighbours(TileType.Stone);
	}

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(texture, x - (w / 2), y - (h / 2));

		if (neighbours[0])
			r.drawImage(textureTL, x - (w / 2), y - (h / 2));
		if (neighbours[1])
			r.drawImage(textureTR, x - (w / 2), y - (h / 2));
		if (neighbours[2])
			r.drawImage(textureBR, x - (w / 2), y - (h / 2));
		if (neighbours[3])
			r.drawImage(textureBL, x - (w / 2), y - (h / 2));

	}
}
