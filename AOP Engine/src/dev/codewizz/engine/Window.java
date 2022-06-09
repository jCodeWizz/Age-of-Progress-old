package dev.codewizz.engine;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_MAXIMIZED;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import dev.codewizz.engine.input.MouseListener;

public class Window {

	private int width, height;
	private String title;
	private long glfwWindow;
	
	private static Window window = null;
	
	private Window() {
		this.width = 1920;
		this.height = 1080;
		
		this.title = "Age of Progress";
	}
	
	public static Window get() {
		if(window == null) {
			window = new Window();
		
		}
		
		return window;
	}
	
	public void init() {
		// SETUP error callback
		GLFWErrorCallback.createPrint(System.err).set();
		
		// INIT GLFW
		if(!glfwInit()) {
			throw new IllegalStateException("Uh oh no GLFW init oops");
		}
		
		// CONFIGURE GLFW
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
		
		// CREATE WINDOW
		glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
		if(glfwWindow == NULL) {
			throw new IllegalStateException("WAVING THRU NO WINDOW");
		}
		
		glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
		glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
		glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
		
		// MAKE OPENGL
		glfwMakeContextCurrent(glfwWindow);
		// ENABLE MAGIC V-SYNC
		glfwSwapInterval(1);
		
		// MAKE THE WINDOW VISIBLE
		glfwShowWindow(glfwWindow);
		
		// important?
		GL.createCapabilities();
	}
	
	public void loop() {
		while (!glfwWindowShouldClose(glfwWindow)) {
			glfwPollEvents();
			
			glClearColor(1f, 0f, 0f, 1f);
			glClear(GL_COLOR_BUFFER_BIT);
			
			glfwSwapBuffers(glfwWindow);
		}
	}
	
	public void run() {
		System.out.println("Hello bitch " + Version.getVersion());
		
		init();
		loop();
		
		// FREE THE MEMORY
		glfwFreeCallbacks(glfwWindow);
		glfwDestroyWindow(glfwWindow);
		
		// Terminate GLFW and error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
}
