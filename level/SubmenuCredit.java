package com.thecherno.flappy.level;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;


/**
 * Created by fflea_000 on 2/20/2017.
 */
public class SubmenuCredit extends Submenutexture {
    private float SIZE = 4f;
    private VertexArray mesh;
    private Texture credittexture;
    private boolean reset;


    public SubmenuCredit() {
        float[] vertices = new float[]{
                -SIZE, -SIZE / 8 * 9, 0.3f,
                -SIZE, SIZE / 8 * 9, 0.3f,
                SIZE, SIZE / 8 * 9, 0.3f,
                SIZE, -SIZE / 8 * 9, 0.3f
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

        credittexture = new Texture("res/RPG/Mainmenu/Credit/Credit.png");

    }

    public void update(boolean reseting) {
        if (!reseting && !reset) {
            credittexture = new Texture("res/RPG/Mainmenu/Credit/Credit.png");
            reset = true;
        } else if (reseting && reset) {
            credittexture = new Texture("res/RPG/Mainmenu/Credit/CreditI.png");
            reset = false;
        }
    }


    @Override
    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f(x, 0.8f, 0)));
        credittexture.bind();
        mesh.render();
        Shader.BIRD.disable();

    }
}