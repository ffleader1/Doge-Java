package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Warlord extends Creature {
    public Warlord() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(75, 6, 8, 9, 13, 7, 12, 6, 15, 30, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(8);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(8,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getMP() / Monstat.getMAXMP() < 0.3f) {

            action = getAction(8, Monstat);

        } else if ((float) Monstat.getHP() / Monstat.getMAXHP() < 0.3f) {

            action = getAction(2, Monstat);

        } else {
            if (abs(System.currentTimeMillis() % 100) > 20) {
                action = getAction(4, Monstat);
            } else {
                action = getAction(1, Monstat);
            }
        }
        return action;
    }


}
