package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Skeleton extends Creature {
    public Skeleton() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(65, 13, 9, 13, 11, 8, 8, 6, 15, 20, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(7);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(7,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.4f) {

            action = getAction(0, Monstat);

        } else {
            action = getAction(9, Monstat);
        }
        return action;
    }


}
