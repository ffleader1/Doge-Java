package com.thecherno.flappy.Worldmap;

import com.thecherno.flappy.customvariable.Stats;
import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.input.Input;
import com.thecherno.flappy.mapmaker.MapTitles;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;
import com.thecherno.flappy.updatedigit.Digit;

import static com.thecherno.flappy.Main.mapdata;
import static com.thecherno.flappy.clicklocation.Maplocation.*;
import static com.thecherno.flappy.customvariable.Customvar.*;
import static com.thecherno.flappy.input.MouseInput.mouse_button_callback;
import static com.thecherno.flappy.updatedigit.Updatedigit.*;
import static com.thecherno.flappy.utils.AccessMap.readAdjMap;
import static com.thecherno.flappy.utils.AccessMap.readMapdata;
import static com.thecherno.flappy.utils.readData.writedata;
import static java.lang.Math.abs;
import static org.lwjgl.glfw.GLFW.*;
import static com.thecherno.flappy.sound.Playsound.*;

/**
 * Created by fflea_000 on 3/19/2017.
 */
public class Worldmap {

    private VertexArray background, fade;
    private Texture bgTexture;


    private float time = 0.0f;


    private MapTitles[] maptitles = new MapTitles[204];
    private Charsprite charsprite = new Charsprite();

    private boolean loaded, stepped = false;
    private int countstep;
    private int curloc = 0;
    private WorldMapInterface wmi = new WorldMapInterface();
    private Digit[] dHP = new Digit[6];
    private Digit[] dMHP = new Digit[6];
    private Digit[] dMP = new Digit[5];
    private Digit[] dMMP = new Digit[5];
    private Digit[] dATK = new Digit[3];
    private Digit[] dMAG = new Digit[3];
    private Digit[] dDEF = new Digit[3];
    private Digit[] dMDEF = new Digit[3];
    private Digit[] dSPD = new Digit[3];
    private Digit[] dLUCK = new Digit[3];
    private Digit[] dBONUS = new Digit[3];
    private Digit[] dLevel = new Digit[2];
    private char terrain;
    private Mover mover;
    private boolean holding;
    private double cachex, cachey;
    private float cachedir;
    private boolean resetstatspending = false;

