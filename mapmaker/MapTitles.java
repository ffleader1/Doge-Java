package com.thecherno.flappy.mapmaker;

/**
 * Created by Anh on 3/16/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;


/**
 * Created by fflea_000 on 2/20/2017.
 */
public class MapTitles {
    // private static float width = 9f, height = .1f;
    private static float width = .938f, height = .938f;


    public boolean stat = true;
    private Vector3f position = new Vector3f(-14, -14, 0);
    private Texture texture;

    private VertexArray mesh;


    public MapTitles(float f) {
        if (f < 0) f = 1;
        float[] vertices = new float[]{
                0.0f, 0.0f, 0.3f,
                0.0f, height * f, 0.3f,
                width * f, height * f, 0.3f,
                width * f, 0.0f, 0.3f
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


    public void update(char c, float x, float y) {
        position.x = x;
        position.y = y;
        switch (c) {
            case 'g':
                texture = new Texture("res/RPG/Terrain/Grass1.png");
                break;
            case 'G':
                texture = new Texture("res/RPG/Terrain/Grass2.png");
                break;
            case 'd':
                texture = new Texture("res/RPG/Terrain/desert1.png");
                break;
            case 'D':
                texture = new Texture("res/RPG/Terrain/Desert2.png");
                break;
            case 'l':
                texture = new Texture("res/RPG/Terrain/Lava1.png");
                break;
            case 'L':
                texture = new Texture("res/RPG/Terrain/Lava2.png");
                break;
            case 'r':
                texture = new Texture("res/RPG/Terrain/rock1.png");
                break;
            case 'R':
                texture = new Texture("res/RPG/Terrain/rock2.png");
                break;
            case 's':
                texture = new Texture("res/RPG/Terrain/snow1.png");
                break;
            case 'S':
                texture = new Texture("res/RPG/Terrain/snow2.png");
                break;
            case 'M':
                texture = new Texture("res/RPG/Terrain/Moss.png");
                break;
            case 'I':
                texture = new Texture("res/RPG/Terrain/Ice.png");
                break;
            case 'F':
                texture = new Texture("res/RPG/Terrain/Flower.png");
                break;
            case 'W':
                texture = new Texture("res/RPG/Terrain/Water.png");
                break;
            case '>':
                texture = new Texture("res/RPG/Terrain/Legend.Out.Glyph.Black.png");
                break;
            case '<':
                texture = new Texture("res/RPG/Terrain/Legend.In.Glyph.Black.png");
                break;
            case '!':
                texture = new Texture("res/RPG/Terrain/Legend.Boss.Glyph.Black.png");
                break;
            case '?':
                texture = new Texture("res/RPG/Terrain/Legend.Summon.Glyph.Black.png");
                break;
            default:
                texture = new Texture("res/RPG/Terrain/0.png");


        }


    }

    public void render() {
        Shader.PIPE.enable();
        Shader.PIPE.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.PIPE.disable();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

}

