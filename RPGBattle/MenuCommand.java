package com.thecherno.flappy.RPGBattle;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.customvariable.Customvar.battleMenulocation;

/**
 * package com.thecherno.flappy.ingame;
 * <p>
 * import com.thecherno.flappy.graphics.Shader;
 * import com.thecherno.flappy.graphics.Texture;
 * import com.thecherno.flappy.graphics.VertexArray;
 * import com.thecherno.flappy.input.Input;
 * import com.thecherno.flappy.math.Matrix4f;
 * import com.thecherno.flappy.math.Vector3f;
 * import static com.thecherno.flappy.Main.*;
 * import java.util.Random;
 * <p>
 * import static com.thecherno.flappy.clicklocation.Checkoptionpos.checksquarepos;
 * import static com.thecherno.flappy.customvariable.Customvar.*;
 * import static com.thecherno.flappy.utils.readfile.readcustomdata;
 * import static org.lwjgl.glfw.GLFW.GLFW_KEY_END;
 * import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
 * import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
 * <p>
 * /**
 * Created by fflea_000 on 2/25/2017.
 */
public class MenuCommand {

    private VertexArray mesh;
    private Texture texture;

    private float SIZE = 0.7f;
    private Vector3f position = new Vector3f(-14f, -14f, 0);


    public MenuCommand() {
        float[] vertices = new float[]{
                -SIZE * 5, -SIZE, 0.4f,
                -SIZE * 5, SIZE, 0.4f,
                SIZE * 5, SIZE, 0.4f,
                SIZE * 5, -SIZE, 0.4f

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


    public void update(int i) {
        if (i == -1) position.y = -14;
        if (i == 0) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Attack.png");
            return;
        }
        if (i == 1) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Magic.png");
            return;
        }
        if (i == 2) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Technique.png");
            return;
        }
        if (i == 3) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Blast.png");
            return;
        }
        if (i == 4) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Cure.png");
            return;
        }
        if (i == 5) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Meteor.png");
            return;
        }
        if (i == 6) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Gravity.png");
            return;
        }
        if (i == 7) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Absorb.png");
            return;
        }
        if (i == 8) {
            position.x = battleMenulocation('x', i);
            position.y = battleMenulocation('y', i);
            texture = new Texture("res/RPG/battlemenu/Cmd.Ultima.png");
            return;
        }
        if (i == 9) {
            position.x = battleMenulocation('x', i - 6);
            position.y = battleMenulocation('y', i - 6);
            texture = new Texture("res/RPG/battlemenu/Cmd.Flee.png");
            return;
        }
        if (i == 10) {
            position.x = battleMenulocation('x', i - 6);
            position.y = battleMenulocation('y', i - 6);
            texture = new Texture("res/RPG/battlemenu/Cmd.Heal.png");
            return;
        }
        if (i == 11) {
            position.x = battleMenulocation('x', i - 6);
            position.y = battleMenulocation('y', i - 6);
            texture = new Texture("res/RPG/battlemenu/Cmd.Pound.png");
            return;
        }
        if (i == 12) {
            position.x = battleMenulocation('x', i - 6);
            position.y = battleMenulocation('y', i - 6);
            texture = new Texture("res/RPG/battlemenu/Cmd.Quickdraw.png");
            return;
        }
        if (i == 13) {
            position.x = battleMenulocation('x', i - 6);
            position.y = battleMenulocation('y', i - 6);
            texture = new Texture("res/RPG/battlemenu/Cmd.Despair.png");
            return;
        }
        if (i == 14) {
            position.x = battleMenulocation('x', i - 6);
            position.y = battleMenulocation('y', i - 6);
            texture = new Texture("res/RPG/battlemenu/Cmd.Eden.png");
            return;
        }


    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }


}
