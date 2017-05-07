package com.thecherno.flappy.input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by fflea_000 on 2/17/2017.
 */
public class MouseInput extends GLFWMouseButtonCallback {
    public static int mouse_button_callback = -1;

    public void invoke(long window, int button, int action, int mods) {
        if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS)
            mouse_button_callback = GLFW_PRESS;
        if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_RELEASE)
            mouse_button_callback = GLFW_RELEASE;
        if (button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_PRESS)
            mouse_button_callback = GLFW_MOUSE_BUTTON_3;
        if (button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_RELEASE)
            mouse_button_callback = GLFW_MOUSE_BUTTON_4;
    }
}
