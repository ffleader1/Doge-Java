package com.thecherno.flappy.RPGBattle;

import com.thecherno.flappy.customvariable.Formation;
import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;
import com.thecherno.flappy.customvariable.Valtime;
import com.thecherno.flappy.graphics.Shader;
import com.thecherno.flappy.graphics.Texture;
import com.thecherno.flappy.graphics.VertexArray;
import com.thecherno.flappy.input.Input;
import com.thecherno.flappy.math.Matrix4f;
import com.thecherno.flappy.math.Vector3f;
import com.thecherno.flappy.updatedigit.Digit;

import static com.thecherno.flappy.sound.Playsound.battleBG;
import static com.thecherno.flappy.sound.Playsound.notavailable;
import static com.thecherno.flappy.sound.Playsound.victory;
import static com.thecherno.flappy.updatedigit.Updatedigit.updateHP;
import static java.lang.Math.abs;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;

/**
 * Created by fflea_000 on 3/12/2017.
 */
public class RPGBattle {

    private VertexArray background, fade;
    private Texture bgTexture;


    private float time = 0.0f;


    private Stats charstat = new Stats();


    private Enemy[] enemy = new Enemy[3];
    private int[] creaturestate = new int[3];


    private Bmenuframe bmnfr;
    private MenuCommand[] cmd = new MenuCommand[9];
    private SelectArrow selectarrow = new SelectArrow();
    private Animation animation;
    private int phase = 0;
    private Digit[] hp = new Digit[6], mp = new Digit[6];

    private Valtime[] selection = new Valtime[3];


    private Manoeuvre act = new Manoeuvre();
    private Char character;


    private boolean loaddat = false;
    private int turn = 3;
    private int monno;
    private int exp;

    private int[] spdcache = new int[4], spdrate = new int[4];
    private Actionname actname;

    private Selection select;
    private int[] checktarget = new int[3];
    private int counttarget = 3;
    private boolean framein = false;
    private int specialbattle = 0;


    public RPGBattle() {
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
        bgTexture = new Texture("res/RPG/Field/Grassland.png");

        for (int i = 0; i < 3; i++) {
            enemy[i] = new Enemy();
        }
        character = new Char();
        bmnfr = new Bmenuframe();
        selectarrow = new SelectArrow();
        resetspdcache();
        actname = new Actionname();
        for (int i = 0; i < 6; i++) {
            hp[i] = new Digit();
            mp[i] = new Digit();
        }
        for (int i = 0; i < 3; i++) {
            selection[i] = new Valtime();
        }
        for (int i = 0; i < 3; i++) {
            checktarget[i] = 0;
        }
        select = new Selection();

        for (int i = 0; i < 9; i++) {
            cmd[i] = new MenuCommand();
        }

    }

    private boolean checkwin() {
        if (creaturestate[0] == -1 && creaturestate[1] == -1 && creaturestate[2] == -1) return true;
        return false;
    }


    private void resetspdcache() {
        for (int i = 0; i < 4; i++) spdcache[i] = 0;
    }


    private int turnbase() {
        int mem = -1;
        for (int i = 0; i < 4; i++) {
            spdrate[i] = 0;
        }
        if (character != null) {
            spdrate[3] = character.getSPD();
        }
        for (int i = 0; i < 3; i++) {
            if (enemy[i] != null) spdrate[i] = enemy[i].getSPD();
        }

        for (int i = 0; i < 4; i++) {
            if (spdcache[i] != 0) {
                spdrate[i] -= spdcache[i];
                mem = i;

            }


        }

        int random = (int) abs(System.currentTimeMillis() % (spdrate[0] + spdrate[1] + spdrate[2] + spdrate[3]));

        if (random < spdrate[3]) {
            if (mem != 3) resetspdcache();
            spdcache[3]++;
            return 3;
        }
        if (random >= (spdrate[3]) && random < (spdrate[3] + spdrate[0])) {
            if (mem != 0) resetspdcache();
            spdcache[0]++;
            monno = 0;
            return monno;
        }
        if (random >= (spdrate[3] + spdrate[0]) && random < (spdrate[3] + spdrate[0] + spdrate[1])) {
            if (mem != 1) resetspdcache();
            spdcache[1]++;
            monno = 1;
            return monno;
        }
        if (mem != 2) resetspdcache();
        spdcache[2]++;
        monno = 2;
        return monno;
    }

