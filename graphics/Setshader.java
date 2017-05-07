package com.thecherno.flappy.graphics;

import com.thecherno.flappy.math.Matrix4f;

/**
 * Created by fflea_000 on 2/20/2017.
 */
public class Setshader {

    public static void SetShaderMat() {
        Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, 10.0f * -9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
        Shader.BG.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.BG.setUniform1i("tex", 1);


        Shader.BIRD.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.BIRD.setUniform1i("tex", 1);


        Shader.PIPE.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.PIPE.setUniform1i("tex", 1);


    }
}
