package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Imp extends Creature {
    public Imp() {

    }


    public Stats calculatestats(int charLV) {
        return calculatestats.getStats(75, 16, 10, 15, 15, 16, 16, 4, 4, 30, charLV);

    }

    public String normaltexture() {
        return graphicloc.getTexture(5);
    }

    public String whitetexture() {
        return graphicloc.getTexture(5,'w');
    }

    public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.2f) {
            if (abs(System.currentTimeMillis() % 100) < 20) {
                action = getAction(3, Monstat);
            } else
                action = getAction(0, Monstat);
        } else {
            action = getAction(10, Monstat);
        }
        return action;
    }


}
