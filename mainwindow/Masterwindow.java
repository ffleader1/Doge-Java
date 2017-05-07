package com.thecherno.flappy.mainwindow;

//import com.thecherno.flappy.RPGBattle.RPGBattle;

import com.thecherno.flappy.RPGBattle.RPGBattle;
import com.thecherno.flappy.Worldmap.Worldmap;
import com.thecherno.flappy.customvariable.Formation;
import com.thecherno.flappy.customvariable.Stats;
import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.input.Input;
import com.thecherno.flappy.level.Level;
import com.thecherno.flappy.mapmaker.MapMaker;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.sound.Playsound.*;
import static com.thecherno.flappy.utils.AccessMap.writeMapdata;
import static com.thecherno.flappy.utils.readData.readchardat;
import static com.thecherno.flappy.utils.readData.writedata;
import static java.lang.Math.cbrt;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;


/**
 * Created by fflea_000 on 3/15/2017.
 */
public class Masterwindow {


    public static int achievement;
    private VertexArray background, fade; //MAKE BACKGROUND GRAPHIC HERE
    private Texture bgTexture;
    private RPGBattle rpgbattle;
    private MapMaker mapmaker;
    private Level level;
    private Worldmap worldmap;
    private Stats charstatus = new Stats();
    private boolean readata = false;
    private int outcome;
    private Formation form = new Formation();
    private int cacheEXP, cachelevel;
    private boolean result = false;

    private int state;


