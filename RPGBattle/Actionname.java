package com.thecherno.flappy.RPGBattle;

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

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
 * import static com.thecherno.flappy.Customvar.*;
 * import static com.thecherno.flappy.utils.readfile.readcustomdata;
 * import static org.lwjgl.glfw.GLFW.GLFW_KEY_END;
 * import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
 * import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
 * <p>
 * /**
 * Created by fflea_000 on 2/25/2017.
 */
public class Actionname {

    private VertexArray mesh;
    private Texture texture;
    private float SIZE = 0.6f;
    private Vector3f position = new Vector3f(-0, 5.0f, 0);


    public Actionname() {
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


    public boolean update(String string) {
        if (string.equals("null")) texture = new Texture("res/RPG/Empty.png");
        if (string.equals("Attack")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Attack.png");
            return true;
        }
        if (string.equals("Blast")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Blast.png");
            return true;
        }
        if (string.equals("Cure")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Cure.png");
            return true;
        }
        if (string.equals("Gravity")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Gravity.png");
            return true;
        }
        if (string.equals("Absorb")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Absorb.png");
            return true;
        }
        if (string.equals("Ultima")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Ultima.png");
            return true;
        }
        if (string.equals("Flee")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Flee.png");
            return true;
        }
        if (string.equals("Heal")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Heal.png");
            return true;
        }
        if (string.equals("Pound")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Pound.png");
            return true;
        }
        if (string.equals("Quickdraw")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Quickdraw.png");
            return true;
        }
        if (string.equals("Despair")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Despair.png");
            return true;
        }
        if (string.equals("Eden")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Eden.png");
            return true;
        }
        if (string.equals("1000 Needles")) {
            texture = new Texture("res/RPG/Battlemenu/Act.1kneedles.png");
            return true;
        }
        if (string.equals("10000 Needles")) {
            texture = new Texture("res/RPG/Battlemenu/Act.10kneedles.png");
            return true;
        }
        if (string.equals("100000 Needles")) {
            texture = new Texture("res/RPG/Battlemenu/Act.100kneedles.png");
            return true;
        }
        if (string.equals("Blizzard")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Blizzard.png");
            return true;
        }
        if (string.equals("Megiddo Flame")) {
            texture = new Texture("res/RPG/Battlemenu/Act.MegiddoFlame.png");
            return true;
        }
        if (string.equals("Full Cure")) {
            texture = new Texture("res/RPG/Battlemenu/Act.Full Cure.png");
            return true;
        }
        if (string.equals("InsufficientMP")) {
            texture = new Texture("res/RPG/Battlemenu/Act.InsufficientMP.png");
            return true;
        }
        if (string.equals("InsufficientHP")) {
            texture = new Texture("res/RPG/Battlemenu/Act.InsufficientHP.png");
            return true;
        }
        return true;

    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }


}
