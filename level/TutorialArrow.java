package com.thecherno.flappy.level;

/**
 * Created by fflea_000 on 2/22/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;


public class TutorialArrow {

    private float SIZE = 0.7f;
    private VertexArray mesh;
    private Texture texture;
    private float fr = 1f;
    //  private float z = -1f;
    private Vector3f position = new Vector3f(-2.6f, -4.5f, 0);


    public TutorialArrow() {
        float[] vertices = new float[]{
                -SIZE * 2, -SIZE, 0.6f,
                -SIZE * 2, SIZE, 0.6f,
                SIZE * 2, SIZE, 0.6f,
                SIZE * 2, -SIZE, 0.6f
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
        texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorialarrow.png");

    }

    public void update(float f) {
        if (f == 1) {
            texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorialarrowleft.png");
        } else if (f == -1) {
            texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorialarrowright.png");
        } else texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorialarrow.png");


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