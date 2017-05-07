package com.thecherno.flappy.mapmaker;

/**
 * Created by fflea_000 on 2/22/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

public class SelectRedSqr {


    private static float width = 1.5f, height = 1.5f;
    private VertexArray mesh;
    private Texture texture;
    private Texture rec, sqr;
    private float Xpos = 7.215f;
    private float Ypos = 2.33f;
    private Vector3f position = new Vector3f(Xpos, Ypos, 0);


    public SelectRedSqr() {
        float[] vertices = new float[]{
                0.0f, 0.0f, 0.5f,
                0.0f, height, 0.5f,
                width, height, 0.5f,
                width, 0.0f, 0.5f

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
        texture = new Texture("res/RPG/makefield/Selectsqr.png");

        // rot = 45.0f;
    }
    //public void fall() {       delta = -0.015f;    }

    public void update(int i) {

        position.y = Ypos - i * 1.285f;


    }

    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }

    public float getY() {
        return position.y;
    }

    // public float getSize() {       return SIZE;    }


}