package com.thecherno.flappy.graphics;

/**
 * Created by ffleader1 on 4/23/2017.
 */
public class Graphicloc {
    private String[] path= new String[32];
    public Graphicloc() {
        path[0] = "res/RPG/Monster/Flower.png";
        path[1] = "res/RPG/Monster/FlowerW.png";
        path[2] = "res/RPG/Monster/BigWood.png";
        path[3] = "res/RPG/Monster/BigWoodW.png";
        path[4] = "res/RPG/Monster/Wolf.png";
        path[5] = "res/RPG/Monster/wolfW.png";
        path[6] = "res/RPG/Monster/Bandit.png";
        path[7] = "res/RPG/Monster/banditw.png";
        path[8]  = "res/RPG/Monster/Priestess.png";
        path[9] = "res/RPG/Monster/priestessw.png";
        path[10]  = "res/RPG/Monster/Imp.png";
        path[11] = "res/RPG/Monster/impW.png";
        path[12] = "res/RPG/Monster/Crocodile.png";
        path[13]  = "res/RPG/Monster/crocodileW.png";
        path[14] = "res/RPG/Monster/Skeleton.png";
        path[15]   = "res/RPG/Monster/skeletonW.png";
        path[16] = "res/RPG/Monster/Warlord.png";
        path[17] = "res/RPG/Monster/warlordW.png";
        path[18] = "res/RPG/Monster/Wizzard.png";
        path[19] = "res/RPG/Monster/wizzardW.png";
        path[20] = "res/RPG/Monster/Boss.png";
        path[21] = "res/RPG/Monster/bossW.png";
        path[22]  = "res/RPG/Monster/Cactus.png";
        path[23] = "res/RPG/Monster/cactusW.png";
        path[24] = "res/RPG/Monster/Icehand.png";
        path[25] = "res/RPG/Monster/icehandw.png";
        path[26] = "res/RPG/Monster/Icehead.png";
        path[27]  = "res/RPG/Monster/iceheadw.png";
        path[28]  = "res/RPG/Monster/Phoenix.png";
        path[29] = "res/RPG/Monster/phoenixw.png";


    }

    public String getTexture(int i){
        return path[2*i];
    }

    public String getTexture(int i, char c){
        return path[2*i+1];
    }
}
