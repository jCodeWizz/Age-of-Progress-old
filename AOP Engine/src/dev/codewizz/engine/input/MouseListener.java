package dev.codewizz.engine.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

import dev.codewizz.engine.Window;
import dev.codewizz.engine.renderer.Camera;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getX() {
        return (float)get().xPos;
    }

    public static float getY() {
        return (float)get().yPos;
    }
    
    public static float getOrthoX() {
    	float currentX = getX();
    	currentX = (currentX / (float)Window.getWidth()) * 2.0f -1.0f;
    	Vector4f tmp = new Vector4f(currentX, 0f, 0f, 1f);
    	tmp.mul(Window.getScene().getCamera().getInverseProjection()).mul(Window.getScene().getCamera().getInverseView());
    	
    	return tmp.x;
    }
    
    public static Vector2f getWorld() {
    	float currentX = getX() - 0f;
    	currentX = (2.0f * (currentX / Window.getWidth())) - 1.0f;
    	float currentY = getY() - 0;
    	currentY = (2.0f * (1.0f - (currentY / Window.getHeight()))) - 1.0f;
    	
    	Camera c = Window.getScene().getCamera();
    	Vector4f tmp = new Vector4f(currentX, currentY, 0f, 1f);
    	Matrix4f iView = new Matrix4f(c.getInverseView());
    	Matrix4f iProj = new Matrix4f(c.getInverseProjection());
    	
    	tmp.mul(iView.mul(iProj));
    	
    	return new Vector2f(tmp.x, tmp.y);
    }
    	
    public static float getOrthoY() {
    	float currentY = getY();
    	currentY = (currentY / (float)Window.getHeight()) * 2.0f -1.0f;
    	Vector4f tmp = new Vector4f(0f, currentY, 0f, 1f);
    	tmp.mul(Window.getScene().getCamera().getInverseProjection()).mul(Window.getScene().getCamera().getInverseView());
    	
    	return tmp.y;
    }

    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }

    public static float getScrollY() {
        return (float)get().scrollY;
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else {
            return false;
        }
    }
}