package com.thecherno.flappy.RPGBattle;


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.customvariable.Customvar.lastcheck;

public class Bmenuframe {

    private float SIZE = 10f;
    private VertexArray mesh;
    private Texture texture;


    private Vector3f position = new Vector3f(-14, -3.85f, 0);


    public Bmenuframe() {
        float[] vertices = new float[]{
                -SIZE, -SIZE / (1280 / 200), 0.2f,
                -SIZE, SIZE / (1280 / 200), 0.2f,
                SIZE, SIZE / (1280 / 200), 0.2f,
                SIZE, -SIZE / (1280 / 200), 0.2f
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

        texture = new Texture("res/RPG/Battlemenu/BattleMenuFrame.png");
    }


    public int updatein() {

        if (position.x < -0.4) {
            if ((System.currentTimeMillis() - lastcheck) > 10) {
                position.x += 0.4f;
                lastcheck = System.currentTimeMillis();

            }
            return 0;
        }
        return 1;
    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate((position)));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();


    }


}