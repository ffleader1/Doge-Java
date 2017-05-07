package com.thecherno.flappy.clicklocation;

/**
 * Created by fflea_000 on 2/22/2017.
 */
public class Checkoptionpos {
    private static float sizex, sizey;
    private static float windowx = 10.1f, windowy = 5.68f;
    private static float ratiox = 1280 / windowx / 2, ratioy = 720 / windowy / 2;


    public static int checkmainmenuclick(double X, double Y) {
        if (885 < X && X < 1175 && 60 < Y && Y < 145) return 0;
        if (885 < X && X < 1160 && 195 < Y && Y < 263) return 1;
        if (824 < X && X < 1220 && 330 < Y && Y < 385) return 2;
        if (875 < X && X < 1270 && 455 < Y && Y < 515) return 3;
        if (930 < X && X < 1120 && 585 < Y && Y < 640) return 4;
        if (150 < X && X < 215 && 625 < Y && Y < 685) return -2;

        return -1;
    }


    public static int checktutorialarrowpos(double x, double y) {
        sizex = 0.5f;
        sizey = 0.6f;
        float xpos1 = -3.6f;
        float xpos2 = -1.6f;


        if ((xpos1 + windowx - sizex) * ratiox < x && x < (xpos1 + windowx + sizex) * ratiox && (4.5 + windowy - sizey) * ratioy < y && y < (4.5 + windowy + sizey) * ratioy) {

            return -1;
        }
        if ((xpos2 + windowx - sizex) * ratiox < x && x < (xpos2 + windowx + sizex) * ratiox && (4.5 + windowy - sizey) * ratioy < y && y < (4.5 + windowy + sizey) * ratioy) {

            return 1;
        }
        return 0;

    }

    public static boolean checkresetdata(double x, double y) {
        if (x < 559 && x > 336 && y < 545 && 485 < y) return true;
        return false;
    }


}

