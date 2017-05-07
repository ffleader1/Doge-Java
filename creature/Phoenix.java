package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Phoenix extends Creature {
    int count = 2;
    int countcache = 2;
    public Phoenix() {

    }

    @Override public Stats calculatestats(int charLV) {
        return calculatestats.getStats(100, 50, 10, 7, 5, 7, 9, 10, 30, 0, charLV);

    }

    @Override public String normaltexture() {
        return graphicloc.getTexture(14);
    }

    @Override public String whitetexture() {
        return graphicloc.getTexture(14,'w');
    }

    @Override public Manoeuvre act(Stats Monstat) {
        if (count < countcache) {
            long l = abs(System.currentTimeMillis() % 100);
            if (l > 30) {
                action = getAction(17, Monstat);
                countcache--;
                return action;
            }
        }
        if ((float) Monstat.getHP() / Monstat.getMAXHP() < 0.25f) {
            if (count > 0) {
                action = getAction(18, Monstat);
                count--;
            } else if (Monstat.getMP() > 80) {
                long l = abs(System.currentTimeMillis() % 100);
                if(l%5==0)action = getAction(5, Monstat);
                else if(l%5==1) action = getAction(2, Monstat);
                else if(l%5==2) action = getAction(3, Monstat);
                else action = getAction(4, Monstat);
            }
        } else if (count > 0 && (float) Monstat.getMP() / Monstat.getMAXMP() < 0.5f) {
            action = getAction(8, Monstat);
        } else {
            long l = abs(System.currentTimeMillis() % 100);
            if (l > 60) {
                action = getAction(0, Monstat);
            } else if (l > 5) {
                action = getAction(9, Monstat);
            } else action = getAction(17, Monstat);

        }

        return action;
    }


}
