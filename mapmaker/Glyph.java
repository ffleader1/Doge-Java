package com.thecherno.flappy.mapmaker;

/**
 * Created by fflea_000 on 2/22/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

public class Glyph {


    private static float SIZE = .5f;
    private VertexArray mesh;
    private Texture texture;
    private Vector3f position = new Vector3f(-14, -14, 0);


    public Glyph() {
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
        texture = new Texture("res/RPG/makefield/Selectsqr.png");

        // rot = 45.0f;
    }
    //public void fall() {       delta = -0.015f;    }

    public void update(double xpos, double ypos) {
        position.x = (float) xpos / (1280f / 10.1f / 2f) - 10.1f;
        position.y = -((float) ypos / (720f / 5.68f / 2f) - 5.68f);
        //   System.out.println(+position.x+"     "+position.y);


    }

    public void maketexture(int i) {
        if (i == -1) position.x = -14;
        if (i == 0) texture = new Texture("res/RPG/terrain/Legend.Summon.Glyph.png");
        if (i == 2) texture = new Texture("res/RPG/terrain/Legend.Boss.Glyph.png");
        if (i == 1) texture = new Texture("res/RPG/terrain/Legend.In.Glyph.png");
        if (i == 3) texture = new Texture("res/RPG/terrain/Legend.Out.Glyph.png");


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




}