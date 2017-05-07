package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Bigwood extends Creature {
    public Bigwood() {

    }


    @Override public Stats calculatestats(int charLV) {
        return calculatestats.getStats(75, 17, 6, 10, 4, 10, 10, 5, 15, 30, charLV);

    }

    @Override public String normaltexture() {
        return graphicloc.getTexture(1);
    }

    @Override public String whitetexture() {
        return graphicloc.getTexture(1,'w');
    }

    @Override public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.85f) {
            action = getAction(9, Monstat);

        } else if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.4f) {
            action = getAction(0, Monstat);

        } else {
            action = getAction(2, Monstat);
        }
        return action;
    }


}
