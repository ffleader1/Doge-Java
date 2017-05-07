package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;


import static com.thecherno.flappy.action.Action.getAction;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public class Flower extends Creature {
    public Flower() {

    }


    @Override  public Stats calculatestats(int charLV) {
       return  calculatestats.getStats(65, 15, 7, 13, 13, 14, 13, 6, 15, 20, charLV);
    }

    @Override  public String normaltexture() {
        return graphicloc.getTexture(0);
    }

    @Override  public String whitetexture() {
        return graphicloc.getTexture(0,'w');
    }

    @Override  public Manoeuvre act(Stats Monstat) {
        if ((float) Monstat.getHP() / Monstat.getMAXHP() > 0.3f) {
            action = getAction(0, Monstat);

        } else {
            action = getAction(2, Monstat);
        }
        return action;
    }


}
