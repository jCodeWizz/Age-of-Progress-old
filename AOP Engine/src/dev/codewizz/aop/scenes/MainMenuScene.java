package dev.codewizz.aop.scenes;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import dev.codewizz.engine.renderer.Camera;
import dev.codewizz.engine.renderer.Shader;
import dev.codewizz.engine.renderer.Texture;
import dev.codewizz.engine.scene.Scene;

public class MainMenuScene extends Scene {

	private Shader defaultShader;
	private Texture defaultTexture;
	
	private float[] vertexArray = { 
			// POSITION				// COLOR
			100.5f, -0.5f, 0.0f,		1.0f, 0.0f, 0.0f, 1.0f,		1, 1,
			-0.5f, 100.5f, 0.0f,		0.0f, 1.0f, 0.0f, 1.0f,		0, 0,
			100.5f, 100.5f, 0.0f,		0.0f, 0.0f, 1.0f, 1.0f,		1, 0,
			-0.5f, -0.5f, 0.0f,			1.0f, 1.0f, 0.0f, 1.0f,		0, 1
	};
	
	private int vaoID, vboID, eboID;
	
	// IMPORTANT COUNTER CLOCKWISE ORDER
	private int[] elementArray = {
			2, 1, 0,
			0, 1, 3
	};
	
	public MainMenuScene() {
	}
	
	@Override
	public void update(float dt) {
		defaultShader.use();
		
		defaultShader.uploadTexture("TEX_SAMPLER", 0);
		glActiveTexture(GL_TEXTURE0);
		defaultTexture.bind();
		
		defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
		defaultShader.uploadMat4f("uView", camera.getViewMatrix());
		glBindVertexArray(vaoID);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
		glBindVertexArray(0);
		
		defaultShader.detach();
	}

	@Override
	public void init() {
		this.camera = new Camera(new Vector2f());
		
		defaultShader = new Shader(".//res/assets/shaders/default.glsl");
		defaultShader.compile();
		
		this.defaultTexture = new Texture(".//res/assets/textures/base-tile.png");
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
		vertexBuffer.put(vertexArray).flip();
		
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		
		IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
		elementBuffer.put(elementArray).flip();
		
		eboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);
		
		int positionSize = 3;
		int colorSize = 4;
		int uvSize = 2;
		int vertexSizeBytes = (positionSize + colorSize + uvSize) * Float.BYTES;
		glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
		glEnableVertexAttribArray(0);
		
		glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * Float.BYTES);
		glEnableVertexAttribArray(1);
		
		glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionSize + colorSize) * Float.BYTES);
		glEnableVertexAttribArray(2);
		
	}
}
