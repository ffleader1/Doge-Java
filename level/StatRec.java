package com.thecherno.flappy.level;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

/**
 * Created by fflea_000 on 2/20/2017.
 */
public class StatRec {
    private Vector3f position = new Vector3f(0, 0, 0);
    private Matrix4f ml_matrix;
    private float SIZE = 1.6f;

    private Texture texture, text0, text1, text2;
    private VertexArray mesh;

    public StatRec() {
        float[] vertices = new float[]{

                -SIZE, -SIZE / 382 * 116, 0.5f,
                -SIZE, SIZE / 382 * 116, 0.5f,
                SIZE, SIZE / 382 * 116, 0.5f,
                SIZE, -SIZE / 382 * 116, 0.5f
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
        texture = text0 = new Texture("res/RPG/Empty.png");


    }

    public void update(float x, float y, int stat) {
        position.x = x;
        position.y = y;
        position.z = 0;

        if (stat == -2) {
            position.z = -1;
            return;
        }
        if (stat == -1) {
            texture = new Texture("res/RPG/Mainmenu/Custom/DataAvailable.png");
            return;
        }
        if (stat >= 0) {
            texture = new Texture("res/RPG/Mainmenu/Custom/StartpointAvailable.png");
            return;
        }


    }

    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }


}
