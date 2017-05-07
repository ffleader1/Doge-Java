package com.thecherno.flappy.level;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;


/**
 * Created by fflea_000 on 2/20/2017.
 */
public class SubmenuTutorial extends Submenutexture {

    private float SIZE = 4f;
    private VertexArray mesh;
    private Texture texture;
    // private float x = -18f;


    public SubmenuTutorial() {
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

        texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorial1.png");

    }


    public void updatenext(int p) {
        if (p == 1) texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorial1.png");
        else if (p == 2) texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorial2.png");
        else if (p == 3) texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorial3.png");
        else
            texture = new Texture("res/RPG/Mainmenu/Tutorial/tutorial4.png");

    }


    @Override
    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f(x, 0.8f, 0)));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();

    }


}