    public void loadbattledata(Stats charstatus) {

        charstat = charstatus;

    }

    private void loadmagic(Stats charstatus) {
        if (charstatus.getBlast()) cmd[3].update(3);
        if (charstatus.getCure()) cmd[4].update(4);
        if (charstatus.getMeteor()) cmd[5].update(5);
        if (charstatus.getGravity()) cmd[6].update(6);
        if (charstatus.getAbsorb()) cmd[7].update(7);
        if (charstatus.getUltima()) cmd[8].update(8);
    }

    private void loadtechnique(Stats charstatus) {
        if (charstatus.getFlee()) cmd[3].update(9);
        if (charstatus.getHeal()) cmd[4].update(10);
        if (charstatus.getPound()) cmd[5].update(11);
        if (charstatus.getQuickdraw()) cmd[6].update(12);
        if (charstatus.getDespair()) cmd[7].update(13);
        if (charstatus.getEden()) cmd[8].update(14);
    }

    private boolean checkMagicAvailability(int i, Stats charstatus) {
        if (i == 0 && charstatus.getBlast()) return true;
        if (i == 1 && charstatus.getCure()) return true;
        if (i == 2 && charstatus.getMeteor()) return true;
        if (i == 3 && charstatus.getGravity()) return true;
        if (i == 4 && charstatus.getAbsorb()) return true;
        if (i == 5 && charstatus.getUltima()) return true;
        return false;
    }

    private boolean checkTechniqueAvailability(int i, Stats charstatus) {
        if (i == 0 && charstatus.getFlee()) return true;
        if (i == 1 && charstatus.getHeal()) return true;
        if (i == 2 && charstatus.getPound()) return true;
        if (i == 3 && charstatus.getQuickdraw()) return true;
        if (i == 4 && charstatus.getDespair()) return true;
        if (i == 5 && charstatus.getEden()) return true;
        return false;
    }

    public void hidesubmenu() {
        for (int i = 0; i < 6; i++) cmd[i + 3].update(-1);
    }

