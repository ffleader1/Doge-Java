package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Stats;

/**
 * Created by ffleader1 on 5/6/2017.
 */
public class Calculatestats {
    private Stats status = new Stats();
    public Stats getStats(int lvmod, int HP, int MP, int ATK, int MAG, int DEF, int MDEF, int SPD, int LUCK, int EXP, int charLV) {
        status.setlevel(lvmod * charLV / 100);
        status.setMAXHP(HP * status.getlevel() * status.getlevel());
        status.setHP(HP * status.getlevel() * status.getlevel());
        status.setMAXMP(MP * status.getlevel());
        status.setMP(MP * status.getlevel());
        status.setATK(ATK * status.getlevel() * 2 / 5);
        status.setMAG(MAG * status.getlevel() * 2 / 5);
        status.setDEF(DEF * status.getlevel() * 2 / 3);
        status.setMDEF(MDEF * status.getlevel() * 2 / 3);
        status.setSPD(SPD);
        status.setLUCK(LUCK);
        status.setEXP(EXP * status.getlevel() * 10);
        return status;
    }
    public Calculatestats(){}
}
