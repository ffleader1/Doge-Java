package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;
import static java.lang.Math.abs;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Icehead extends Creature {
    public Icehead() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return calculatestats.getStats(100, 46, 23, 1, 8, 15, 15, 7, 32, 0, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(13);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(13,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getMP() / Monstat.getMAXMP() < 0.9f && (float) Monstat.getMP() / Monstat.getMAXMP() > 0.32f) {
            long l = abs(System.currentTimeMillis() % 100);
            if (l > 60) action = getAction(3, Monstat);
            else if (l > 15) {
                if ((float) Monstat.getHP() / Monstat.getMAXHP() < 0.4f)
                    action = getAction(2, Monstat);
                else action = getAction(1, Monstat);
            } else action = getAction(16, Monstat);
            System.out.println(Monstat.getMP());
        } else action = getAction(16, Monstat);///////////16

        return action;
    }


}
