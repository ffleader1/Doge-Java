package com.thecherno.flappy.RPGBattle;

/**
 * Created by fflea_000 on 2/22/2017.
 */

import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

public class Animation {


    private VertexArray mesh;
    private Texture texture;

    private float rot = 0.0f;

    private float delta = 0.0f;
    private int subcnt = 0;

    private float SIZE = 1.5f;
    private Vector3f position = new Vector3f(0, 0, 0);
    private boolean started;


    public Animation() {
        float[] vertices = new float[]{
                -SIZE, -SIZE, 0.4f,
                -SIZE, SIZE, 0.4f,
                SIZE, SIZE, 0.4f,
                SIZE, -SIZE, 0.4f

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

    private boolean AttackAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/SwordSlash.png");
            position.x = xpos - 2;
            position.y = ypos;
        }
        delta += .006f;
        position.x = xpos - 2 + delta;
        if (delta > 1.5) {
            return true;
        }
        return false;
    }

    private boolean CureAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Cure.png");
            position.x = xpos;
            position.y = ypos - 2;
        }
        delta += .005f;
        position.y = ypos - 2 + delta;
        if (delta > 1.5) {
            return true;
        }
        return false;
    }

    private boolean BlastAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Blast.png");
            position.x = xpos + 4.5f;
            position.y = ypos + 5.5f;

        }
        delta += .015f;
        position.x = xpos + 4.5f - delta;
        position.y = ypos + 4.5f - delta;
        if (delta > 4.5) {
            return true;
        }
        return false;
    }

    private boolean MeteorAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Meteor.png");
            position.x = xpos;
            position.y = ypos + 5.5f;
        }
        delta += .015f;
        position.y = ypos + 4.5f - delta;
        if (delta > 4.5) {
            return true;
        }
        return false;
    }

    private boolean GravityAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Gravity.png");
            position.x = xpos;
            position.y = ypos;
        }
        delta += .005f;
        if (subcnt % 2 == 0) {
            position.y = ypos + delta;
            position.x = xpos - delta;
        } else {
            position.y = ypos - delta;
            position.x = xpos + delta;
        }
        subcnt++;
        if (delta > 1.5) {
            return true;
        }
        return false;
    }

    private boolean AbsorbAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Absorb.png");
            position.x = xpos;
            position.y = ypos;
        }
        delta += .05f;
        position.x = xpos - 1.5f + delta;

        subcnt++;
        if (delta > 3.5) {

            return true;
        }
        return false;
    }

    private boolean UltimaAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Ultima.png");
            position.x = 0;
            position.y = 0;
        }
        delta += .05f;
        rot = 20 * delta;
        position.x = 10 - (System.currentTimeMillis() % 14) * (System.currentTimeMillis() % 14) / 10f;
        position.y = 5 - (System.currentTimeMillis() % 10) * (System.currentTimeMillis() % 10) / 10f;

        if (delta > 15) {

            return true;
        }
        return false;
    }

    private boolean HealAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Heal.png");
            position.x = xpos;
            position.y = ypos - 2;
        }
        delta += .005f;
        position.y = ypos - 2 + delta;
        if (delta > 1.5) {
            return true;
        }
        return false;
    }

    private boolean PoundAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Pound.png");
            position.x = xpos;
            position.y = ypos - 5.5f;
        }
        delta += .015f;
        position.y = ypos - 2.75f + delta;
        if (delta > 4.5) {
            return true;
        }
        return false;
    }

    private boolean QuickdrawAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Quickdraw.png");
            position.x = xpos;
            position.y = ypos;
        }
        delta += 1.5f;
        rot += delta;
        if (delta > 450) {
            return true;
        }
        return false;
    }

    private boolean DespairAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Despair.png");
            position.x = xpos;
            position.y = ypos;
        }
        delta += 0.005f;
        if (System.currentTimeMillis() % 2 == 0) {
            position.x = xpos + delta;
            position.y = ypos - delta;
        } else {
            position.x = xpos - delta;
            position.y = ypos + delta;
        }
        if (delta > 0.40) {
            return true;
        }
        return false;
    }

    private boolean EdenAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Eden.png");
            position.x = 0;
            position.y = 0;
        }
        delta += 0.005f;
        position.x = 9 - ((System.currentTimeMillis() % 70) + (System.currentTimeMillis() % 40)) / 10.1f;
        position.y = 4 - ((System.currentTimeMillis() % 30) + (System.currentTimeMillis() % 40)) / 7f;


        if (delta > 0.40) {
            return true;
        }
        return false;
    }

    private boolean onethousandneedlesAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/1kneedles.png");
            position.x = xpos + 1.2f;
            position.y = ypos + 1.2f;
        }
        delta += 0.015;
        position.x = xpos + 1.2f - delta;
        position.y = ypos + 1.2f - delta;
        if (delta >= 1.2) {
            return true;
        }

        return false;
    }

    private boolean tenthousandneedlesAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/10kneedles.png");
            position.x = xpos + 1.5f;
            position.y = ypos + 1.5f;
        }
        delta += 0.015;
        position.x = xpos + 1.5f - delta;
        position.y = ypos + 1.5f - delta;
        if (delta >= 1.5) {
            return true;
        }

        return false;
    }

    private boolean onehundredthousandneedlesAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/100kneedles.png");
            position.x = xpos + 1.8f;
            position.y = ypos + 1.8f;
        }
        delta += 0.015;
        position.x = xpos + 1.8f - delta;
        position.y = ypos + 1.8f - delta;
        if (delta >= 1.8) {
            return true;
        }

        return false;
    }

    private boolean BlizzardAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Blizzard.png");
            position.x = 0;
            position.y = 0;
        }
        delta += .05f;
        rot = 20 * delta;
        position.x = 10 - (System.currentTimeMillis() % 14) * (System.currentTimeMillis() % 14) / 10f;
        position.y = 5 - (System.currentTimeMillis() % 10) * (System.currentTimeMillis() % 10) / 10f;

        if (delta > 15) {

            return true;
        }
        return false;
    }

    private boolean MegiddoFlameAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/MegiddoFlame.png");
            position.x = 0;
            position.y = 0;
        }
        delta += 0.005f;
        position.x = 9 - ((System.currentTimeMillis() % 70) + (System.currentTimeMillis() % 40)) / 10.1f;
        position.y = 4 - ((System.currentTimeMillis() % 30) + (System.currentTimeMillis() % 40)) / 7f;


        if (delta > 0.40) {
            return true;
        }
        return false;
    }

    private boolean FullCureAnimation(float xpos, float ypos) {
        if (!started) {
            started = true;
            texture = new Texture("res/RPG/BattleAnimation/Full Cure.png");
            position.x = xpos;
            position.y = ypos;
        }
        delta += 0.15;
        position.x = xpos;

        if ((int) (delta % 4) == 1 || (int) (delta % 4) == 0) position.y = ypos;
        else position.y = -14f;
        if (delta >= 18) {
            return true;
        }
        return false;
    }


    public boolean update(String command, float xpos, float ypos) {
        if (command.equals("Attack")) {
            return (AttackAnimation(xpos, ypos));
        }
        if (command.equals("Cure")) {
            return (CureAnimation(xpos, ypos));
        }
        if (command.equals("Blast")) {
            return (BlastAnimation(xpos, ypos));
        }
        if (command.equals("Meteor")) {
            return (MeteorAnimation(xpos, ypos));
        }
        if (command.equals("Gravity")) {
            return (GravityAnimation(xpos, ypos));
        }
        if (command.equals("Absorb")) {
            return (AbsorbAnimation(xpos, ypos));
        }
        if (command.equals("Ultima")) {
            return (UltimaAnimation(xpos, ypos));
        }
        if (command.equals("Flee")) {
            return true;
        }
        if (command.equals("Heal")) {
            return (HealAnimation(xpos, ypos));
        }
        if (command.equals("Pound")) {
            return (PoundAnimation(xpos, ypos));
        }
        if (command.equals("Quickdraw")) {
            return (QuickdrawAnimation(xpos, ypos));
        }
        if (command.equals("Despair")) {
            return (DespairAnimation(xpos, ypos));
        }
        if (command.equals("Eden")) {
            return (EdenAnimation(xpos, ypos));
        }
        if (command.equals("1000 Needles")) {
            return (onethousandneedlesAnimation(xpos, ypos));
        }
        if (command.equals("10000 Needles")) {
            return (tenthousandneedlesAnimation(xpos, ypos));
        }
        if (command.equals("100000 Needles")) {
            return (onehundredthousandneedlesAnimation(xpos, ypos));
        }
        if (command.equals("Blizzard")) {
            return (BlizzardAnimation(xpos, ypos));
        }
        if (command.equals("Megiddo Flame")) {
            return (MegiddoFlameAnimation(xpos, ypos));
        }
        if (command.equals("Full Cure")) {
            return (FullCureAnimation(xpos, ypos));
        }
        if (command.equals("InsufficientMP")) {
            return true;
        }
        if (command.equals("InsufficientHP")) {

            return true;
        }
        return false;
    }

    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }

    public float getY() {
        return position.y;
    }

    // public float getSize() {       return SIZE;    }


}