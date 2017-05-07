package com.thecherno.flappy.level;

/**
 * Created by fflea_000 on 2/22/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.customvariable.Customvar.recval;


public class SelectRec {

    private float SIZE = 1.665f;
    private VertexArray mesh;
    private Texture texture;


    private Vector3f position = new Vector3f(-14, -14, 0);


    public SelectRec() {
        float[] vertices = new float[]{
                -SIZE, -SIZE / 425 * 151, 0.4f,
                -SIZE, SIZE / 425 * 151, 0.4f,
                SIZE, SIZE / 425 * 151, 0.4f,
                SIZE, -SIZE / 425 * 151, 0.4f
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
        texture = new Texture("res/RPG/Mainmenu/Custom/SelectionRetagcle.png");

    }


    public boolean update(int f) {

        if (f == -1) position.z = -1;
        if (f >= 0) {
            position.x = recval('x', f);
            position.y = recval('y', f);
            position.z = 0;
            return true;
        }
        return false;


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