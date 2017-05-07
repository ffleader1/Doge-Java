package com.thecherno.flappy.RPGBattle;

import com.thecherno.flappy.customvariable.Manoeuvre;

/**
 * Created by ffleader1 on 5/6/2017.
 */
public interface Entity {
    Manoeuvre updateaction(int monno)  ;
    int updatedamage(Manoeuvre output);
    boolean action(float stop);
    boolean action();
    boolean updateblink();
    void render();

}