package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Boss extends Creature {
    public Boss() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(85, 45, 6, 9, 9, 13, 13, 9, 20, 30, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(10);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(10,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getMP() / Monstat.getMAXMP() < 0.2f) {
            action = getAction(8, Monstat);

        } else if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.15f) {
            long l = abs(System.currentTimeMillis() % 100);
            if (l > 60) {
                action = getAction(4, Monstat);///4
            } else if (l > 20) {
                action = getAction(10, Monstat);///10
            } else action = getAction(1, Monstat);

        } else {
            action = getAction(2, Monstat);
        }
        return action;
    }


}
