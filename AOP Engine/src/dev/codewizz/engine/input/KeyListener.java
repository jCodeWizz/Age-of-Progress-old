package dev.codewizz.engine.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[350];
    private boolean keyJustPressed[] = new boolean[350];

    private KeyListener() {

    }

    public static KeyListener get() {
        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
    	if(key < get().keyPressed.length && key >= 0) {
    		 if (action == GLFW_PRESS) {
    	            get().keyPressed[key] = true;
    	        } else if (action == GLFW_RELEASE) {
    	            get().keyPressed[key] = false;
    	        }
    	}
    }

    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }
    
    public static boolean isKeyUp(int keyCode) {
    	return !get().keyPressed[keyCode];
    }
    
    public static boolean isKeyDown(int keyCode) {
    	if(isKeyPressed(keyCode) && !get().keyJustPressed[keyCode]) {
    		get().keyJustPressed[keyCode] = true;
    		return true;
    	} else {
    		if(isKeyUp(keyCode))
    			get().keyJustPressed[keyCode] = false;
    		
    		return false;
    	}
    }
}