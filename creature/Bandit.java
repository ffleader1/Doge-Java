package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;

import static com.thecherno.flappy.action.Action.getAction;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Bandit extends Creature {
    public Bandit() {

    }


    @Override  public Stats calculatestats(int charLV) {
        return  calculatestats.getStats(65, 15, 4, 11, 5, 14, 14, 7, 8, 20, charLV);

    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(3);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(3,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.6f) {
            action = getAction(0, Monstat);

        } else if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.2f) {
            action = getAction(9, Monstat);

        } else {
            action = getAction(7, Monstat);
        }
        return action;
    }


}
