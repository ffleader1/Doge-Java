package com.thecherno.flappy.level;


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.customvariable.Customvar.edpval;

public class EditDeletePlay {

    private float SIZE = 0.77f;
    private VertexArray mesh;
    private Texture texture;

    private Vector3f position = new Vector3f();
    private float rot;
    private float delta = 0.015f;

    public EditDeletePlay() {
        float[] vertices = new float[]{
                -SIZE, -SIZE / 199 * 76, 0.4f,
                -SIZE, SIZE / 199 * 76, 0.4f,
                SIZE, SIZE / 199 * 76, 0.4f,
                SIZE, -SIZE / 199 * 76, 0.4f
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

    public void fall() {
        delta = -0.015f;
    }

    public int update(int f) {
        if (f == 0) position.z = -1;
        if (f == 11) {
            texture = new Texture("res/RPG/Mainmenu/Custom/Editbutton.png");
            position.x = edpval('x', 0);
            position.y = edpval('y', 0);
            position.z = 0;
            return 1;
        }
        if (f == 13) {
            texture = new Texture("res/RPG/Mainmenu/Custom/Deletebutton.png");
            position.x = edpval('x', 1);
            position.y = edpval('y', 1);
            position.z = 0;
            return 2;
        }
        if (f == 17) {
            texture = new Texture("res/RPG/Mainmenu/Custom/Setbutton.png");
            position.x = edpval('x', 2);
            position.y = edpval('y', 2);
            position.z = 0;
            return 3;
        }

        return 0;
    }

    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }


}