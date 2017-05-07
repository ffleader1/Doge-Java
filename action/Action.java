package com.thecherno.flappy.action;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.sound.Playsound.*;
import static java.lang.Math.abs;

/**
 * Created by fflea_000 on 3/17/2017.
 */
public class Action {
    private static Manoeuvre Default = new Manoeuvre();
    private static Manoeuvre LackMP = new Manoeuvre();
    private static Manoeuvre LackHP = new Manoeuvre();

    public static Manoeuvre getAction(int skillno, Stats Stats) {
        if (skillno == 0) {

            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK() + Stats.getPTSLUCK())) {
                random = 60;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel()* (Stats.getATK() + Stats.getPTSATK()) / 100));
            Default.setType('-');
            Default.setcmdname ("Attack");

            Default.setcostHP(0);
            Default.setcostMP(0);
            attack.play();
        } else if (skillno == 1) {
            if (Stats.getMP() < 10) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }
            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK() + Stats.getPTSLUCK())) {
                random = 50;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * 4 * (Stats.getMAG() + Stats.getPTSMAG() * Stats.getlevel() * 2 / 5) / 300));
            Default.setType('*');
            Default.setcmdname ("Blast");
            Default.setcostHP(0);
            Default.setcostMP(10);
            blast.play();
        } else if (skillno == 2) {
            if (Stats.getMP()< 30) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }
            int random = (int) System.currentTimeMillis() % 5;

            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * 17 * (Stats.getMAG() + Stats.getPTSMAG() * Stats.getlevel() * 2 / 5) / 500));
            Default.setType('+');
            Default.setcmdname ("Cure");
            Default.setcostHP(0);
            Default.setcostMP(30);
            cure.play();
        } else if (skillno == 3) {
            if (Stats.getMP()< 50) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }
            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK()+ Stats.getPTSLUCK())) {
                random = 30;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * 8 * (Stats.getMAG() + Stats.getPTSMAG() * Stats.getlevel() * 2 / 5) / 500));
            Default.setType('*');
            Default.setcmdname ("Meteor");
            Default.setcostHP( 0);
            Default.setcostMP(50);
            meteor.play();
        } else if (skillno == 4) {
            if (Stats.getMP() < 50) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }

            Default.setAmount(30 + (Stats.getMAG() + Stats.getPTSMAG() * Stats.getlevel() * 2 / 5) / 50);
            Default.setType('%');
            Default.setcmdname ("Gravity");
            Default.setcostHP( 0);
            Default.setcostMP(50);
            gravity.play();
        } else if (skillno == 5) {
            if (Stats.getMP() < 80) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }

            Default.setAmount( 0);
            Default.setType(' ');
            Default.setcmdname ("Absorb");
            Default.setcostHP(0);
            Default.setcostMP(80);
            absorb.play();
        } else if (skillno == 6) {
            if (Stats.getMP() < 120) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }
            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK()+ Stats.getPTSLUCK())) {
                random = 30;

            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * 2 * (Stats.getMAG() + Stats.getPTSMAG() * Stats.getlevel() * 2 / 5) / 100));
            Default.setType('*');
            Default.setcmdname ("Ultima");
            Default.setcostHP(0);
            Default.setcostMP(120);
            ultima.play();

        } else if (skillno == 7) {
            Default.setAmount(0);
            Default.setcmdname ("Flee");
            Default.setcostHP(0);
            Default.setcostMP(0);
            flee.play();
        } else if (skillno == 8) {
            Default.setAmount(0);
            Default.setType(' ');
            Default.setcmdname ("Heal");
            Default.setcostHP(0);
            int nhl = (Stats.getMAXMP() + Stats.getPTSMP() * Stats.getlevel()) * 15 / 100;
            int nhls = Stats.getMAXMP() + Stats.getPTSMP() * Stats.getlevel() - Stats.getMP();
            if (nhls < nhl) Default.setcostMP(-nhls);
            else Default.setcostMP(-nhl);
            heal.play();
        } else if (skillno == 9) {
            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK() + Stats.getPTSLUCK())) {
                random = 30;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * (Stats.getATK() + Stats.getPTSATK()) * 3 / 200));
            Default.setType('-');
            Default.setcmdname ("Pound");
            Default.setcostHP(Default.getAmount() / 4);
            if (Default.getcostHP() >= Stats.getHP()) {
                notavailable.play();
                LackHP.setcmdname ("InsufficientHP");
                return LackHP;
            }
            Default.setcostMP( 0);
            pound.play();
        } else if (skillno == 10) {
            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK()+ Stats.getPTSLUCK())) {
                random = 30;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * 2 * (Stats.getATK() + Stats.getPTSATK()) / 500));
            Default.setType('|');
            Default.setcmdname ("Quickdraw");
            Default.setcostHP(-Default.getAmount() / 10);
            Default.setcostMP(0);
            quickdraw.play();
        } else if (skillno == 11) {
            double hpratio = (double) (Stats.getMAXHP() + Stats.getPTSHP() * Stats.getlevel() * Stats.getlevel()) / (double) Stats.getHP();
            double mpratio = (double) (Stats.getMAXMP() + Stats.getPTSMP() * Stats.getlevel()) / (double) Stats.getMP();
            if (hpratio > 10) hpratio = 10d;
            if (mpratio > 13) mpratio = 13d;

            Default.setAmount((int) (Stats.getlevel() * (Stats.getATK() + Stats.getPTSATK()) * (hpratio + mpratio) / 7));
            Default.setType('|');
            Default.setcmdname ("Despair");
            Default.setcostHP(0);
            Default.setcostMP(0);
            despair.play();
        } else if (skillno == 12) {
            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK() + Stats.getPTSLUCK())) {
                random = 25;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel()* (Stats.getATK() + Stats.getPTSATK()) * 5 / 400));
            Default.setType('-');
            Default.setcmdname ("Eden");
            Default.setcostHP(Stats.getHP() / 5);
            Default.setcostMP( 0);
            eden.play();
        } else if (skillno == 13) {
            Default.setAmount(1000);
            Default.setType('|');
            Default.setcmdname ("1000 Needles");
            Default.setcostHP(0);
            Default.setcostMP( 0);
            needles.play();
        } else if (skillno == 14) {
            Default.setAmount( 10000);
            Default.setType('|');
            Default.setcmdname ("10000 Needles");
            Default.setcostHP(0);
            Default.setcostMP(0);
            needles.play();
        } else if (skillno == 15) {
            Default.setAmount( 100000);
            Default.setType('|');
            Default.setcmdname ("100000 Needles");
            Default.setcostHP(0);
            Default.setcostMP(0);
            needles.play();
        }
        if (skillno == 16) {
            if (Stats.getMP()< 120) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }
            int random = (int) System.currentTimeMillis() % 10;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK() + Stats.getPTSLUCK())) {
                random = 30;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * 2 * (Stats.getMAG() + Stats.getPTSMAG() * Stats.getlevel()* 2 / 5) / 100));
            Default.setType('*');
            Default.setcmdname ("Blizzard");
            Default.setcostHP(0);
            Default.setcostMP(120);
            blizzard.play();
        } else if (skillno == 17) {
            int random = (int) System.currentTimeMillis() % 5;
            if (abs(System.currentTimeMillis()) % 60 < (Stats.getLUCK() + Stats.getPTSLUCK())) {
                random = 25;
            }
            Default.setAmount(Math.round((random + 100) * Stats.getlevel() * (Stats.getATK() + Stats.getPTSATK()) * 5 / 400));
            Default.setType('-');
            Default.setcmdname ("Megiddo Flame");
            Default.setcostHP( Stats.getHP() / 5);
            Default.setcostMP(0);
            megiddoflame.play();
        } else if (skillno == 18) {

            if (Stats.getMP() < 120) {
                notavailable.play();
                LackMP.setcmdname ("InsufficientMP");
                return LackMP;
            }
            Default.setAmount(Stats.getMAXHP()* 2);
            Default.setType('+');
            Default.setcmdname ("Full Cure");
            Default.setcostHP(0);
            Default.setcostMP(120);
            cure.play();

        }
        if (Default.getAmount() > 999999) Default.setAmount( 999999);
        return Default;
    }


}
