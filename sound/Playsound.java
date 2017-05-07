package com.thecherno.flappy.sound;

/**
 * Created by fflea_000 on 3/12/2017.
 */
public class Playsound {
    public static MP3 attack = new MP3("res/RPG/sound/F.attack.mp3");
    public static MP3 notavailable = new MP3("res/RPG/sound/F.notavailable.mp3");
    public static MP3 cure = new MP3("res/RPG/sound/F.cure.mp3");
    public static MP3 blast = new MP3("res/RPG/sound/F.blast.mp3");
    public static MP3 meteor = new MP3("res/RPG/sound/F.meteor.mp3");
    public static MP3 gravity = new MP3("res/RPG/sound/F.gravity.mp3");
    public static MP3 absorb = new MP3("res/RPG/sound/F.absorb.mp3");
    public static MP3 ultima = new MP3("res/RPG/sound/F.ultima.mp3");
    public static MP3 flee = new MP3("res/RPG/sound/F.Flee.mp3");
    public static MP3 heal = new MP3("res/RPG/sound/F.Heal.mp3");
    public static MP3 pound = new MP3("res/RPG/sound/F.pound.mp3");
    public static MP3 quickdraw = new MP3("res/RPG/sound/F.quickdraw.mp3");
    public static MP3 despair = new MP3("res/RPG/sound/F.despair.mp3");
    public static MP3 eden = new MP3("res/RPG/sound/F.eden.mp3");
    public static MP3 battleBG = new MP3("res/RPG/sound/F.battleBG.mp3");
    public static MP3 save = new MP3("res/RPG/sound/F.Save.mp3");
    public static MP3 needles = new MP3("res/RPG/sound/F.Needles.mp3");
    public static MP3 blizzard = new MP3("res/RPG/sound/F.Blizzard.mp3");
    public static MP3 megiddoflame = new MP3("res/RPG/sound/F.MegiddoFlame.mp3");
    public static MP3 hcmenu = new MP3("res/RPG/sound/F.HCmenu.mp3");
    public static MP3 victory = new MP3("res/RPG/Sound/F.Victory.mp3");
    public static MP3 warp = new MP3("res/RPG/Sound/F.warp.mp3");
    public static MP3 bgm;


    private static String getSong(int i) {

        if (i == 0) return "res/RPG/Sound/F.BGM1.mp3";
        if (i == 1) return "res/RPG/sound/bgm1.mp3";
        if (i == 2) return "res/RPG/sound/bgm2.mp3";
        if (i == 3) return "res/RPG/sound/bgm3.mp3";
        if (i == 4) return "res/RPG/sound/bgm4.mp3";
        return "res/sound/bgmMM.mp3";
    }


    public static void changebgm(int i) {
        if (bgm != null) bgm.close();
        if (i < 0) return;
        bgm = new MP3(getSong(i));
        bgm.play();

    }

}