    public Masterwindow() {
        float[] vertices = new float[]{
                -10.0f, -10.0f * 9.0f / 16.0f, -0.1f, //////THE THIRD ELEMENT IS THE DEPTH, smaller= behind, larger = in front
                -10.0f, 10.0f * 9.0f / 16.0f, -0.1f,
                10.0f, 10.0f * 9.0f / 16.0f, -0.1f,
                10.0f, -10.0f * 9.0f / 16.0f, -0.1f

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


        background = new VertexArray(vertices, indices, tcs);


        bgTexture = new Texture("res/RPG/Empty.png");


        //  worldmap= new Worldmap();


    }

    private void readdata() {
        try {
            charstatus = readchardat();
            // System.out.println(charstatus.Achievement);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int countdivisible(int a, int b) {

        if (b > a) {
            int count = 0;

            while (true) {
                a = a + 1;
                if (a % 5 == 0) count++;
                if (a == b) break;
            }


            return count;
        }
        return 0;
    }


    private void statschange(boolean resetHPMP, boolean updatestats, boolean forcereset) {
        if (forcereset) {
            charstatus.setHP (charstatus.getMAXHP() + charstatus.getPTSHP() * charstatus.getlevel() * charstatus.getlevel());
            charstatus.setMP ( charstatus.getMAXMP() + charstatus.getPTSMP() * charstatus.getlevel());
            return;
        }
        if (charstatus.getEXP() > 5360000) charstatus.setEXP ( 5360000);
        charstatus.setlevel ( (int) (cbrt(charstatus.getEXP() + 125) + 23) / 2);
        if (updatestats) {
            charstatus.setMAXHP(15 * 15 * charstatus.getlevel());
            charstatus.setMAXMP(  5 * 5 * charstatus.getlevel() / 2);
            charstatus.setATK ( 6 * charstatus.getlevel() * 2 / 5);
            charstatus.setMAG ( 6 * charstatus.getlevel() * 2 / 5);
            charstatus.setDEF( 10 * charstatus.getlevel() * 2 / 3);
            charstatus.setMDEF ( 10 * charstatus.getlevel() * 2 / 3);
            charstatus.setSPD (11);
            charstatus.setLUCK ( 24);

            if (charstatus.getlevel() < 15) {
                charstatus.setCure(false);
                charstatus.setHeal (false);
            } else {
                charstatus.setCure(true);
                charstatus.setHeal (true);
            }
            if (charstatus.getlevel() < 20) {
                charstatus.setMeteor (false);
                charstatus.setPound (false);
            } else {
                charstatus.setMeteor  (true);
                charstatus.setPound ( true);
            }
            if (charstatus.getlevel() < 25) {
                charstatus.setQuickdraw (false);
                charstatus.setGravity (false);
            } else {
                charstatus.setQuickdraw  (true);
                charstatus.setGravity (true);
            }
            if (charstatus.getlevel() < 30) {
                charstatus.setDespair ( false);
                charstatus.setAbsorb (false);
            } else {
                charstatus.setDespair (true);
                charstatus.setAbsorb (true);

            }
            if (charstatus.getlevel() < 40) {
                charstatus.setUltima( false);// <- -------------------------------------

                charstatus.setEden ( false);
            } else {
                charstatus.setUltima(true);

                charstatus.setEden ( true);
            }


            if (resetHPMP && (cachelevel < charstatus.getlevel())) {
                charstatus.setHP (charstatus.getMAXHP() + charstatus.getPTSHP() * charstatus.getlevel() * charstatus.getlevel());
                charstatus.setMP ( charstatus.getMAXMP() + charstatus.getPTSMP() * charstatus.getlevel());
            }
        }

    }

    public void update() {
        if (!readata) {
            readdata();
            statschange(false, true, false);
            achievement = charstatus.getAchievement();
            readata = true;
            level = new Level();
        }
        if (level != null) state = level.update();
        if (state > 0 && state % 11 == 0 && mapmaker == null) {
            level = null;
            mapmaker = new MapMaker();
            changebgm(-1);
            state = state / 11;
        }
        if (state > 0 && state % 13 == 0 && mapmaker == null) {
            level = null;
            writeMapdata(state / 13);
            level = new Level();

        }
        if (state > 0 && state % 17 == 0 && mapmaker == null) {
            level = null;
            readata = false;

        }
        if (state == 23) readata = false;
        if (state > 0 && mapmaker != null) {
            if (mapmaker.update(state)) {
                mapmaker = null;
                state = 0;
                level = new Level();
            }

        }
        if (state == -1) {
            changebgm(-1);
            level = null;
            state = -2;
            worldmap = new Worldmap();

        }
        if (state == -2) {


            if (worldmap != null) {
                charstatus = worldmap.update(charstatus);
                if (charstatus.getfieldtype() != '_' && charstatus.getfieldtype() != '.' && charstatus.getfieldtype() != ' ' && charstatus.getfieldtype() != '+') {

                    form = form.getformation(charstatus.getfieldtype());
                    cacheEXP = charstatus.getEXP();
                    cachelevel = charstatus.getlevel();
                    worldmap = null;
                }
                if (charstatus.getfieldtype() == '.') {

                    level = new Level();

                    state = 0;
                }
                if (charstatus.getfieldtype() == ' ') {

                    worldmap = null;

                    worldmap = new Worldmap();
                }
                if (charstatus.getfieldtype() == '+') {
                    hcmenu.play();


                    statschange(false, false, true);
                }
            }
            if (worldmap == null) {
                if (outcome > -1) {

                    if (rpgbattle == null) rpgbattle = new RPGBattle();

                    outcome = rpgbattle.update(form, charstatus);
                }
                if (outcome > 0) {
                    charstatus = rpgbattle.returnstats();
                    if (outcome == 1) {


                        statschange(false, false, false);
                        if (cachelevel < charstatus.getlevel()) {
                            statschange(true, true, false);

                            charstatus.alterPOINTS ( countdivisible(cachelevel, charstatus.getlevel()));
                        }

                    }
                    rpgbattle = null;
                    outcome = 0;

                    battleBG.close();
                    worldmap = new Worldmap();
                }
                if (outcome == -1) {
                    if (!result) {
                        bgTexture = new Texture("res/RPG/makefield/Gameover.png");
                        rpgbattle = null;
                        battleBG.close();
                        result = true;
                    }
                    if (Input.keys[GLFW_KEY_X]) {
                        bgTexture = new Texture("res/RPG/Empty.png");
                        level = new Level();
                        outcome = 0;
                        state = 0;
                        readata = false;
                        result = false;
                        Input.keys[GLFW_KEY_X] = false;
                    }
                }
                if (outcome <= -2) {
                    if (!result) {
                        if (charstatus.getAchievement() % 2 != 0 && outcome == -2) {
                            bgTexture = new Texture("res/RPG/makefield/Congrat+Completed.png");
                            charstatus.setAchievement ( charstatus.getAchievement() * 2);
                        } else if (charstatus.getAchievement() % 3 != 0 && outcome == -3) {
                            bgTexture = new Texture("res/RPG/makefield/Congrat+Cactus.png");
                            charstatus.setAchievement ( charstatus.getAchievement() * 3);
                        } else if (charstatus.getAchievement() % 5 != 0 && outcome == -5) {
                            bgTexture = new Texture("res/RPG/makefield/Congrat+Icehead.png");
                            charstatus.setAchievement (charstatus.getAchievement()* 5);
                        } else if (charstatus.getAchievement() % 7 != 0 && outcome == -7) {
                            bgTexture = new Texture("res/RPG/makefield/Congrat+Phoenix.png");
                            charstatus.setAchievement ( charstatus.getAchievement() * 7);
                        } else bgTexture = new Texture("res/RPG/makefield/Congrat.png");
                        rpgbattle = null;
                        battleBG.close();
                        result = true;
                    }
                    if (Input.keys[GLFW_KEY_X]) {
                        bgTexture = new Texture("res/RPG/Empty.png");
                        level = new Level();
                        outcome = 0;
                        state = 0;
                        writedata(charstatus);
                        result = false;
                        achievement = charstatus.getAchievement();
                        Input.keys[GLFW_KEY_X] = false;
                    }
                }

            }
            if (state == 0) worldmap = null;
        }


    }


    public void render() {

        bgTexture.bind();
        Shader.BG.enable();
        background.bind();
        Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(0f, 0.0f, 0.0f)));
        background.draw();
        Shader.BG.disable();
        bgTexture.unbind();

        if (rpgbattle != null) rpgbattle.render();
        if (mapmaker != null) mapmaker.render();
        if (worldmap != null) worldmap.render();
        if (level != null) level.render();

    }

}