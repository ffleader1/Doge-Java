package com.thecherno.flappy.updatedigit;

/**
 * Created by fflea_000 on 3/17/2017.
 */
public class Updatedigit {
    public static void inbattledamage(Digit[] digit, float posx, float posy, int Var, char c) {
        if (Var < 0) Var = -Var;
        if (c == '+') c = 'g';
        else if (c == ' ') c = '!';
        else c = 'w';
        if (Var >= 100000) digit[0].update(posx - 1f, posy, Var / 100000, c);
        else digit[0].update(posx + .49f, posy, Var / 10000, '!');

        if (Var >= 10000) digit[1].update(posx - .6f, posy, (Var % 100000) / 10000, c);
        else digit[1].update(posx + .49f, posy, Var / 10000, '!');

        if (Var >= 10) digit[2].update(posx + 0.6f, posy, (Var % 100) / 10, c);
        else digit[2].update(posx + 0.245f, posy, (Var % 100) / 10, '!');

        if (Var >= 100) digit[3].update(posx + .2f, posy, (int) (Var % 1000) / 100, c);
        else digit[3].update(posx, posy, (int) (Var % 1000) / 100, '!');

        if (Var >= 1000) digit[4].update(posx - .2f, posy, (int) (Var % 10000) / 1000, c);
        else digit[4].update(posx - .245f, posy, (int) (Var % 10000) / 1000, '!');

        digit[5].update(posx + 1f, posy, Var % 10, c);

    }


    public static void updateHP(Digit[] digit, float posx, float posy, int Var, char c) {

        if (Var >= 100000) digit[0].update(posx - 1.25f, posy, Var / 100000, c);
        else digit[0].update(posx + .49f, posy, Var / 10000, '!');

        if (Var >= 10000) digit[1].update(posx - .75f, posy, (Var % 100000) / 10000, c);
        else digit[1].update(posx + .49f, posy, Var / 10000, '!');

        if (Var >= 1000) digit[4].update(posx - .25f, posy, (int) (Var % 10000) / 1000, c);
        else digit[4].update(posx - .245f, posy, (int) (Var % 10000) / 1000, '!');

        if (Var >= 100) digit[3].update(posx + .25f, posy, (int) (Var % 1000) / 100, c);
        else digit[3].update(posx, posy, (int) (Var % 1000) / 100, '!');


        if (Var >= 10) digit[2].update(posx + 0.75f, posy, (Var % 100) / 10, c);
        else digit[2].update(posx + 0.245f, posy, (Var % 100) / 10, '!');


        digit[5].update(posx + 1.25f, posy, Var % 10, c);


    }

    public static void updateHPWM(Digit[] digit, float posx, float posy, int Var, char c) {
        if (Var < 0) Var = -Var;

        if (Var >= 100000) digit[0].update(posx - 0.6f, posy, Var / 100000, c);
        else digit[0].update(posx + .49f, posy, Var / 10000, '!');

        if (Var >= 10000) digit[1].update(posx - .36f, posy, (Var % 100000) / 10000, c);
        else digit[1].update(posx + .49f, posy, Var / 10000, '!');

        if (Var >= 10) digit[2].update(posx + 0.36f, posy, (Var % 100) / 10, c);
        else digit[2].update(posx + 0.245f, posy, (Var % 100) / 10, '!');

        if (Var >= 100) digit[3].update(posx + .12f, posy, (int) (Var % 1000) / 100, c);
        else digit[3].update(posx, posy, (int) (Var % 1000) / 100, '!');

        if (Var >= 1000) digit[4].update(posx - .12f, posy, (int) (Var % 10000) / 1000, c);
        else digit[4].update(posx - .245f, posy, (int) (Var % 10000) / 1000, '!');

        digit[5].update(posx + .6f, posy, Var % 10, c);

    }

    public static void updateMPWM(Digit[] digit, float posx, float posy, int Var, char c) {
        if (Var < 0) Var = -Var;


        if (Var >= 10000) digit[0].update(posx - .36f, posy, (Var % 100000) / 10000, c);
        else digit[0].update(posx + .49f, posy, Var / 10000, '!');

        if (Var >= 10) digit[1].update(posx + 0.36f, posy, (Var % 100) / 10, c);
        else digit[1].update(posx + 0.245f, posy, (Var % 100) / 10, '!');

        if (Var >= 100) digit[2].update(posx + .12f, posy, (int) (Var % 1000) / 100, c);
        else digit[2].update(posx, posy, (int) (Var % 1000) / 100, '!');

        if (Var >= 1000) digit[3].update(posx - .12f, posy, (int) (Var % 10000) / 1000, c);
        else digit[3].update(posx - .245f, posy, (int) (Var % 10000) / 1000, '!');

        digit[4].update(posx + 0.6f, posy, Var % 10, c);

    }

    public static void updateotherstatsWM(Digit[] digit, float posx, float posy, int Var, char c) {
        if (Var < 0) Var = -Var;


        if (Var >= 100) digit[0].update(posx + .12f, posy, (int) (Var % 1000) / 100, c);
        else digit[0].update(posx, posy, (int) (Var % 1000) / 100, '!');

        if (Var >= 10) digit[1].update(posx + 0.36f, posy, (Var % 100) / 10, c);
        else digit[1].update(posx + 0.245f, posy, (Var % 100) / 10, '!');

        digit[2].update(posx + 0.6f, posy, Var % 10, c);

    }

    public static void updateLevelWM(Digit[] digit, float posx, float posy, int Var, char c) {
        if (Var < 0) Var = -Var;

        if (Var >= 10) digit[0].update(posx + 0.36f, posy, (Var % 100) / 10, c);
        else digit[1].update(posx + 0.245f, posy, (Var % 100) / 10, '!');

        digit[1].update(posx + 0.6f, posy, Var % 10, c);

    }

}
