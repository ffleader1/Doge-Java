package com.thecherno.flappy.RPGBattle;


import com.thecherno.flappy.creature.*;
import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;
import com.thecherno.flappy.customvariable.Valtime;
import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;
import com.thecherno.flappy.updatedigit.Digit;

import static com.thecherno.flappy.RPGBattle.Calculation.calculateOutput;

import static com.thecherno.flappy.customvariable.Customvar.MonPos;
import static com.thecherno.flappy.customvariable.Customvar.lastcheck3;
import static com.thecherno.flappy.updatedigit.Updatedigit.inbattledamage;




public class Enemy implements Entity {

    Manoeuvre action = new Manoeuvre();
    private float SIZE = 1.5f;
    private VertexArray mesh;
    private Texture texture, texturenormal, texturewhite;
    private float Xpos = 6f;
    private float Ypos = -1f;
    private float delta;
    private Stats Monstat = new Stats();
    private Digit[] digit = new Digit[6];
    private boolean showval = false;
    private Valtime vl = new Valtime();
    private int absorbcount = 0;
    private Creature creature;
    private int texturestate = 0;


    private Vector3f position = new Vector3f(Xpos, Ypos, 0);


    public Enemy() {
        float[] vertices = new float[]{
                -SIZE, -SIZE, 0.1f,
                -SIZE, SIZE, 0.1f,
                SIZE, SIZE, 0.1f,
                SIZE, -SIZE, 0.1f
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


    public void make(int target, int monnum, int charLV) {
        Xpos = MonPos('x', target);
        Ypos = MonPos('y', target);
        position.x = Xpos;
        position.y = Ypos;


        if (monnum == 0) {
            creature = new Flower();
        } else if (monnum == 1) {
            creature = new Bigwood();
        } else if (monnum == 2) {
            creature = new Wolf();
        } else if (monnum == 3) {
            creature = new Bandit();
        } else if (monnum == 4) {
            creature = new Priestess();
        } else if (monnum == 5) {
            creature = new Imp();
        } else if (monnum == 6) {
            creature = new Crocodile();
        } else if (monnum == 7) {
            creature = new Skeleton();
        } else if (monnum == 8) {
            creature = new Warlord();
        } else if (monnum == 9) {
            creature = new Wizzard();
        } else if (monnum == 10) {
            creature = new Boss();
        } else if (monnum == 11) {
            creature = new Cactus();
        } else if (monnum == 12) {
            creature = new Icehand();
        } else if (monnum == 13) {
            creature = new Icehead();
        } else if (monnum == 14) {
            creature = new Phoenix();
        }
        {
            texture = texturenormal = new Texture(creature.normaltexture());
            texturewhite = new Texture(creature.whitetexture());
            Monstat = creature.calculatestats(charLV);
        }


    }


    public Manoeuvre updateaction(int monno) {

        action = creature.act(Monstat);
        if (absorbcount > 0) absorbcount--;


        if ((action.getcmdname()).equals("Cure") || (action.getcmdname()).equals("Full Cure") || (action.getcmdname()).equals("Heal") || (action.getcmdname()).equals("Flee") || (action.getcmdname()).equals("Absorb") || (action.getcmdname()).equals("InsufficientHP") || (action.getcmdname()).equals("InsufficientMP")) {
            action.settarget(monno);
        } else if ((action.getcmdname()).equals("Ultima")) action.settarget(-1);
        else action.settarget(3);
        if (action.getcostHP() != 0) Monstat.alterHP( - action.getcostHP());
        if (action.getcostMP() != 0) Monstat.alterMP( - action.getcostMP());

        return action;
    }

    public boolean action(float stop) {
        delta += 0.02;

        position.x = Xpos - delta;


        if (delta > stop)
            return true;

        return false;
    }

    public boolean action() {
        delta -= 0.02;

        position.x = Xpos - delta;

        if (delta < 0) {
            delta = 0;
            position.x = Xpos;
            return true;
        }
        return false;
    }

    public boolean updateblink() {
        if (texturestate % 2 == 0 && (System.currentTimeMillis() - lastcheck3 > 80)) {
            texture = texturewhite;
            texturestate++;
            lastcheck3 = System.currentTimeMillis();
        }
        if (texturestate % 2 == 1 && (System.currentTimeMillis() - lastcheck3 > 80)) {
            texture = texturenormal;
            texturestate++;
            lastcheck3 = System.currentTimeMillis();
        }
        if (texturestate == 10) {
            texturestate = 0;
            return true;
        }

        return false;
    }


    public int updatedamage(Manoeuvre output) {
        if ((output.getcmdname()).equals("InsufficientMP") || (output.getcmdname()).equals("InsufficientHP")) {

            return 2;
        }

        if (((output.getcmdname()).equals("Flee"))) {
            return 10;

        }

        if (!showval) {
            for (int i = 0; i < 6; i++) {
                digit[i] = new Digit();
            }
            showval = true;
        }
        if (absorbcount > 0) output.setType('+');
        vl = calculateOutput(Monstat, output);

        if (digit[0] != null) inbattledamage(digit, position.x, position.y, vl.value, output.getType());

        if (vl.time < 0) {
            for (int i = 0; i < 6; i++) {
                digit[i] = null;
            }

            Monstat.alterHP( vl.value);
            if (Monstat.getHP() > Monstat.getMAXHP()) Monstat.setHP (Monstat.getMAXHP());
            showval = false;
            vl = new Valtime();
            if ((output.getcmdname()).equals("Absorb")) absorbcount = 3;
            else if (absorbcount > 0) absorbcount--;
            if (Monstat.getHP() <= 0) return -1;

            return 2;
        }


        return 0;

    }


    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();

        for (int i = 0; i < 6; i++) {
            if (digit[i] != null) digit[i].render();
        }

    }

    public int getSPD() {
        return (Monstat.getSPD() + Monstat.getPTSSPD());
    }

    public int getEXP() {
        return (Monstat.getEXP());
    }

    public int getHP() {
        return (Monstat.getHP());
    }

    public float getXpos() {
        return position.x;
    }

    public float getYpos() {
        return position.y;
    }


}