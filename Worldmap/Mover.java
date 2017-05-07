package com.thecherno.flappy.Worldmap;

/**
 * Created by fflea_000 on 2/22/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

public class Mover {


    private static float SIZE = 1f;
    private static float width = 1.5f, height = 1.5f;

    private VertexArray mesh;
    private Texture texture;
    private Vector3f position = new Vector3f(-14, -14, 0);


    public Mover() {
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


    public void update(double xpos, double ypos) {
        position.x = (float) xpos / (1280f / 10.1f / 2f) - 10.1f;
        position.y = -((float) ypos / (720f / 5.68f / 2f) - 5.68f);


    }

    public void maketexture(float i, boolean holding) {
        if (!holding) position.x = -14;
        if (i == 2) texture = new Texture("res/RPG/Makefield/holdup.png");
        else if (i == -2) texture = new Texture("res/RPG/Makefield/holddown.png");
        else if (i == -1) texture = new Texture("res/RPG/Makefield/holdleft.png");
        else if (i == 1) texture = new Texture("res/RPG/Makefield/holdright.png");
        else if (i == -0.5) texture = new Texture("res/RPG/Makefield/holdupright.png");
        else if (i == 3.5) texture = new Texture("res/RPG/Makefield/holddownright.png");
        else if (i == 0.5) texture = new Texture("res/RPG/Makefield/holddownleft.png");
        else if (i == -3.5) texture = new Texture("res/RPG/Makefield/holdupleft.png");


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