package com.thecherno.flappy.Worldmap;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

/**
 * Created by fflea_000 on 3/19/2017.
 */
public class Charsprite {
    private static float width = 1.2f, height = 1.2f;
    // private float SIZE = 0.6f;
    private VertexArray mesh;
    private Texture texture;
    private Texture[] text = new Texture[20];
    private int pos = 1;
    private float delta = 0.05f;
    private Vector3f position = new Vector3f(-14f, -14f, 0);

    public Charsprite() {
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


        text[0] = new Texture("res/RPG/Character/Mov.Down1.png");
        text[1] = new Texture("res/RPG/Character/Mov.Down2.png");
        text[2] = new Texture("res/RPG/Character/Mov.Down3.png");
        text[3] = new Texture("res/RPG/Character/Mov.Down4.png");
        text[4] = new Texture("res/RPG/Character/Mov.Down5.png");
        text[5] = new Texture("res/RPG/Character/Mov.Left1.png");
        text[6] = new Texture("res/RPG/Character/Mov.Left2.png");
        text[7] = new Texture("res/RPG/Character/Mov.Left3.png");
        text[8] = new Texture("res/RPG/Character/Mov.Left4.png");
        text[9] = new Texture("res/RPG/Character/Mov.Left5.png");
        text[10] = new Texture("res/RPG/Character/Mov.Up1.png");
        text[11] = new Texture("res/RPG/Character/Mov.Up2.png");
        text[12] = new Texture("res/RPG/Character/Mov.Up3.png");
        text[13] = new Texture("res/RPG/Character/Mov.Up4.png");
        text[14] = new Texture("res/RPG/Character/Mov.Up5.png");
        text[15] = new Texture("res/RPG/Character/Mov.Right1.png");
        text[16] = new Texture("res/RPG/Character/Mov.Right2.png");
        text[17] = new Texture("res/RPG/Character/Mov.Right3.png");
        text[18] = new Texture("res/RPG/Character/Mov.Right4.png");
        text[19] = new Texture("res/RPG/Character/Mov.Right5.png");
        texture = text[0];


    }


    public void update(char c) {
        if (c == 'v') {
            texture = text[pos - 1];
            pos++;
            if (position.y > -5.633f)
                position.y -= delta;
        } else if (c == '<') {

            texture = text[5 + pos - 1];
            pos++;
            if (position.x > -10.465f)
                position.x -= delta;
        } else if (c == '^') {

            texture = text[10 + pos - 1];
            pos++;
            if (position.y < 4.885f)
                position.y += delta;
        } else {

            texture = text[15 + pos - 1];
            pos++;
            if (position.x < 5.048f)//5.482f
                position.x += delta;
        }
        if (pos > 5) pos = 1;


    }

    public void render(float x, float y) {
        position.x = x;
        position.y = y;

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

    public float getX() {
        return position.x;
    }


}