    public int update(Formation form, Stats charstatus) {

        if (!loaddat) {

            ///At this point, read data from save file, and return corresponding value
            loadbattledata(charstatus);

            if (charstatus.getfieldtype() == 'G' || charstatus.getfieldtype() == 'g')
                bgTexture = new Texture("res/RPG/Field/Grassland.png");
            else if (charstatus.getfieldtype() == 'D' || charstatus.getfieldtype() == 'd')
                bgTexture = new Texture("res/RPG/Field/Desert.png");
            else if (charstatus.getfieldtype() == 'L' || charstatus.getfieldtype() == 'l')
                bgTexture = new Texture("res/RPG/Field/Lava.png");
            else if (charstatus.getfieldtype() == 'S' || charstatus.getfieldtype() == 's')
                bgTexture = new Texture("res/RPG/Field/Snow.png");
            else if (charstatus.getfieldtype()== 'F') bgTexture = new Texture("res/RPG/Field/Flower.png");
            else if (charstatus.getfieldtype() == 'I') bgTexture = new Texture("res/RPG/Field/Ice.png");
            else if (charstatus.getfieldtype() == 'M') bgTexture = new Texture("res/RPG/Field/Moss.png");
            else if (charstatus.getfieldtype()== '!') bgTexture = new Texture("res/RPG/Field/Dark.png");


            ////////////////////////////////////

            for (int i = 0; i < 3; i++) {
                if (form.monno[i] != -1) {
                    enemy[i].make(i, form.monno[i], charstat.getlevel());
                } else {
                    creaturestate[i] = -1;
                    enemy[i] = null;

                }
                if (form.monno[i] == 10) specialbattle = -2;
                if (form.monno[i] == 11) specialbattle = -3;
                if (form.monno[i] == 13) specialbattle = -5;
                if (form.monno[i] == 14) specialbattle = -7;

            }
            battleBG.play();


            character.updatecharstat(charstat);
            bmnfr.updatein();

            for (int i = 0; i < 3; i++) {
                if (cmd[i] != null) cmd[i].update(i);
            }
            //hidesubmenu();


            loaddat = true;
        } else {

            if (bmnfr.updatein() >= 1) {
                for (int i = 0; i < 6; i++) {
                    updateHP(hp, 8.2f, -2.9f, character.getHP(), 'g');
                }
                for (int i = 0; i < 6; i++) {
                    updateHP(mp, 8.2f, -3.7f, character.getMP(), 'w');
                }


            }


            boolean win = checkwin();
            if (win) {

                if (Input.keypr[GLFW_KEY_LEFT_SHIFT] || Input.keyre[GLFW_KEY_LEFT_SHIFT]) {
                    System.out.println("DEBUG MODE: 100xMORE EXP");
                    charstatus.alterEXP ( exp * 100);
                } else
                    charstatus.alterEXP (exp);
                victory.play();
                if (specialbattle != 0) return specialbattle;
                return 1;

            }

            if (turn == -2) {
                turn = turnbase();


                select = null;
                select = new Selection();
            }

            if (turn == 3) {


                if (select.sl0[0] < 0) {
                    selectarrow.update(select.select0(), 'm');
                    if (select.sl0[0] == 1) loadmagic(charstatus);
                    if (select.sl0[0] == 2) loadtechnique(charstatus);
                } else if (select.sl1[0] < 0 && select.sl0[0] > 0) {
                    selectarrow.update(select.select1(), 's');
                    if (select.sl0[0] == 1 && select.sl1[0] >= 0) {
                        if (!checkMagicAvailability(select.sl1[0], charstatus)) {
                            select.sl1[0] = -1;
                            notavailable.play();
                        }
                    } else if (select.sl0[0] == 2 && select.sl1[0] >= 0) {
                        if (!checkTechniqueAvailability(select.sl1[0], charstatus)) {
                            select.sl1[0] = -1;
                            notavailable.play();
                        }
                    } else if (select.sl0[0] < 0) hidesubmenu();
                } else if (select.sl2[0] < 0) {


                    selectarrow.update(select.select2(), 't');
                    if (select.sl2[1] < 3) {
                        if (enemy[select.sl2[1]] == null) {
                            select.sl2[1]++;
                            if (select.sl2[1] > 2) select.sl2[1] = 0;
                        }
                        if (select.sl0[0] == 2 && select.sl1[0] == 0) {
                            select.sl2[1] = 3;
                        }

                    }
                } else {
                    act = character.updateaction(select.converttoaction());
                    act.settarget(select.sl2[0]);

                    if ((act.getcmdname()).equals("Ultima")) act.settarget(-1);

                    selectarrow.update(0, 'n');
                    hidesubmenu();
                    turn = 23;
                    if ((act.getcmdname()).equals("Flee")) {

                        if (System.currentTimeMillis() % 100 > 2 * character.getLUCK()) {
                            notavailable.play();
                            turn = -2;
                        }
                    }

                }

            }


            if (turn == monno) {
                if (enemy[monno] != null) {
                    act = enemy[monno].updateaction(monno);

                    turn = -monno - 10;
                } else turn = -2;

            }


            if (turn == 23) {


                if (character != null) {
                    if (phase == 0) {


                        if (character.action(2f)) phase++;

                    }
                    if (phase == 1) {
                        if (animation == null) animation = new Animation();
                        if (act.gettarget() >= 3) {
                            if (animation.update(act.getcmdname(), character.getXpos(), character.getYpos())) {
                                animation = null;
                                actname.update(act.getcmdname());

                                phase++;
                            }
                        } else if (act.gettarget() >= 0) {

                            if (animation.update(act.getcmdname(), enemy[act.gettarget()].getXpos(), enemy[act.gettarget()].getYpos())) {
                                animation = null;
                                actname.update(act.getcmdname());
                                phase++;

                            }

                        } else if (act.gettarget() == -1) {

                            if (animation.update(act.getcmdname(), 0, 0)) {
                                animation = null;
                                actname.update(act.getcmdname());

                                phase++;

                            }

                        }
                    }
                    if (phase == 2) {

                        if (act.gettarget() >= 3) {
                            if (character.updateblink()) phase++;
                        } else if (act.gettarget() >= 0) {

                            if (enemy[act.gettarget()].updateblink()) {
                                phase++;

                            }
                        } else if (act.gettarget() == -1) {
                            for (int i = 0; i < 3; i++) {
                                if (enemy[i] != null) {
                                    if (checktarget[i] != 1 && enemy[i].updateblink()) {

                                        checktarget[i] = 1;

                                    }
                                } else checktarget[i] = 1;
                            }
                            if ((checktarget[0] + checktarget[1] + checktarget[2]) >= 3) {
                                for (int i = 0; i < 3; i++) {
                                    checktarget[i] = 0;
                                }
                                phase++;
                            }
                        }
                    }
                    if (phase == 3) {
                        if (character.action()) {
                            phase++;

                        }
                    }
                    if (phase == 4) {
                        if (act.gettarget() >= 3) {
                            int ii = character.updatedamage(act);
                            if (ii == -1) {
                                battleBG.close();
                                return -1;
                            } else if (ii == 2) {
                                phase++;

                            } else if (ii == 10) {
                                battleBG.close();
                                return 2;
                            }
                        } else if (act.gettarget() >= 0) {


                            creaturestate[act.gettarget()] = enemy[act.gettarget()].updatedamage(act);
                            if (creaturestate[act.gettarget()] == 2) {

                                phase++;
                            }
                            if (creaturestate[act.gettarget()] == -1) {
                                exp += enemy[act.gettarget()].getEXP();
                                enemy[act.gettarget()] = null;


                                phase++;
                            }

                        } else if (act.gettarget() == -1) {
                            for (int i = 0; i < 3; i++) {
                                if (enemy[i] != null && checktarget[i] != 1) {
                                    creaturestate[i] = enemy[i].updatedamage(act);
                                    if (creaturestate[i] == 2) {
                                        checktarget[i] = 1;
                                    }
                                    if (creaturestate[i] == -1) {
                                        exp += enemy[i].getEXP();
                                        enemy[i] = null;
                                        checktarget[i] = 1;
                                    }

                                } else if (enemy[i] == null) checktarget[i] = 1;
                            }
                            if ((checktarget[0] + checktarget[1] + checktarget[2]) >= 3) {
                                for (int i = 0; i < 3; i++) {
                                    checktarget[i] = 0;
                                }
                                phase++;
                            }
                        }

                        if (((act.getcmdname()).equals("Eden") || (act.getcmdname()).equals("Megiddo Flame")) && phase == 5) {
                            if (enemy[act.gettarget()] != null) {
                                if (counttarget > 1) {
                                    phase = 0;
                                    counttarget--;
                                    act.setAmount ( act.getAmount() * 108 / 100);

                                }
                            }

                        }
                    }
                    if (phase == 5) {
                        counttarget = 3;
                        turn = -2;

                        if ((act.getcmdname()).equals("Quickdraw")) {
                            if (abs((int) System.currentTimeMillis()) % 100 < 30) {
                                turn = 3;
                                select = null;
                                select = new Selection();

                            }
                        }
                        actname.update("null");
                        phase = 0;

                        act.settarget( -99);
                    }


                }

            }


            if (turn == -monno - 10) {
                if (enemy[monno] != null) {

                    if (phase == 0) {
                        if (enemy[monno].action(2f)) phase++;
                    }
                    if (phase == 1) {
                        if (animation == null) animation = new Animation();

                        if (act.gettarget() >= 3) {
                            if (animation.update(act.getcmdname(), character.getXpos(), character.getYpos())) {
                                animation = null;
                                actname.update(act.getcmdname());

                                phase++;
                            }
                        } else if (act.gettarget() >= 0) {

                            if (animation.update(act.getcmdname(), enemy[act.gettarget()].getXpos(), enemy[act.gettarget()].getYpos())) {
                                animation = null;
                                actname.update(act.getcmdname());
                                phase++;

                            }
                        } else if (act.gettarget() == -1) {

                            if (animation.update(act.getcmdname(), -1, -1)) {
                                animation = null;
                                actname.update(act.getcmdname());
                                phase++;
                            }
                        }
                    }

                    if (phase == 2) {
                        if (act.gettarget() >= 3 || act.gettarget() == -1) {
                            if (character.updateblink()) phase++;
                        } else if (act.gettarget()>= 0) {

                            if (enemy[act.gettarget()].updateblink()) {
                                phase++;

                            }
                        }
                    }
                    if (phase == 3) {
                        if (enemy[monno].action()) phase++;
                    }
                    if (phase == 4) {
                        if (act.gettarget() >= 3 || act.gettarget() == -1) {
                            int ii = character.updatedamage(act);
                            if (ii == -1) {
                                battleBG.close();
                                return -1;
                            } else if (ii == 2) {
                                phase++;

                            }
                        } else if (act.gettarget() >= 0) {


                            creaturestate[act.gettarget()] = enemy[act.gettarget()].updatedamage(act);
                            if (creaturestate[act.gettarget()] == 2) {

                                phase++;
                            }
                            if (creaturestate[act.gettarget()] == -1) {
                                exp += enemy[act.gettarget()].getEXP();
                                enemy[act.gettarget()] = null;

                                phase++;
                            }
                            if (creaturestate[act.gettarget()] == 10) {
                                enemy[act.gettarget()] = null;
                                creaturestate[act.gettarget()] = -1;
                                phase++;
                            }
                        }
                        if (((act.getcmdname()).equals("Eden") || (act.getcmdname()).equals("Megiddo Flame")) && phase == 5) {
                            if (character != null) {
                                if (counttarget > 1) {
                                    phase = 0;
                                    counttarget--;
                                    act.setAmount ( act.getAmount() * 108 / 100);

                                }
                            }
                        }


                    }
                    if (phase == 5) {
                        counttarget = 3;
                        turn = -2;
                        if ((act.getcmdname()).equals("Quickdraw")) {

                            if (abs((int) System.currentTimeMillis()) % 100 < 30) {
                                turn = monno;

                            }
                        }


                        actname.update("null");
                        phase = 0;


                    }


                }
            }


        }


        time += 0.05;
        return 0;
    }

    public Stats returnstats() {
        return character.getStats();
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

        for (int i = 0; i < 3; i++) {
            if (enemy[i] != null) enemy[i].render();
        }
        for (int i = 0; i < 9; i++) {
            if (cmd[i] != null) cmd[i].render();
        }

        if (selectarrow != null) selectarrow.render();
        if (actname != null) actname.render();

        if (character != null) character.render();
        if (bmnfr != null) bmnfr.render();
        if (animation != null) animation.render();
        for (int i = 0; i < 6; i++) {
            if (hp[i] != null) hp[i].render();
            if (mp[i] != null) mp[i].render();
        }

    }
}
