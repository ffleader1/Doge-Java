package com.thecherno.flappy.level;


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.customvariable.Customvar.lastcheck;
import static com.thecherno.flappy.customvariable.Customvar.lastcheck2;

public class Submenutexture {

    protected float x = -18f;
    private float SIZE = 5.2f;
    private VertexArray mesh;
    private Texture texture;


    public Submenutexture() {
        float[] vertices = new float[]{
                -SIZE, -SIZE / 12 * 16f, 0.2f,
                -SIZE, SIZE / 12 * 16f, 0.2f,
                SIZE, SIZE / 12 * 16f, 0.2f,
                SIZE, -SIZE / 12 * 16f, 0.2f
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

        texture = new Texture("res/RPG/Mainmenu/Submenutexture/Old-Paper-Texture.png");
    }


    public int updatein(boolean reset) {

        if (x <= -3.2) {
            if ((System.currentTimeMillis() - lastcheck) > 10) {
                x += 0.2f;
                if (reset)
                    lastcheck = System.currentTimeMillis();

            }
            return 0;
        }
        return 1;
    }


    public int updateout(boolean reset) {
        if (x >= -18) {
            if ((System.currentTimeMillis() - lastcheck2) > 10) {
                x -= 0.2f;
                if (reset)
                    lastcheck2 = System.currentTimeMillis();
            }
            return 1;

        }
        return 0;

    }

    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f(x, 0, 0)));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();

    }


}