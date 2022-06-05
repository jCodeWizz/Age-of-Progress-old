package dev.CodeWizz.main.objects.environment.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.main.objects.environment.Cell;
import dev.CodeWizz.main.objects.environment.Tile;
import dev.CodeWizz.main.objects.environment.TileType;

public abstract class PathTile extends Tile {

	private static Image texture = Textures.get("path-tile");
	private static Image textureTL = Textures.get("path-tile-tl");
	private static Image textureTR = Textures.get("path-tile-tr");
	private static Image textureBR = Textures.get("path-tile-br");
	private static Image textureBL = Textures.get("path-tile-bl");

	private boolean[] neighbours = new boolean[] { false, false, false, false };

	protected Image d = null;
	private Image t;
	private BufferedImage b;

	public PathTile(int x, int y, Cell cell) {
		super(x, y, cell);

		this.type = TileType.Stone;

		t = Textures.get("base-tile");
		b = new BufferedImage(t.getW(), t.getH(), BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public void tick(GameContainer gc) {
	}

	@Override
	public void onPlace() {
		this.neighbours = this.checkNeighbours();

		super.onPlace();
		this.setupTexture();
	}

	public void setupTexture() {
		Graphics g = b.createGraphics();

		g.drawImage(texture.getImage(), 0, 0, null);
		
		if (neighbours[0])
			g.drawImage(textureTL.getImage(), 0, 0, null);
		if (neighbours[1])
			g.drawImage(textureTR.getImage(), 0, 0, null);
		if (neighbours[2])
			g.drawImage(textureBR.getImage(), 0, 0, null);
		if (neighbours[3])
			g.drawImage(textureBL.getImage(), 0, 0, null);
		
		t = new Image(b);
		
		for(int yy = 0; yy < t.getH(); yy++) {
			for(int xx = 0; xx < t.getW(); xx++) {
				if(t.getP()[xx + yy * t.getW()] == 0xffff00ff) {
					t.getP()[xx + yy * t.getW()] = d.getP()[xx + yy * t.getW()];
				}
			}
		}
	}

	@Override
	public void update() {
		this.neighbours = this.checkNeighbours();
		this.setupTexture();
	}

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(t, x - (w / 2), y - (h / 2));
	}
}
