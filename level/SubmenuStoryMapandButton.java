package com.thecherno.flappy.level;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.customvariable.Customvar.lastcheck;
import static com.thecherno.flappy.customvariable.Customvar.lastcheck2;


/**
 * Created by fflea_000 on 2/20/2017.
 */
public class SubmenuStoryMapandButton {
    private float SIZE = 4f;
    private VertexArray mesh;
    private Texture maptexture;
    private float x = -18f;


    //   private VertexArray fade;
    private Vector3f position = new Vector3f(x, 0.8f, 0);
    //   private float rot;
    private float delta = 0.015f;


    public SubmenuStoryMapandButton() {
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
//        fade=new VertexArray(6);
        mesh = new VertexArray(vertices, indices, tcs);
        //      texture = new Texture("res/mm/Empty.png");
        maptexture = new Texture("res/mm/Story/TitleandMap.png");

    }


    public int updatein() {

        if (x <= -3.2) {

            if ((System.currentTimeMillis() - lastcheck2) > 10) {
                x += 0.2f;
                lastcheck2 = System.currentTimeMillis();
            }
            return 0;

        }
        return 1;
    }

    public void updatefinish() {
        maptexture = new Texture("res/mm/Story/Final.png");
    }

    public int updateout() {
        if (x >= -18) {
            if ((System.currentTimeMillis() - lastcheck) > 10) {
                x -= 0.2f;
                lastcheck = System.currentTimeMillis();
            }
            return 1;
        }
        return 0;


    }

    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f(x, 0.8f, 0)));
        maptexture.bind();

        mesh.render();
        Shader.BIRD.disable();

    }
}