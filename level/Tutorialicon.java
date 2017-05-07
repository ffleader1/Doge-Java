package com.thecherno.flappy.level;


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.clicklocation.Checkoptionpos.checktutorialarrowpos;
import static com.thecherno.flappy.customvariable.Customvar.*;
import static com.thecherno.flappy.input.MouseInput.mouse_button_callback;
import static com.thecherno.flappy.mainwindow.Masterwindow.achievement;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;


public class Tutorialicon implements MenuIcon {

    private float SIZE = 0.73f;
    private VertexArray mesh;
    private Texture texture, blue, lime;


    private Vector3f position = new Vector3f(6, 0f, 0);


    private Submenutexture submenutexture;
    private TutorialArrow tutorialarrow;
    private SubmenuTutorial submenututorial;
    private boolean loadAchie = false;

    private int insubmenututorial = 0;
    private int count = 1;
    private boolean change = false;
    private Achievement[] achieve = new Achievement[4];


    public Tutorialicon() {
        float[] vertices = new float[]{
                -SIZE * 445 / 101f, -SIZE, 0.1f,
                -SIZE * 445 / 101f, SIZE, 0.1f,
                SIZE * 445 / 101f, SIZE, 0.1f,
                SIZE * 445 / 101f, -SIZE, 0.1f
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
        texture = blue = new Texture("res/RPG/Mainmenu/Mainmenuoption/TutorialBlue.png");
        lime = new Texture("res/RPG/Mainmenu/Mainmenuoption/TutorialLime.png");

        submenutexture = new Submenutexture();
        submenututorial = new SubmenuTutorial();


    }

    private void dismissAchieve() {
        loadAchie = false;
        if (achieve[0] != null) for (int i = 0; i < 4; i++) {
            achieve[i] = null;
        }
    }


    public int update(int stt) {
        if (stt == 0) {
            texture = lime;
            return 0;
        }

        if (stt == -1) {
            texture = blue;
            if (insubmenututorial > 0) {
                insubmenututorial = submenutexture.updateout(false);
                submenututorial.updateout(true);
                dismissAchieve();
                tutorialarrow = null;
                return 0;
            }
        }
        if (stt == 1) {

            texture = lime;
            if (insubmenututorial == 0) {

                insubmenututorial = submenutexture.updatein(false);
                submenututorial.updatein(true);

            } else {

                if (insubmenututorial == 1) {
                    tutorialarrow = new TutorialArrow();
                    tutorialarrow.update(0);


                    insubmenututorial++;
                } else {
                    if (mouse_button_callback == GLFW_PRESS && checktutorialarrowpos(MouseXpos, MouseYpos) != 0 && System.currentTimeMillis() - lastcheck > 100) {

                        count += checktutorialarrowpos(MouseXpos, MouseYpos);


                        if (count > 4) count = 1;
                        if (count < 1) count = 4;
                        if (count == 4) {
                            if (!loadAchie) {
                                for (int i = 0; i < 4; i++) {
                                    achieve[i] = new Achievement();
                                }
                                if (achievement % 2 == 0) achieve[0].update(-5.3f, 1.8f, 2);
                                else achieve[0].update(-5.3f, 1.8f, -2);
                                if (achievement % 3 == 0) achieve[1].update(-5.3f, 0.35f, 3);
                                else achieve[1].update(-5.3f, 0.35f, -3);
                                if (achievement % 5 == 0) achieve[2].update(-5.3f, -1.1f, 5);
                                else achieve[2].update(-5.3f, -1.1f, -5);
                                if (achievement % 7 == 0) achieve[3].update(-5.3f, -2.55f, 7);
                                else achieve[3].update(-5.3f, -2.55f, -7);
                                // System.out.println(achievement)
                                loadAchie = true;
                            }
                            //
                            // count ++;
                        }

                        if (count < 4) {
                            dismissAchieve();
                        }

                        submenututorial.updatenext(count);
                        lastcheck = System.currentTimeMillis();

                    }
                    if (checktutorialarrowpos(MouseXpos, MouseYpos) != 0 && !change) {

                        tutorialarrow.update(checktutorialarrowpos(MouseXpos, MouseYpos));
                        change = true;
                    }
                    if (checktutorialarrowpos(MouseXpos, MouseYpos) == 0 && change) {
                        tutorialarrow.update(checktutorialarrowpos(MouseXpos, MouseYpos));
                        change = false;
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

        submenutexture.render();
        if (tutorialarrow != null) tutorialarrow.render();
        if (submenututorial != null) submenututorial.render();
        for (int i = 0; i < 4; i++) {
            if (achieve[i] != null) achieve[i].render();
        }


    }


}