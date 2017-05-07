package com.thecherno.flappy.creature;

import com.thecherno.flappy.customvariable.Manoeuvre;
import com.thecherno.flappy.customvariable.Stats;
import com.thecherno.flappy.graphics.Graphicloc;

/**
 * Created by ffleader1 on 4/1/2017.
 */
public abstract class Creature {


    protected Manoeuvre action = new Manoeuvre();
    protected Calculatestats calculatestats= new Calculatestats();
    protected Graphicloc graphicloc= new Graphicloc();

    public Creature() {

    }

    abstract public Stats calculatestats(int charLV);

    abstract public String normaltexture();

    abstract public String whitetexture();

    abstract public Manoeuvre act(Stats Monstat);

}
