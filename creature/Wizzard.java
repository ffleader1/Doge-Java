package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Wizzard extends Creature {
    public Wizzard() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(65, 6, 5, 5, 12, 4, 9, 5, 12, 20, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(9);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(9,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getMP() / Monstat.getMAXMP() < 0.15f) {
            action = getAction(8, Monstat);

        } else {
            action = getAction(1, Monstat);
        }
        return action;
    }


}
