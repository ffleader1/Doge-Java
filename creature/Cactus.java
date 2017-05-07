package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Cactus extends Creature {
    public Cactus() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(100, 35, 0, 0, 0, 25, 25, 35, 0, 0, charLV);
    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(11);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(11,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.1f) {
            long l = abs(System.currentTimeMillis() % 100);
            if ((l > ((float) (Monstat.getHP() / Monstat.getMAXHP())) * 100) && l % 7 == 0) {
                action = getAction(14, Monstat);
            } else {
                action = getAction(13, Monstat);
            }
        } else {
            long l = abs(System.currentTimeMillis() % 100);
            if (l > 80) {
                action = getAction(7, Monstat);
            } else action = getAction(15, Monstat);
        }

        return action;
    }


}
