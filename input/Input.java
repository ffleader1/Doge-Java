package com.thecherno.flappy.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Created by fflea_000 on 2/17/2017.
 */
public class Input extends GLFWKeyCallback {

    public static boolean[] keys = new boolean[65536];
    public static boolean[] keypr = new boolean[65536];
    public static boolean[] keyre = new boolean[65536];


    public void invoke(long window, int key, int scancode, int action, int mods) {
        //  keys[key] = Action == GLFW.GLFW_RELEASE;
        if (key >= 0) {
            keys[key] = action == GLFW.GLFW_RELEASE;
            keypr[key] = action == GLFW.GLFW_PRESS;
            keyre[key] = action == GLFW.GLFW_REPEAT;
        }

    }

}