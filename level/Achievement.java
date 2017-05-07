package com.thecherno.flappy.level;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

/**
 * Created by fflea_000 on 2/20/2017.
 */
public class Achievement {
    private Vector3f position = new Vector3f(0, 0, 0);
    private Matrix4f ml_matrix;
    private float SIZE = 0.7f;

    private Texture texture;
    private VertexArray mesh;

    public Achievement() {
        float[] vertices = new float[]{

                -SIZE, -SIZE, 0.5f,
                -SIZE, SIZE, 0.5f,
                SIZE, SIZE, 0.5f,
                SIZE, -SIZE, 0.5f
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
        texture = new Texture("res/RPG/Empty.png");


    }

    public void update(float x, float y, int stat) {
        position.x = x;
        position.y = y;

        if (stat == 0) {
            texture = new Texture("res/RPG/Empty.png");
            return;
        }
        if (stat == 2) {
            texture = new Texture("res/RPG/Achievement/F.Completed.png");
            return;
        }
        if (stat == -2) {
            texture = new Texture("res/RPG/Achievement/F.CompletedG.png");
            return;
        }
        if (stat == 3) {
            texture = new Texture("res/RPG/Achievement/F.Cactus.png");
            return;
        }
        if (stat == -3) {
            texture = new Texture("res/RPG/Achievement/F.Cactusg.png");
            return;
        }
        if (stat == 5) {
            texture = new Texture("res/RPG/Achievement/F.IceHead.png");
            return;
        }
        if (stat == -5) {
            texture = new Texture("res/RPG/Achievement/F.IceHeadG.png");
            return;
        }
        if (stat == 7) {
            texture = new Texture("res/RPG/Achievement/F.Phoenix.png");
            return;
        }
        if (stat == -7) {
            texture = new Texture("res/RPG/Achievement/F.Phoenixg.png");

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
