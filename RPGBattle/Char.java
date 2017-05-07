package com.thecherno.flappy.RPGBattle;


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
import static com.thecherno.flappy.action.Action.getAction;

import static com.thecherno.flappy.customvariable.Customvar.lastcheck3;
import static com.thecherno.flappy.updatedigit.Updatedigit.inbattledamage;

//import static com.thecherno.flappy.level.SubmenuStoryMapandButton.insubmenustory;

public class Char implements Entity {

    private final float Xpos = -6.5f;
    private float SIZE = 1.4f;
    private VertexArray mesh;
    private Texture texture, texturenormal, texturewhite;
    // private boolean updatetexture = false;
    private float delta = 0;
    private Stats Char = new Stats();
    private Manoeuvre action = new Manoeuvre();


    private int texturestate = 0;
    private Digit[] digit = new Digit[6];

    private Valtime vl = new Valtime();

    private boolean showval = false;
    //  private char addsub;
    private int absorbcount = 0;

    private Vector3f position = new Vector3f(Xpos, 0f, 0);


    public Char() {
        float[] vertices = new float[]{
                -SIZE, -SIZE / 20 * 25, 0.1f,
                -SIZE, SIZE / 20 * 25, 0.1f,
                SIZE, SIZE / 20 * 25, 0.1f,
                SIZE, -SIZE / 20 * 25, 0.1f
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

    public void updatecharstat(Stats charstat) {
        texture = texturenormal = new Texture("res/RPG/Character/Char.inbattle.png");
        texturewhite = new Texture("res/RPG/Character/Char.inbattle.w.png");

        Char = charstat;

    }




    public Manoeuvre updateaction(int actno) {
        action = getAction(actno, Char);
        if (absorbcount > 0) absorbcount--;
        if (action.getcostHP() != 0) Char.alterHP( - action.getcostHP());
        if (action.getcostMP() != 0) Char.alterMP( - action.getcostMP());
        return action;
    }


    public int updatedamage(Manoeuvre output) {
        if (((output.getcmdname()).equals("InsufficientMP")) || ((output.getcmdname()).equals("InsufficientHP"))) {

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
        vl = calculateOutput(Char, output);

        if (digit[0] != null) inbattledamage(digit, position.x, position.y, vl.value, output.getType());

        if (vl.time < 0) {
            for (int i = 0; i < 6; i++) {
                digit[i] = null;
            }

            Char.alterHP( + vl.value);
            if (Char.getHP() > Char.getMAXHP()+ Char.getPTSHP() * Char.getlevel() * Char.getlevel())
                Char.setHP( Char.getMAXHP() + Char.getPTSHP() * Char.getlevel() * Char.getlevel());
            showval = false;
            vl = new Valtime();
            if ((output.getcmdname()).equals("Absorb")) absorbcount = 3;
            else if (absorbcount > 0) absorbcount--;
            if (Char.getHP() <= 0) return -1;

            return 2;
        }


        return 0;
    }

    public boolean action(float stop) {
        delta += 0.02;

        position.x = Xpos + delta;


        if (delta > stop)
            return true;

        return false;
    }

    public boolean action() {
        delta -= 0.02;

        position.x = Xpos + delta;


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
        return (Char.getSPD() + Char.getPTSSPD());
    }

    public float getXpos() {
        return position.x;
    }

    public float getYpos() {
        return position.y;
    }

    public int getHP() {
        return Char.getHP();
    }

    public int getMP() {
        return Char.getMP();
    }

    public int getLUCK() {
        return Char.getLUCK() + Char.getPTSLUCK();
    }

    public Stats getStats() {
        return Char;
    }
}