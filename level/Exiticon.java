package com.thecherno.flappy.level;


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.sound.Playsound.bgm;

//import static com.thecherno.flappy.level.SubmenuStoryMapandButton.insubmenustory;

public class Exiticon implements MenuIcon {

    private float SIZE = 0.73f;
    private VertexArray mesh;
    private Texture texture, lime, blue;
    private Vector3f position = new Vector3f(6, -4f, 0);


    public Exiticon() {
        float[] vertices = new float[]{
                -SIZE * 217 / 101f, -SIZE, 0.1f,
                -SIZE * 217 / 101f, SIZE, 0.1f,
                SIZE * 217 / 101f, SIZE, 0.1f,
                SIZE * 217 / 101f, -SIZE, 0.1f
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
        texture = blue = new Texture("res/RPG/Mainmenu/Mainmenuoption/ExitBlue.png");
        lime = new Texture("res/RPG/Mainmenu/Mainmenuoption/ExitLime.png");


    }

    public int update(int stt) {
        if (stt == 0) texture = lime;
        if (stt == -1) texture = blue;
        if (stt == 1) {
            if (bgm != null) bgm.close();
            System.exit(0);
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