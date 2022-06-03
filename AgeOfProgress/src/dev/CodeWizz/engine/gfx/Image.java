package dev.CodeWizz.engine.gfx;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import dev.CodeWizz.engine.gfx.light.Light;

public class Image {

	private int w, h;
	private int[] p;
	private BufferedImage image;
	private boolean alpha = false;
	private int lightBlock = Light.NONE;
	private Image normalMap;
	private boolean hasNormalMap;

	public Image(String path) {

		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		w = image.getWidth();
		h = image.getHeight();

		p = image.getRGB(0, 0, w, h, null, 0, w);
	}

	public Image(BufferedImage image) {

		w = image.getWidth();
		h = image.getHeight();

		p = image.getRGB(0, 0, w, h, null, 0, w);
	}

	private static AffineTransform rotateImageClockWise(BufferedImage image, double angle) {

		double rad = Math.toRadians(angle);
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(rad, imageWidth / 2, imageHeight / 2);

		double offset = (imageWidth - imageHeight) / 2;
		affineTransform.translate(offset, offset);
		return affineTransform;
	}
	
	public static Image rotateImage(Image image, double angle) {
		BufferedImage i = image.image;
		BufferedImage output = new BufferedImage(i.getHeight(), i.getWidth(), i.getType());
		
		AffineTransform affineTransform = rotateImageClockWise(i, angle);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        affineTransformOp.filter(i, output);
        
        return new Image(output);
	}

	public Image(int[] p, int w, int h) {
		this.p = p;
		this.w = w;
		this.h = h;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}

	public boolean isAlpha() {
		return alpha;
	}

	public void setAlpha(boolean alpha) {
		this.alpha = alpha;
	}

	public int getLightBlock() {
		return lightBlock;
	}

	public void setLightBlock(int lightBlock) {
		this.lightBlock = lightBlock;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Image getNormalMap() {
		return normalMap;
	}

	public void setNormalMap(Image normalMap) {
		this.hasNormalMap = true;
		this.normalMap = normalMap;
	}

	public boolean hasNormalMap() {
		return hasNormalMap;
	}

	public void setHasNormalMap(boolean hasNormalMap) {
		this.hasNormalMap = hasNormalMap;
	}
}
