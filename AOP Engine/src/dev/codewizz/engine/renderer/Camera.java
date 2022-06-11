package dev.codewizz.engine.renderer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
	
	private Matrix4f projectionMatrix, viewMatrix;
	public Vector2f position;
	public float zoom;
	public static final float zoomConst = 2f;
	
	private float projectionWidth = 6;
	private float projectionHeight = 3;
	private Vector2f projectionSize = new Vector2f(projectionWidth, projectionHeight);

	
	public Camera(Vector2f position) {
		this.position = position;
		this.zoom = 500f;
		
		this.projectionMatrix = new Matrix4f();
		this.viewMatrix = new Matrix4f();
		
		adjustProjection();
	}
	
	public void adjustProjection() {
		projectionMatrix.identity();
		projectionMatrix.ortho(0.0f, projectionSize.x * zoom, 0.0f, projectionSize.y * zoom, 0.0f, 100.0f);
	}
	
	public Matrix4f getViewMatrix() {
		Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
		Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
		
		this.viewMatrix.identity();
		viewMatrix = viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.0f), cameraFront.add(position.x, position.y, 0.0f), cameraUp);
		return this.viewMatrix;
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	public void adjustZoom(float value) {
		this.zoom += value;
		this.adjustProjection();
	}
	
	public float getZoom() {
		return this.zoom;
	}
}
