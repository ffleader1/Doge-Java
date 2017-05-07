package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Priestess extends Creature {
    public Priestess() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(75, 14, 11, 10, 13, 9, 14, 5, 14, 30, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(4);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(4,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getMP() / Monstat.getMAXMP() < 0.3f) {
            action = getAction(8, Monstat);

        } else if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.30f) {

            if (abs(System.currentTimeMillis() % 100) < 50) {
                action = getAction(1, Monstat);
            } else
                action = getAction(3, Monstat);

        } else {
            action = getAction(2, Monstat);
        }
        return action;
    }


}
