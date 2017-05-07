package com.thecherno.flappy.level;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

/**
 * Created by fflea_000 on 2/20/2017.
 */
public class Storyicon implements MenuIcon {
    private float SIZE = 0.73f;
    private VertexArray mesh;
    private Texture texture, blue, lime;


    private Submenutexture submenutexture;


    private Vector3f position = new Vector3f(6, 4, 0);


    private int insubmenustory = 0;


    public Storyicon() {
        float[] vertices = new float[]{
                -SIZE * 281 / 101f, -SIZE, 0.1f,
                -SIZE * 281 / 101f, SIZE, 0.1f,
                SIZE * 281 / 101f, SIZE, 0.1f,
                SIZE * 281 / 101f, -SIZE, 0.1f
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
        texture = new Texture("res/RPG/Mainmenu/Mainmenuoption/StoryLime.png");
        blue = new Texture("res/RPG/Mainmenu/Mainmenuoption/StoryBlue.png");
        lime = new Texture("res/RPG/Mainmenu/Mainmenuoption/StoryLime.png");
        submenutexture = new Submenutexture();


    }


    public int update(int stt) {
        if (stt == 0) texture = lime;
        if (stt == -1) {
            texture = blue;
            if (insubmenustory > 0) {

                insubmenustory = submenutexture.updateout(true);

            }
            return 0;
        }
        if (stt == 1) {
            return 1;

        }

        return 0;

    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();

    }

}
