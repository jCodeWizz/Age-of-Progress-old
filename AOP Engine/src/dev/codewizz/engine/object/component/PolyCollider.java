package dev.codewizz.engine.object.component;

import java.awt.Polygon;

public class PolyCollider extends Collider {

	private Polygon g;
	private float[] offsetsX, offsetsY;
	private int npoints;
	
	
	public PolyCollider(float[] offsetsX, float[] offsetsY, int npoints) {
		this.offsetsX = offsetsX;
		this.offsetsY = offsetsY;
		this.npoints = npoints;
	}
	
	private void updatePolygon() {
		int[] xpoints = new int[offsetsX.length];
		int[] ypoints = new int[offsetsY.length];
		
		for(int i = 0; i < xpoints.length; i++) {
			xpoints[i] = (int)(offsetsX[i] + gameObject.transform.position.x);
			ypoints[i] = (int)(offsetsY[i] + gameObject.transform.position.y);
		}
		
		g = new Polygon(xpoints, ypoints, npoints);
	}
	
	
	
	
	public Polygon getPolygon() {
		return g;
	}
	
	
	@Override
	public boolean contains(float x, float y) {
		updatePolygon();
		return g.contains(x, y);
	}

	@Override
	public boolean intersects(BoxCollider c) {
		return false;
	}

	@Override
	public boolean intersects(CircleCollider c) {
		return false;
	}

	@Override
	public void start() {
		
	}

	@Override
	public void update(float dt) {
		
	}
}
