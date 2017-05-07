package com.thecherno.flappy.level;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.clicklocation.Checkoptionpos.checkmainmenuclick;
import static com.thecherno.flappy.customvariable.Customvar.*;
import static com.thecherno.flappy.input.MouseInput.mouse_button_callback;
import static com.thecherno.flappy.mainwindow.Masterwindow.achievement;
import static com.thecherno.flappy.sound.Playsound.changebgm;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

/**
 * Created by fflea_000 on 2/19/2017.
 */


public class Level {

    private VertexArray background, fade;
    private Texture bgTexture;

    private int xScroll = 0;
    private int map = 0;


    private Storyicon storyicon;
    private Customicon customicon;
    private Tutorialicon tutorialicon;
    private Crediticon crediticon;
    private Exiticon exiticon;
    private int insub = -1, liming;
    private int[] state = new int[6];

    private float time = 0.0f;
    private boolean change = false;
    private boolean checkBG;
    private int choice1 = 0, choice2 = 0, choice3 = 0;


    public Level() {
        float[] vertices = new float[]{
                -10.0f, -10.0f * 9.0f / 16.0f, 0.0f, //////THE THIRD ELEMENT IS THE DEPTH, smaller= behind, larger = in front
                -10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                10.0f, -10.0f * 9.0f / 16.0f, 0.0f

        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[]{
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        fade = new VertexArray(6);
        background = new VertexArray(vertices, indices, tcs);


        storyicon = new Storyicon();                                                               /////DIED HERE ONCE
        customicon = new Customicon();
        tutorialicon = new Tutorialicon();                                               /////////// ĐIE HERE THREETIMES
        crediticon = new Crediticon();
        exiticon = new Exiticon();

        bgTexture = new Texture("res/RPG/Mainmenu/Title0.png");
        for (int i = 0; i < 6; i++) {
            state[i] = -1;
        }


    }


    public int update() {
        if (!checkBG) {
            changebgm(0);
            if (achievement % 2 == 0) bgTexture = new Texture("res/RPG/Mainmenu/Title.png");
            checkBG = true;
        }
        if (Enable_BG_Scroll) {
            xScroll--;
            if (-xScroll % 500 == 0) map++;

        }
        if (storyicon != null) choice1 = storyicon.update(state[0]);
        if (customicon != null) choice2 = customicon.update(state[1]);
        if (tutorialicon != null) tutorialicon.update(state[2]);
        if (crediticon != null) choice3 = crediticon.update(state[3]);
        if (exiticon != null) exiticon.update(state[4]);

        if (mouse_button_callback == GLFW_PRESS && insub == -1 && checkmainmenuclick(MouseXpos, MouseYpos) >= 0) {
            for (int i = 0; i < 5; i++) {
                if (i == checkmainmenuclick(MouseXpos, MouseYpos)) {
                    state[i] = 1;
                    insub = i;
                } else (state[i]) = -1;

            }


        }

        if (mouse_button_callback == GLFW_PRESS && insub != -1 && checkmainmenuclick(MouseXpos, MouseYpos) == -2) {
            state[insub] = -1;
            insub = -1;
        }


        time += 0.01f;

        if (insub == -1) {
            if (checkmainmenuclick(MouseXpos, MouseYpos) >= 0 && !change) {
                liming = checkmainmenuclick(MouseXpos, MouseYpos);
                state[liming] = 0;


                change = true;
            }
            if (checkmainmenuclick(MouseXpos, MouseYpos) < 0 && change) {
                state[liming] = -1;
                change = false;
            }
        }

        if (choice1 != 0) return -choice1;

        if (choice2 != 0) return choice2;
        if (choice3 != 0) return choice3;
        return 0;
    }


    public void render() {
        bgTexture.bind();
        Shader.BG.enable();
        background.bind();
        for (int i = map; i < map + 4; i++) {

            Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(i * 10 + xScroll * 0.3f/* for speed */, 0.0f, 0.0f)));
            background.draw();

        }
        Shader.BG.disable();
        bgTexture.unbind();

        if (storyicon != null) storyicon.render();
        if (customicon != null) customicon.render();
        if (tutorialicon != null) tutorialicon.render();
        if (crediticon != null) crediticon.render();
        if (exiticon != null) exiticon.render();

        Shader.FADE.enable();
        Shader.FADE.setUniform1f("time", time);
        fade.render();
        Shader.FADE.disable();

    }
}
