package com.thecherno.flappy.level;


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.clicklocation.Checkoptionpos.checkresetdata;
import static com.thecherno.flappy.customvariable.Customvar.MouseXpos;
import static com.thecherno.flappy.customvariable.Customvar.MouseYpos;
import static com.thecherno.flappy.customvariable.Customvar.lastcheck;
import static com.thecherno.flappy.input.MouseInput.mouse_button_callback;
import static com.thecherno.flappy.utils.readData.writedata;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;


//import static com.thecherno.flappy.level.SubmenuStoryMapandButton.insubmenustory;

public class Crediticon implements MenuIcon {

    private float SIZE = 0.73f;
    private VertexArray mesh;
    private Texture texture, blue, lime;


    private Vector3f position = new Vector3f(6, -2f, 0);

    private Submenutexture submenutexture;
    private SubmenuCredit submenucredit;

    private int insubmenucredit;


    public Crediticon() {
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

        blue = texture = new Texture("res/RPG/Mainmenu/Mainmenuoption/CreditBlue.png");
        lime = new Texture("res/RPG/Mainmenu/Mainmenuoption/CreditLime.png");
        submenutexture = new Submenutexture();
        submenucredit = new SubmenuCredit();


    }


    public int update(int stt) {
        if (stt == 0) texture = lime;
        if (stt == -1) {
            texture = blue;
            if (insubmenucredit > 0) {
                insubmenucredit = submenutexture.updateout(false);
                submenucredit.updateout(true);
            }
        }
        if (stt == 1) {
            texture = lime;
            if (insubmenucredit == 0) {
                insubmenucredit = submenutexture.updatein(false);
                submenucredit.updatein(true);
            } else {
                if (checkresetdata(MouseXpos, MouseYpos)) {
                    submenucredit.update(true);
                    if (System.currentTimeMillis() - lastcheck > 1000 && mouse_button_callback == GLFW_PRESS) {

                        writedata();
                        lastcheck = System.currentTimeMillis();
                        return 23;
                    }
                } else submenucredit.update(false);
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
        submenucredit.render();
        submenutexture.render();
    }
}