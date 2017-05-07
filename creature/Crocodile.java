package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Crocodile extends Creature {
    public Crocodile() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(65, 15, 13, 11, 11, 9, 10, 5, 17, 20, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(6);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(6,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getMP() / Monstat.getMAXMP() > 0.3f) {
            action = getAction(1, Monstat);
        } else {
            action = getAction(0, Monstat);
        }
        return action;
    }


}