    public Worldmap() {
        float[] vertices = new float[]{
                -10.0f, -10.0f * 9.0f / 16.0f, 0.0f, //////THE THIRD ELEMENT IS THE DEPTH, smaller= behind, larger = in front
                -10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                10.0f, -10.0f * 9.0f / 16.0f, 0.0f

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

        fade = new VertexArray(6);
        background = new VertexArray(vertices, indices, tcs);
        for (int i = 0; i < 204; i++) maptitles[i] = new MapTitles(-1);


        bgTexture = new Texture("res/RPG/Empty.png");
        charsprite = new Charsprite();
        for (int i = 0; i < 6; i++) {
            dHP[i] = new Digit();
            dMHP[i] = new Digit();
        }
        for (int i = 0; i < 5; i++) {
            dMP[i] = new Digit();
            dMMP[i] = new Digit();
        }
        for (int i = 0; i < 3; i++) {
            dATK[i] = new Digit();
            dMAG[i] = new Digit();
            dDEF[i] = new Digit();
            dMDEF[i] = new Digit();
            dSPD[i] = new Digit();
            dLUCK[i] = new Digit();
            dBONUS[i] = new Digit();
        }
        for (int i = 0; i < 2; i++) {
            dLevel[i] = new Digit();
        }
        mover = new Mover();

    }

    private void maketitles() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 17; j++) {
                maptitles[i * 17 + j].update(mapdata[i][j], -9.995f + 0.938f * j, 4.685f - .938f * i);
            }
        }

    }

    private boolean triggerencounter() {
        countstep++;
        char c = getTerrain(0f, 0f);
        if (c == '?' || c == '<' || c == '>' || c == '!') return false;

        if (countstep < 10) return false;
        if (Input.keyre[GLFW_KEY_LEFT_SHIFT] || Input.keypr[GLFW_KEY_LEFT_SHIFT]) {
            System.out.println("DEBUG MODE NO ENCOUNTER");
            return false;
        } else {
            if (5 * abs(Math.random() * (1000 - countstep)) < countstep) return true;

        }
        return false;
    }

    private char getTerrain(float deltax, float deltay) {
        curloc = spriteXYtotitleNO(charsprite.getX() + deltax, charsprite.getY() + deltay);
        int i = curloc / 17;
        int j = curloc - 17 * i;
        return mapdata[i][j];
    }

    private void statsupdate(Stats charstatus) {


        if (charstatus.getPTSHP() == 0) {
            if (dMHP[0] != null) updateHPWM(dMHP, 8.5f, 3.84f, charstatus.getMAXHP() + charstatus.getPTSHP(), 'w');
        } else {
            if (dHP[0] != null)
                updateHPWM(dMHP, 8.5f, 3.84f, charstatus.getMAXHP() + charstatus.getPTSHP() * charstatus.getlevel() * charstatus.getlevel(), 'g');
        }

        if (charstatus.getPTSMP() == 0) {
            if (dMMP[0] != null) updateMPWM(dMMP, 8.5f, 2.62f, charstatus.getMAXMP() + charstatus.getPTSMP(), 'w');
        } else {
            if (dMP[0] != null)
                updateMPWM(dMMP, 8.5f, 2.62f, charstatus.getMAXMP()+ charstatus.getPTSMP() * charstatus.getlevel(), 'g');
        }
        if (charstatus.getPTSATK() == 0) {
            if (dATK[0] != null) updateotherstatsWM(dATK, 8.5f, 2.01f, charstatus.getATK()+ charstatus.getPTSATK(), 'w');
        } else {
            if (dATK[0] != null)
                updateotherstatsWM(dATK, 8.5f, 2.01f, charstatus.getATK()+ charstatus.getPTSATK()* charstatus.getlevel() * 2 / 5, 'g');
        }
        if (charstatus.getPTSMAG() == 0) {
            if (dMAG[0] != null) updateotherstatsWM(dMAG, 8.5f, 1.4f, charstatus.getMAG() + charstatus.getPTSMAG(), 'w');
        } else {
            if (dMAG[0] != null)
                updateotherstatsWM(dMAG, 8.5f, 1.4f, charstatus.getMAG() + charstatus.getPTSMAG() * charstatus.getlevel() * 2 / 5, 'g');
        }
        if (charstatus.getPTSDEF() == 0) {
            if (dDEF[0] != null) updateotherstatsWM(dDEF, 8.5f, .79f, charstatus.getDEF() + charstatus.getPTSDEF(), 'w');
        } else {
            if (dDEF[0] != null)
                updateotherstatsWM(dDEF, 8.5f, .79f, charstatus.getDEF() + charstatus.getPTSDEF() * charstatus.getlevel() / 3, 'g');
        }
        if (charstatus.getPTSMDEF()== 0) {
            if (dMDEF[0] != null) updateotherstatsWM(dMDEF, 8.5f, .18f, charstatus.getMDEF() + charstatus.getPTSMDEF(), 'w');
        } else {
            if (dMDEF[0] != null)
                updateotherstatsWM(dMDEF, 8.5f, .18f, charstatus.getMDEF() + charstatus.getPTSMDEF()* charstatus.getlevel() / 3, 'g');
        }
        if (charstatus.getPTSSPD() == 0) {
            if (dSPD[0] != null) updateotherstatsWM(dSPD, 8.5f, -.43f, charstatus.getSPD() + charstatus.getPTSSPD(), 'w');
        } else {
            if (dSPD[0] != null) updateotherstatsWM(dSPD, 8.5f, -.43f, charstatus.getSPD()  + charstatus.getPTSSPD(), 'g');
        }
        if (charstatus.getPTSLUCK() == 0) {
            if (dLUCK[0] != null) updateotherstatsWM(dLUCK, 8.5f, -1.04f, charstatus.getLUCK() + charstatus.getPTSLUCK(), 'w');
        } else {
            if (dLUCK[0] != null)
                updateotherstatsWM(dLUCK, 8.5f, -1.04f, charstatus.getLUCK()+ charstatus.getPTSLUCK(), 'g');
        }
        updateLevelWM(dLevel, 8.5f, -2.26f, charstatus.getlevel(), 'w');
        if (dBONUS[0] != null) updateotherstatsWM(dBONUS, 8.5f, -1.65f, charstatus.getPOINTS(), 'w');
        if (charstatus.getHP() > charstatus.getMAXHP() + charstatus.getPTSHP() * charstatus.getlevel() * charstatus.getlevel())
            charstatus.setHP (charstatus.getMAXHP() + charstatus.getPTSHP() * charstatus.getlevel() * charstatus.getlevel());
        if (charstatus.getMP()> charstatus.getMAXMP() + charstatus.getPTSMP() * charstatus.getlevel())
            charstatus.setMP( charstatus.getMAXMP() + charstatus.getPTSMP()* charstatus.getlevel());

        if (dHP[0] != null) updateHPWM(dHP, 8.5f, 4.45f, charstatus.getHP(), 'w');
        if (dMP[0] != null) updateMPWM(dMP, 8.5f, 3.23f, charstatus.getMP(), 'w');
    }


    public Stats update(Stats charstatus) {
        if (!loaded) {

            try {
                readMapdata(mapdata, charstatus.getmapnum());
            } catch (Exception e) {
                e.printStackTrace();
            }
            maketitles();
            charsprite.render(titleNOtospriteXY(charstatus.getlocation(), 'x'), titleNOtospriteXY(charstatus.getlocation(), 'y'));
            loaded = true;


            wmi.update();
            statsupdate(charstatus);

        } else {
            if (resetstatspending) {
                statsupdate(charstatus);
                resetstatspending = false;
            }
            if (Input.keys[GLFW_KEY_X]) {
                if (getTerrain(0f, 0f) == '?') {
                    resetstatspending = true;
                    charstatus.setfieldtype ('+');
                    Input.keys[GLFW_KEY_X] = false;
                    return charstatus;
                }
                if (getTerrain(0f, 0f) == '!') {
                    charstatus.setfieldtype ('!');
                    charstatus.setlocation ( curloc);
                    Input.keys[GLFW_KEY_X] = false;
                    return charstatus;
                }
            }


            if (System.currentTimeMillis() - lastcheck > 10) {
                if ((Input.keypr[GLFW_KEY_LEFT]) || (Input.keyre[GLFW_KEY_LEFT]) || cachedir == -1f || cachedir == -3.5f || cachedir == 0.5f) {
                    if (getTerrain(-0.1f, 0f) != '0' && getTerrain(-0.1f, 0f) != 'W') {
                        charsprite.update('<');
                        stepped = true;
                    }
                }
                if ((Input.keypr[GLFW_KEY_UP]) || (Input.keyre[GLFW_KEY_UP]) || cachedir == 2f || cachedir == -3.5f || cachedir == -0.5f) {
                    if (getTerrain(0f, 0.1f) != '0' && getTerrain(0f, 0.1f) != 'W') {
                        charsprite.update('^');
                        stepped = true;
                    }

                }
                if ((Input.keyre[GLFW_KEY_DOWN]) || (Input.keypr[GLFW_KEY_DOWN]) || cachedir == -2f || cachedir == 3.5f || cachedir == .5) {
                    if (getTerrain(0f, -0.1f - 0.2345f) != '0' && getTerrain(0f, -0.1f - 0.2345f) != 'W') {
                        charsprite.update('v');
                        stepped = true;
                    }

                }
                if ((Input.keypr[GLFW_KEY_RIGHT]) || (Input.keyre[GLFW_KEY_RIGHT]) || cachedir == 1f || cachedir == -.5f || cachedir == 3.5f) {
                    if (getTerrain(0.1f + 0.2345f, 0f) != '0' && getTerrain(0.1f + 0.2345f, 0f) != 'W') {
                        charsprite.update('>');
                        stepped = true;
                    }
                }
                if (stepped) {
                    terrain = getTerrain(0f, 0f);
                    if (triggerencounter()) {
                        charstatus.setfieldtype (terrain);
                        charstatus.setlocation (curloc);
                        return charstatus;
                    }


                    stepped = false;
                }


                lastcheck = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - lastcheck2 > 150) {
                if (mouse_button_callback == GLFW_PRESS) {
                    int i = bonusstatsupdate(MouseXpos, MouseYpos);
                    if (i < 0) {
                        if (charstatus.getPTSHP() > 0 && i == -1) {
                            charstatus.alterPTSHP(-1);
                            charstatus.alterPOINTS(1);
                        } else if (charstatus.getPTSMP()> 0 && i == -2) {
                            charstatus.alterPTSMP(-1);
                            charstatus.alterPOINTS(1);
                        } else if (charstatus.getPTSATK() > 0 && i == -3) {
                            charstatus.alterPTSATK(-1);
                            charstatus.alterPOINTS(1);
                        } else if (charstatus.getPTSMAG() > 0 && i == -4) {
                            charstatus.alterPTSMAG(-1);
                            charstatus.alterPOINTS(1);
                        } else if (charstatus.getPTSDEF() > 0 && i == -5) {
                            charstatus.alterPTSDEF(-1);
                            charstatus.alterPOINTS(1);
                        } else if (charstatus.getPTSMDEF() > 0 && i == -6) {
                            charstatus.alterPTSMDEF(-1);
                            charstatus.alterPOINTS(1);
                        } else if (charstatus.getPTSSPD() > 0 && i == -7) {
                            charstatus.alterPTSSPD(-1);
                            charstatus.alterPOINTS(1);
                        } else if (charstatus.getPTSLUCK() > 0 && i == -8) {
                            charstatus.alterPTSLUCK(-1);
                            charstatus.alterPOINTS(1);
                        }
                        statsupdate(charstatus);

                    } else if (i > 0) {
                        if (charstatus.getPOINTS() > 0 && i == 1) {
                            charstatus.alterPTSHP(1);
                            charstatus.alterPOINTS(-1);
                        } else if (charstatus.getPOINTS() > 0 && i == 2) {
                            charstatus.alterPTSMP(1);
                            charstatus.alterPOINTS(-1);
                        } else if (charstatus.getPOINTS() > 0 && i == 3) {
                            charstatus.alterPTSATK(1);
                            charstatus.alterPOINTS(-1);
                        } else if (charstatus.getPOINTS() > 0 && i == 4) {
                            charstatus.alterPTSMAG(1);
                            charstatus.alterPOINTS(-1);
                        } else if (charstatus.getPOINTS() > 0 && i == 5) {
                            charstatus.alterPTSDEF(1);
                            charstatus.alterPOINTS(-1);
                        } else if (charstatus.getPOINTS() > 0 && i == 6) {
                            charstatus.alterPTSMDEF(1);
                            charstatus.alterPOINTS(-1);
                        } else if (charstatus.getPOINTS() > 0 && i == 7) {
                            charstatus.alterPTSSPD(1);
                            charstatus.alterPOINTS(-1);
                        } else if (charstatus.getPOINTS()> 0 && i == 8) {
                            charstatus.alterPTSLUCK(1);
                            charstatus.alterPOINTS(-1);
                        }
                        statsupdate(charstatus);
                    } else {
                        if (checkexitsave(MouseXpos, MouseYpos) == 1) {
                            getTerrain(0f, 0f);
                            charstatus.setlocation ( curloc);
                            writedata(charstatus);
                            save.play();
                        }
                        if (checkexitsave(MouseXpos, MouseYpos) == 0) {
                            charstatus.setfieldtype ('.');
                            return charstatus;
                        }

                    }
                }


            }
            if (!holding) {

                if (mouse_button_callback == GLFW_PRESS) {
                    if (checkTitleCoordinate(MouseXpos, MouseYpos) >= 0) {
                        holding = true;
                        cachex = MouseXpos;
                        cachey = MouseYpos;
                        mover.update(cachex, cachey);
                        cachedir = 0;
                    }
                }

            } else {

                float i = checkdir(cachex, cachey, MouseXpos, MouseYpos);
                if (cachedir != i) {
                    mover.maketexture(i, holding);
                    cachedir = i;

                }

                if (mouse_button_callback == GLFW_RELEASE) {
                    holding = false;
                    cachedir = 0;
                    mover.maketexture(0, holding);
                }
            }
        }

        if ((getTerrain(0f, 0f) == '<' || getTerrain(0f, 0f) == '>')) {
            if (Input.keys[GLFW_KEY_X]) {
                Input.keys[GLFW_KEY_X] = false;
                int i = readAdjMap(charstatus.getmapnum(), terrain);
                if (i > 0) {
                    charstatus.setlocation ( i);
                    warp.play();
                    if (terrain == '>') charstatus.altermapnum(1);
                    if (terrain == '<') charstatus.altermapnum(-1);
                    if (charstatus.getmapnum() < 1) charstatus.setmapnum( 8);
                    if (charstatus.getmapnum() > 8) charstatus.setmapnum ( 1);
                    charstatus.setfieldtype (' ');

                    return charstatus;
                }

            }
        }


        time += 1f;

        charstatus.setfieldtype ('_');
        return charstatus;
    }


    public void render() {

        bgTexture.bind();
        Shader.BG.enable();
        background.bind();
        Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(0f, 0.0f, 0.0f)));
        background.draw();
        Shader.BG.disable();
        bgTexture.unbind();


        Shader.FADE.enable();
        Shader.FADE.setUniform1f("time", time);
        fade.render();
        Shader.FADE.disable();


        for (int i = 0; i < 204; i++) {
            if (maptitles[i] != null) maptitles[i].render();
        }
        if (charsprite != null) charsprite.render();
        if (wmi != null) wmi.render();


        for (int i = 0; i < 6; i++) {
            if (dHP[i] != null) dHP[i].render();
            if (dMHP[i] != null) dMHP[i].render();
        }

        for (int i = 0; i < 5; i++) {
            if (dMP[i] != null) dMP[i].render();
            if (dMMP[i] != null) dMMP[i].render();
        }
        for (int i = 0; i < 3; i++) {
            if (dATK[i] != null) dATK[i].render();
            if (dMAG[i] != null) dMAG[i].render();
            if (dDEF[i] != null) dDEF[i].render();
            if (dMDEF[i] != null) dMDEF[i].render();
            if (dSPD[i] != null) dSPD[i].render();
            if (dLUCK[i] != null) dLUCK[i].render();
            if (dBONUS[i] != null) dBONUS[i].render();

        }
        for (int i = 0; i < 2; i++) {
            if (dLevel[i] != null) dLevel[i].render();
        }

        if (mover != null) mover.render();


    }
}

