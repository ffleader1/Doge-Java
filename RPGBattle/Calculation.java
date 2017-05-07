package com.thecherno.flappy.RPGBattle;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;
import com.thecherno.flappy.customvariable.Valtime;

import static java.lang.Math.abs;

/**
 * Created by fflea_000 on 3/17/2017.
 */
public class Calculation {


    private static Valtime vt = new Valtime();

    public static Valtime calculateOutput(Stats obj, Manoeuvre output) {
        if (output.getType() == '-') {
            int i = output.getAmount() - (obj.getDEF() + obj.getPTSDEF() * obj.getlevel() / 3) * obj.getlevel() / 3;
            if (i < 0) vt.value = 0;
            else vt.value = -i;

        } else if (output.getType() == '+') vt.value = output.getAmount() / 2;
        else if (output.getType() == '*') {
            int i = output.getAmount() - (obj.getMDEF() + obj.getPTSMDEF() * obj.getlevel() / 3) * obj.getlevel() / 3;
            if (vt.time >= 19.98f) System.out.println(output.getAmount());
            if (vt.time >= 19.98f) System.out.println((obj.getMDEF() + obj.getPTSMDEF()) * obj.getlevel() / 3);
            if (i < 0) vt.value = 0;
            else
                vt.value = -i;

        } else if (output.getType() == '%') vt.value = -obj.getHP() * output.getAmount() / 100;
        else if (output.getType() == '|') vt.value = -output.getAmount();
        else if (output.getType() == ' ') vt.value = 0;

        vt.time += 0.02f;
        if (vt.time < 20f) {
            vt.time += 0.03f;
        } else {
            vt.time = -0.01f;
        }

        return vt;
    }


}
