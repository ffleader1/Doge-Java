package com.thecherno.flappy.level;


import com.thecherno.flappy.customvariable.Stats;
import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.clicklocation.Maplocation.checkededpppos;
import static com.thecherno.flappy.clicklocation.Maplocation.checkrectanglepos;
import static com.thecherno.flappy.customvariable.Customvar.*;
import static com.thecherno.flappy.input.MouseInput.mouse_button_callback;
import static com.thecherno.flappy.sound.Playsound.notavailable;
import static com.thecherno.flappy.utils.AccessMap.readMapdata;
import static com.thecherno.flappy.utils.readData.readchardat;
import static com.thecherno.flappy.utils.readData.writedata;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;



public class Customicon implements MenuIcon {

    private float SIZE = 0.73f;
    private VertexArray mesh;
    private Texture texture, blue, lime;


    private Vector3f position = new Vector3f(6, 2f, 0);

    private Submenutexture submenutexture;
    private SubmenuCustom submenucustom;
    private EditDeletePlay edp;
    private StatRec[] statrec = new StatRec[8];
    private SelectRec rectangle;
    private char[][] mapdat = new char[12][17];
    private int edpval, recval;


    private int[] stat = new int[8];

    private int insubmenucustom = 0;

    public Customicon() {
        float[] vertices = new float[]{
                -SIZE * 331 / 101f, -SIZE, 0.1f,
                -SIZE * 331 / 101f, SIZE, 0.1f,
                SIZE * 331 / 101f, SIZE, 0.1f,
                SIZE * 331 / 101f, -SIZE, 0.1f
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

        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("res/RPG/Mainmenu/Mainmenuoption/CustomBlue.png");
        blue = new Texture("res/RPG/Mainmenu/Mainmenuoption/CustomBlue.png");
        lime = new Texture("res/RPG/Mainmenu/Mainmenuoption/CustomLime.png");
        submenutexture = new Submenutexture();
        submenucustom = new SubmenuCustom();

        rectangle = new SelectRec();

        //////////////////// DIED HERE ONCE

        for (int j = 0; j < 8; j++) {
            try {
                stat[j] = readMapdata(mapdat, j + 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    public int update(int stt) {
        if (stt == 0) texture = lime;
        if (stt == -1) {
            texture = blue;
            if (insubmenucustom > 0) {
                insubmenucustom = submenutexture.updateout(false);
                submenucustom.updateout(true);
                for (int i = 0; i < 8; i++) {
                    statrec[i] = null;

                }
                edp = null;

            }
            return 0;
        }
        if (stt == 1) {
            texture = lime;
            if (insubmenucustom == 0) {
                insubmenucustom = submenutexture.updatein(false);
                submenucustom.updatein(true);

            } else {
                if (insubmenucustom == 1) {
                    for (int i = 0; i < 8; i++) {
                        statrec[i] = new StatRec();

                    }
                    edp = new EditDeletePlay();
                    for (int i = 0; i < 8; i++) {
                        statrec[i].update(recval('x', i), recval('y', i), stat[i]);
                    }

                    insubmenucustom = 2;
                }
                if (edp != null) edp.update(edpval = checkededpppos(MouseXpos, MouseYpos));
                if (mouse_button_callback == GLFW_PRESS && edpval == 0) {
                    rectangle.update(recval = checkrectanglepos(MouseXpos, MouseYpos));
                }

                if (mouse_button_callback == GLFW_PRESS && edpval != 0 && recval >= 0) {

                    if (edpval != 17) {

                        return (edpval * (recval + 1));
                    } else {
                        if (stat[recval] >= 0) {
                            Stats tempt = new Stats();
                            tempt = readchardat();
                            tempt.setmapnum ( recval + 1);
                            tempt.setlocation ( stat[recval]);
                            writedata(tempt);

                            return 17;
                        } else notavailable.play();

                    }
                }
            }
        }
        return 0;
    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
        submenucustom.render();
        submenutexture.render();
        if (edp != null) edp.render();
        rectangle.render();

        for (int i = 0; i < 8; i++) {
            if (statrec[i] != null) statrec[i].render();
        }

    }


}