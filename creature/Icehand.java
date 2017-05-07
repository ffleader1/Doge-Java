package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Icehand extends Creature {
    public Icehand() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(90, 12, 19, 6, 7, 12, 12, 6, 17, 0, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(12);

    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(12,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getMP() / Monstat.getMAXMP() < 0.2f) {
            action = getAction(8, Monstat);
        } else {
            long l = abs(System.currentTimeMillis() % 100);
            if (l > 40) action = getAction(3, Monstat);
            else if (l % 3 != 0) action = getAction(2, Monstat);
            else action = getAction(9, Monstat);/////////9
        }
        return action;
    }


}
