package com.thecherno.flappy.mapmaker;

/**
 * Created by fflea_000 on 3/15/2017.
 */


import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;

import static com.thecherno.flappy.Main.mapdata;
import static com.thecherno.flappy.clicklocation.Maplocation.*;
import static com.thecherno.flappy.customvariable.Customvar.*;
import static com.thecherno.flappy.input.MouseInput.mouse_button_callback;
import static com.thecherno.flappy.sound.Playsound.save;
import static com.thecherno.flappy.utils.AccessMap.readMapdata;
import static com.thecherno.flappy.utils.AccessMap.writeMapdata;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

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
public class MapMaker {
    public static float busy = 0;
    private VertexArray background, fade; //MAKE BACKGROUND GRAPHIC HERE
    private Texture bgTexture;
    private float time = 0.0f;
    private boolean loaded = false;
    private float ratio = 1.4f;
    private int atexample = 0;
    private int atredsqr = 0, currentglyph = 0;
    private char currentterrain = 'g';
    private boolean holdingglyph = false, pressing = false;
    private double MouseXcache1 = 0d, MouseXcache2 = 0d, MouseYcache1 = 0d, MouseYcache2 = 2d;


    private char[][] editingdata = new char[12][17];

    private MapTitles[] maptitles = new MapTitles[204];
    private MapTitles[] exampletitles = new MapTitles[5];
    private MapmakerInterface mapmakerinterface = new MapmakerInterface();
    private SelectRedSqr sltredsqr = new SelectRedSqr();
    private Glyph glyph = new Glyph();


    public MapMaker() {
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
        for (int i = 0; i < 5; i++) exampletitles[i] = new MapTitles(ratio);
        glyph = new Glyph();


        bgTexture = new Texture("res/RPG/Empty.png");
        sltredsqr = new SelectRedSqr();


    }

    public void maketitles() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 17; j++) {
                maptitles[i * 17 + j].update(editingdata[i][j], -9.995f + 0.938f * j, 4.685f - .938f * i); //////4.69 works ~~0.01f
            }
        }

    }

    public char makeexampletitles(int rolldir, int resultno) {
        if (rolldir > 0) atexample++;
        else if (rolldir < 0) atexample--;


        if (atexample > 14) atexample = 0;
        if (atexample < 0) atexample = 14;
        for (int i = 0; i < 5; i++) {

            exampletitles[i].update(getTerrainChar((atexample + i) % 15), 7.315f, 2.5f - .938f * ratio * i); /////////////////PROBLEM
        }
        return getTerrainChar((atexample + resultno) % 15);
    }


    public boolean update(int mapno) {
        if (!loaded) {
            try {
                readMapdata(mapdata, mapno);

            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 17; j++) {
                    editingdata[i][j] = mapdata[i][j];
                }
            }
            maketitles();
            makeexampletitles(0, 0);
            loaded = true;

        } else {
            if (!holdingglyph && !pressing) {
                if (mouse_button_callback == GLFW_PRESS) {
                    if (checkTitleCoordinate(MouseXpos, MouseYpos) >= 0) {
                        if (System.currentTimeMillis() - lastcheck > 10) {
                            int coo = checkTitleCoordinate(MouseXpos, MouseYpos);


                            maptitles[coo].update(currentterrain, -9.995f + 0.938f * (coo % 17), 4.685f - .938f * (coo / 17));
                            editingdata[coo / 17][coo % 17] = currentterrain;
                            lastcheck = System.currentTimeMillis();
                        }

                    } else if (checkclick(MouseXpos, MouseYpos) != 0) {
                        if (System.currentTimeMillis() - lastcheck > 100) {

                            currentterrain = makeexampletitles(checkclick(MouseXpos, MouseYpos), atredsqr);
                            lastcheck = System.currentTimeMillis();
                        }
                    } else if ((checkredsqr(MouseXpos, MouseYpos)) >= 0) {
                        if (System.currentTimeMillis() - lastcheck > 100) {
                            atredsqr = checkredsqr(MouseXpos, MouseYpos);
                            sltredsqr.update(atredsqr);
                            currentterrain = makeexampletitles(0, atredsqr);
                            lastcheck = System.currentTimeMillis();
                        }
                    } else if ((checkglyph(MouseXpos, MouseYpos)) >= 0) {
                        if (System.currentTimeMillis() - lastcheck > 100) {
                            holdingglyph = true;
                            glyph.maketexture(currentglyph = checkglyph(MouseXpos, MouseYpos));
                            lastcheck = System.currentTimeMillis();

                        }

                    } else if (checkexitsave(MouseXpos, MouseYpos) >= 0) {
                        if (System.currentTimeMillis() - lastcheck > 100) {
                            if (checkexitsave(MouseXpos, MouseYpos) == 0) return true;
                            else {
                                writeMapdata(editingdata, mapno);
                                save.play();
                            }
                            lastcheck = System.currentTimeMillis();
                        }

                    } else {
                        pressing = true;
                        MouseXcache1 = MouseXpos;
                        MouseYcache1 = MouseYpos;
                        lastcheck = System.currentTimeMillis();
                    }
                }
            } else if (holdingglyph) {
                glyph.update(MouseXpos, MouseYpos);
                if (mouse_button_callback == GLFW_RELEASE) {
                    holdingglyph = false;
                    glyph.maketexture(-1);
                    int coo = checkTitleCoordinate(MouseXpos, MouseYpos);
                    if (coo > 0) {
                        maptitles[coo].update(getTerrainChar(-1 * (currentglyph + 1)), -9.995f + 0.938f * (coo % 17), 4.685f - .938f * (coo / 17));
                        editingdata[coo / 17][coo % 17] = getTerrainChar(-1 * (currentglyph + 1));
                    }
                }


            } else if (pressing) {
                if (mouse_button_callback == GLFW_RELEASE) {
                    pressing = false;
                    MouseXcache1 = 0d;
                    MouseXcache2 = 0d;
                    MouseYcache1 = 0d;
                    MouseYcache2 = 2d;
                }
                if (System.currentTimeMillis() - lastcheck > 50) {
                    MouseXcache2 = MouseXpos;
                    MouseYcache2 = MouseYpos;
                    float cd = checkdir(MouseXcache1, MouseYcache1, MouseXcache2, MouseYcache2);

                    if (cd == 2f) {
                        currentterrain = makeexampletitles(1, atredsqr);
                    } else if (cd == -2f) {
                        currentterrain = makeexampletitles(-1, atredsqr);
                    }
                    MouseXcache1 = MouseXpos;
                    MouseYcache1 = MouseYpos;
                    lastcheck = System.currentTimeMillis();
                }


            }


        }


        time += 0.1f;// if(time>9000f)time=9000f;
        ///////////////////////////////////////////FIX TO 0.01 for FADE
        return false;
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
        for (int i = 0; i < 5; i++) {
            if (exampletitles[i] != null) exampletitles[i].render();
        }
        if (mapmakerinterface != null) mapmakerinterface.render();
        if (sltredsqr != null) sltredsqr.render();
        if (glyph != null) glyph.render();


    }

}
