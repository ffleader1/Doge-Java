package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Wolf extends Creature {
    public Wolf() {

    }


    @Override public Stats calculatestats(int charLV) {
        return calculatestats.getStats(65, 14, 1, 15, 1, 16, 16, 6, 14, 20, charLV);

    }

    @Override public String normaltexture() {
        return graphicloc.getTexture(2);
    }

    @Override public String whitetexture() {
        return graphicloc.getTexture(2,'w');
    }

    @Override public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.1f) {
            action = getAction(0, Monstat);

        } else {
            action = getAction(7, Monstat);
        }
        return action;
    }


}
