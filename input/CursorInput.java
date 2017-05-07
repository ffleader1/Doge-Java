package com.thecherno.flappy.input;

import com.thecherno.flappy.customvariable.Customvar;
import org.lwjgl.glfw.GLFWCursorPosCallback;

/**
 * Created by fflea_000 on 2/17/2017.
 */
public class CursorInput extends GLFWCursorPosCallback {

    @Override
    public void invoke(long window, double xpos, double ypos) {
        Customvar.MouseXpos = xpos;
        Customvar.MouseYpos = ypos;

    }
}






