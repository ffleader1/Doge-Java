package com.thecherno.flappy.updatedigit;


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

public class Digit {

    private static float width = .35f, height = .35f;
    //  private float SIZE = 0.5f;
    private VertexArray mesh;
    private Texture texture;
    private Texture[] num = new Texture[21];
    private float SIZE = .25f;
    private Vector3f position = new Vector3f(-8, 5, 0);
    private float rot;
    private float delta = 0.015f;

    public Digit() {
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

        texture = new Texture("res/RPG/Empty.png");

        num[0] = new Texture("res/RPG/Digit/0w.png");
        num[1] = new Texture("res/RPG/Digit/1w.png");
        num[2] = new Texture("res/RPG/Digit/2w.png");
        num[3] = new Texture("res/RPG/Digit/3w.png");
        num[4] = new Texture("res/RPG/Digit/4w.png");
        num[5] = new Texture("res/RPG/Digit/5w.png");
        num[6] = new Texture("res/RPG/Digit/6w.png");
        num[7] = new Texture("res/RPG/Digit/7w.png");
        num[8] = new Texture("res/RPG/Digit/8w.png");
        num[9] = new Texture("res/RPG/Digit/9w.png");

        num[10] = new Texture("res/RPG/Digit/0g.png");
        num[11] = new Texture("res/RPG/Digit/1g.png");
        num[12] = new Texture("res/RPG/Digit/2g.png");
        num[13] = new Texture("res/RPG/Digit/3g.png");
        num[14] = new Texture("res/RPG/Digit/4g.png");
        num[15] = new Texture("res/RPG/Digit/5g.png");
        num[16] = new Texture("res/RPG/Digit/6g.png");
        num[17] = new Texture("res/RPG/Digit/7g.png");
        num[18] = new Texture("res/RPG/Digit/8g.png");
        num[19] = new Texture("res/RPG/Digit/9g.png");
        num[20] = new Texture("res/RPG/Empty.png");


    }


    public void update(float x0, float y0, float y, char c) {
        position.x = x0;
        position.y = y0;
        if (c == '!') {

            texture = num[20];
            return;


        }
        if (c == 'w') {
            if (y == 0) {
                texture = num[0];
                return;
            }
            if (y == 1) {
                texture = num[1];
                return;
            }
            if (y == 2) {
                texture = num[2];
                return;
            }
            if (y == 3) {
                texture = num[3];
                return;
            }
            if (y == 4) {
                texture = num[4];
                return;
            }
            if (y == 5) {
                texture = num[5];
                return;
            }
            if (y == 6) {
                texture = num[6];
                return;
            }
            if (y == 7) {
                texture = num[7];
                return;
            }
            if (y == 8) {
                texture = num[8];
                return;
            }
            if (y == 9) {
                texture = num[9];
                return;
            }
        } else if (c == 'g') {
            if (y == 0) {
                texture = num[10];
                return;
            }
            if (y == 1) {
                texture = num[11];
                return;
            }
            if (y == 2) {
                texture = num[12];
                return;
            }
            if (y == 3) {
                texture = num[13];
                return;
            }
            if (y == 4) {
                texture = num[14];
                return;
            }
            if (y == 5) {
                texture = num[15];
                return;
            }
            if (y == 6) {
                texture = num[16];
                return;
            }
            if (y == 7) {
                texture = num[17];
                return;
            }
            if (y == 8) {
                texture = num[18];
                return;
            }
            if (y == 9) {
                texture = num[19];
                return;
            }
        }

    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }

    public float getY() {
        return position.y;
    }


}