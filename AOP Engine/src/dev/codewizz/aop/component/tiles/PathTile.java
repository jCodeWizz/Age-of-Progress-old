package dev.codewizz.aop.component.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dev.codewizz.aop.world.Cell;
import dev.codewizz.engine.object.component.SpriteRenderer;
import dev.codewizz.engine.renderer.Sprite;
import dev.codewizz.engine.renderer.Texture;
import dev.codewizz.engine.util.AssetPool;

public abstract class PathTile extends Tile {

	private static BufferedImage texture = AssetPool.getBufferedImage("assets/textures/environment/path-tile.png");
	private static BufferedImage textureTL = AssetPool.getBufferedImage("assets/textures/environment/path-tile-TL.png");
	private static BufferedImage textureTR = AssetPool.getBufferedImage("assets/textures/environment/path-tile-TR.png");
	private static BufferedImage textureBR = AssetPool.getBufferedImage("assets/textures/environment/path-tile-BR.png");
	private static BufferedImage textureBL = AssetPool.getBufferedImage("assets/textures/environment/path-tile-BL.png");
	
	private boolean[] connections;
	
	private BufferedImage b;
	private BufferedImage r;
	
	public PathTile(int i, int j, Cell cell) {
		super(i, j, cell);
		
		r = AssetPool.getBufferedImage("assets/textures/environment/grasstile.png");
	}
	
	@Override
	public void onPlace() {
		connections = this.checkNeighbours();
		super.onPlace();
		updateTexture();
	}
	
	@Override
	public void onUpdate() {
		this.connections = this.checkNeighbours();
		this.updateTexture();
	}
	
	@Override
	public void update(float dt) {

	}
	
	public abstract BufferedImage getFullImage();
	
	private void updateTexture() {
		if(AssetPool.textures.containsKey(this.getClass().getName() + connections[0] + connections [1] + connections[2] + connections[3])) {
			System.out.println("E: " + this.getClass().getName() + connections[0] + connections [1] + connections[2] + connections[3]);
			gameObject.getComponent(SpriteRenderer.class).setSprite(new Sprite(AssetPool.getGenTexture(this.getClass().getName() + connections[0] + connections [1] + connections[2] + connections[3])));
			return;
		}	
		b = new BufferedImage(r.getWidth(), r.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = b.createGraphics();

		g.drawImage(texture, 0, 0, null);
		
		if (connections[0])
			g.drawImage(textureTL, 0, 0, null);
		if (connections[1])
			g.drawImage(textureTR, 0, 0, null);
		if (connections[2])
			g.drawImage(textureBR, 0, 0, null);
		if (connections[3])
			g.drawImage(textureBL, 0, 0, null);
		
		
		for(int yy = 0; yy < b.getHeight(); yy++) {
			for(int xx = 0; xx < b.getWidth(); xx++) {
				int rgb = b.getRGB(xx, yy);
				int red = (rgb >> 16) & 0xff;
				int green = (rgb >> 8) & 0xff;
				int blue = (rgb) & 0xff;
				
				if(red == 255 && blue == 255 && green == 0) {
					r.setRGB(xx, yy, getFullImage().getRGB(xx, yy));
				}
			}
		}
		
		File output = new File("output.png");
		try {
			output.createNewFile();
			ImageIO.write(r, "PNG", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Texture t = AssetPool.getTextureFromBufferedImage(r);
		AssetPool.textures.put(this.getClass().getName() + connections[0] + connections[1] + connections[2] + connections[3], t);
		gameObject.getComponent(SpriteRenderer.class).setSprite(new Sprite(t));
	}
}
