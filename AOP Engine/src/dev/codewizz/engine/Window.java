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
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
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

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import dev.codewizz.aop.scenes.LevelScene;
import dev.codewizz.aop.scenes.MainMenuScene;
import dev.codewizz.engine.input.KeyListener;
import dev.codewizz.engine.input.MouseListener;
import dev.codewizz.engine.scene.Scene;

public class Window {

	private int width, height;
	private String title;
	private long glfwWindow;
	private float a, r, g, b;
	
	private static Window window = null;
	private static Scene currentScene = null;
	
	private Window() {
		this.width = 1920;
		this.height = 1080;
		
		this.title = "Age of Progress";
		
		this.a = 1f;
		this.r = 0.23137254900196078f;
		this.g = 0.2549019607843137f;
		this.b = 0.2901960784313725f;
	}
	
	public static void changeScene(int newScene) {
		switch(newScene) {
			case 0:
				currentScene = new MainMenuScene();
				currentScene.init();
				currentScene.start();
				break;
			case 1:
				currentScene = new LevelScene();
				currentScene.init();
				currentScene.start();
				break;
			default:
				assert false : "Unkown scene'" + newScene + "'";
				break;
		}
	}
	
	public static Scene getScene() {
		return currentScene;
	}
	
	public static Window get() {
		if(window == null) {
			window = new Window();
		
		}
		
		return window;
	}
	
	public static void showSystemInformation() {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] gs = ge.getScreenDevices();

			System.out.println("");
			System.out.println("------------------------------------------------");
			System.out.println("");
			
			for (int i = 0; i < gs.length; i++) {
				DisplayMode dm = gs[i].getDisplayMode();
				if(gs.length == 1) {
					System.out.println("[System]: Screen Refreshrate = " + dm.getRefreshRate() + "Hz || Screen Resolution = " + dm.getWidth() + "x" + dm.getHeight());
				} else {
					System.out.println("[System]: Screen [" + (i+1) + "] Refreshrate = " + dm.getRefreshRate() + "Hz || Screen Resolution = " + dm.getWidth() + "x" + dm.getHeight());
				}
			}
			
			System.out.println("[System]: Operating System: " + System.getProperty("os.name"));
		 	System.out.println("[System]: Java Version: " + System.getProperty("java.version") + " || OpenGL Version: " + Version.getVersion());
			System.out.println("[System]: Main Path = " + System.getProperty("sun.java.command") + ".java");
			
			System.out.println("");
			System.out.println("------------------------------------------------");
			System.out.println("");
	}
	
	public void init() {
		showSystemInformation();
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
		glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
		
		// MAKE OPENGL
		glfwMakeContextCurrent(glfwWindow);
		// ENABLE MAGIC V-SYNC
		glfwSwapInterval(1);
		
		// MAKE THE WINDOW VISIBLE
		glfwShowWindow(glfwWindow);
		
		// important?
		GL.createCapabilities();
		
		changeScene(0);
	}
	
	public void loop() {
		
		float beginTime = (float)glfwGetTime();
		float endTime = (float)glfwGetTime();
		float dt = -1;
		
		while (!glfwWindowShouldClose(glfwWindow)) {
			glfwPollEvents();
			
			glClearColor(r, g, b, a);
			glClear(GL_COLOR_BUFFER_BIT);
			
			if(dt >= 0) {
				currentScene.update(dt);
			}
			
			glfwSwapBuffers(glfwWindow);
			
			endTime = (float)glfwGetTime();
			dt = endTime - beginTime;
			beginTime = endTime;
		}
	}
	
	public void run() {
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
