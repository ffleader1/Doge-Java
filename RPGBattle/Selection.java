package com.thecherno.flappy.RPGBattle;

import com.thecherno.flappy.input.Input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by fflea_000 on 3/18/2017.
 */
public class Selection {

    public int[] sl0 = new int[2];
    public int[] sl1 = new int[2];
    public int[] sl2 = new int[2];


    public Selection() {
        sl0[0] = -1;
        sl0[1] = 0;
        sl1[0] = -1;
        sl1[1] = 0;
        sl2[0] = -1;
        sl2[1] = 0;


    }


    public int select0() {

        if (Input.keys[GLFW_KEY_X]) {
            sl0[0] = sl0[1];
            Input.keys[GLFW_KEY_X] = false;
        }

        if ((Input.keys[GLFW_KEY_DOWN])) {
            sl0[1]++;
            if (sl0[1] > 2) sl0[1] = 0;
            Input.keys[GLFW_KEY_DOWN] = false;

        }

        if ((Input.keys[GLFW_KEY_UP])) {
            sl0[1]--;
            if (sl0[1] < 0) sl0[1] = 2;
            Input.keys[GLFW_KEY_UP] = false;
        }

        return sl0[1];
    }

    public int select1() {

        if (Input.keys[GLFW_KEY_X]) {
            sl1[0] = sl1[1];
            Input.keys[GLFW_KEY_X] = false;
        }

        if ((Input.keys[GLFW_KEY_DOWN])) {
            sl1[1]++;
            if (sl1[1] > 5) sl1[1] = 0;
            Input.keys[GLFW_KEY_DOWN] = false;

        }

        if ((Input.keys[GLFW_KEY_UP])) {
            sl1[1]--;
            if (sl1[1] < 0) sl1[1] = 5;
            Input.keys[GLFW_KEY_UP] = false;

        }
        if ((Input.keys[GLFW_KEY_RIGHT]) || (Input.keys[GLFW_KEY_LEFT])) {
            if (sl1[1] < 3) sl1[1] += 3;
            else sl1[1] -= 3;
            Input.keys[GLFW_KEY_LEFT] = false;
            Input.keys[GLFW_KEY_RIGHT] = false;
        }

        if ((Input.keys[GLFW_KEY_W])) {
            sl0[0] = -1;
            sl1[1] = 0;
            Input.keys[GLFW_KEY_W] = false;
        }

        return sl1[1];
    }

    public int select2() {
        if (Input.keys[GLFW_KEY_X]) {
            sl2[0] = sl2[1];
            Input.keys[GLFW_KEY_X] = false;
        }
        if ((Input.keys[GLFW_KEY_UP])) {
            sl2[1]++;
            if (sl2[1] > 2) sl2[1] = 0;
            Input.keys[GLFW_KEY_UP] = false;

        }
        if ((Input.keys[GLFW_KEY_DOWN])) {
            sl2[1]--;
            if (sl2[1] < 0) sl2[1] = 2;
            Input.keys[GLFW_KEY_DOWN] = false;

        }
        if ((Input.keys[GLFW_KEY_RIGHT]) || (Input.keys[GLFW_KEY_LEFT])) {
            if (sl2[1] == 3) sl2[1] = 1;
            else sl2[1] = 3;
            Input.keys[GLFW_KEY_LEFT] = false;
            Input.keys[GLFW_KEY_RIGHT] = false;
        }
        if ((Input.keys[GLFW_KEY_W])) {
            if (sl1[0] != -1) sl1[0] = -1;
            else sl0[0] = -1;
            sl2[1] = 0;
            Input.keys[GLFW_KEY_W] = false;
        }
        return sl2[1];
    }

    public int converttoaction() {
        if (sl0[0] == 0) return 0;
        if (sl0[0] == 1) return (sl1[0] + 1);
        else return ((sl1[0] + 1) + 6);
    }

}
