package com.thecherno.flappy.RPGBattle;

/**
 * Created by fflea_000 on 2/22/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.customvariable.Customvar.MonPos;
import static com.thecherno.flappy.customvariable.Customvar.battleMenulocation;

public class SelectArrow {

    private float SIZE = 0.6f;
    private VertexArray mesh;
    private Texture texture;
    private Texture[] text = new Texture[5];


    private Vector3f position = new Vector3f(-14f, -14f, 0);

    public SelectArrow() {

        float[] vertices = new float[]{
                -SIZE, -SIZE, 0.6f,
                -SIZE, SIZE, 0.6f,
                SIZE, SIZE, 0.6f,
                SIZE, -SIZE, 0.6f
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
        texture = text[0] = new Texture("res/RPG/battlemenu/Arrow.png");

        // rot = 45.0f;
    }
    //public void fall() {       delta = -0.015f;    }

    public void update(int i, char c) {

        if (i < 0) return;

        if (c == 'm') {

            position.x = battleMenulocation('x', i) - 2.0f;
            position.y = battleMenulocation('y', i);

        }
        if (c == 's') {
            position.x = battleMenulocation('x', i + 3) - 2.0f;
            position.y = battleMenulocation('y', i + 3);

        }
        if (c == 't') {
            if (i == 3) {
                position.x = -7.5f;
                position.y = 0;
                return;
            }
            position.x = MonPos('x', i);
            position.y = MonPos('y', i);
        }
        if (c == 'n') {
            position.x = -14f;
            position.y = -14f;
        }

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

    public float getSize() {
        return SIZE;
    }


}