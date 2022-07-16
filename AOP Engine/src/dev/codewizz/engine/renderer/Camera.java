package dev.codewizz.engine.renderer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
	
	private Matrix4f projectionMatrix, viewMatrix, inverseProjection, inverseView;
	public Vector2f position;
	public float zoom;
	
	private float projectionWidth = 6;
	private float projectionHeight = 3;
	private Vector2f projectionSize = new Vector2f(projectionWidth, projectionHeight);

	
	public Camera(Vector2f position) {
		this.position = position;
		this.zoom = 500f;
		
		this.projectionMatrix = new Matrix4f();
		this.viewMatrix = new Matrix4f();
		this.inverseProjection = new Matrix4f();
		this.inverseView = new Matrix4f();
		
		adjustProjection();
	}
	
	public void adjustProjection() {
		projectionMatrix.identity();
		projectionMatrix.ortho(projectionSize.x - projectionSize.x * zoom, projectionSize.x * zoom, projectionSize.y - projectionSize.y * zoom, projectionSize.y * zoom, 0.0f, 100.0f);		projectionMatrix.invert(inverseProjection);
	
		this.projectionMatrix.invert(inverseProjection);
	}
	
	public Matrix4f getViewMatrix() {
		Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
		Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
		
		this.viewMatrix.identity();
		viewMatrix = viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.0f), cameraFront.add(position.x, position.y, 0.0f), cameraUp);
		
		this.viewMatrix.invert(inverseView);
		
		return this.viewMatrix;
	}
	
	public Matrix4f getInverseProjection() {
		adjustProjection();
		return inverseProjection;
	}

	public Matrix4f getInverseView() {
		adjustProjection();
		return inverseView;
	}

	public Matrix4f getProjectionMatrix() {
		adjustProjection();
		return projectionMatrix;
	}
	
	public void adjustZoom(float value) {
		this.zoom += value;
		this.adjustProjection();
	}
	
	public float getZoom() {
		return this.zoom;
	}
	
	public void setZoom(float zoom) {
		this.zoom = zoom;
		this.adjustProjection();
	}
}
