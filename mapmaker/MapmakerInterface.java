package com.thecherno.flappy.mapmaker;

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
public class MapmakerInterface {

    private VertexArray mesh;
    private Texture texture;
    private float SIZE = 10.0f;
    private Vector3f position = new Vector3f(0, .0f, 0);


    public MapmakerInterface() {
        float[] vertices = new float[]{
                -SIZE, -SIZE * 9 / 16f, 0.4f,
                -SIZE, SIZE * 9 / 16f, 0.4f,
                SIZE, SIZE * 9 / 16f, 0.4f,
                SIZE, -SIZE * 9 / 16f, 0.4f

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
        texture = new Texture("res/RPG/Makefield/MakefieldBG.png");

    }


    public void update() {


    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }


}
