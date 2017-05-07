package com.thecherno.flappy.clicklocation;

import static com.thecherno.flappy.customvariable.Customvar.edpval;
import static com.thecherno.flappy.customvariable.Customvar.recval;
import static java.lang.Math.abs;

/**
 * Created by Anh on 3/16/2017.
 */
public class Maplocation {
    private static float sizex, sizey;
    private static float windowx = 10.1f, windowy = 5.68f;
    private static float ratiox = 1280 / windowx / 2, ratioy = 720 / windowy / 2;

    public static int checkTitleCoordinate(double x, double y) {
        int a;
        int b;

        if (0 <= x && x <= 1020 && 0 <= y && y <= 720) {
            a = (int) (x) / 60;
            b = (int) (y) / 60;


            return b * 17 + a;
        }
        return -1;
    }


    public static int checkclick(double x, double y) {
        if (1104 < x && x < 1194) {
            if (60 < y && y < 110) {
                return -1;
            }
            if (550 < y && y < 590) {
                return 1;
            }
        }

        return 0;
    }

    public static int checkredsqr(double x, double y) {
        if (1110 < x && x < 1190) {
            if (120 <= y && y < 205) return 0;
            if (205 <= y && y < 290) return 1;
            if (290 <= y && y < 375) return 2;
            if (375 <= y && y < 460) return 3;
            if (460 <= y && y < 545) return 4;
        }
        return -1;
    }

    public static int checkglyph(double x, double y) {
        if (1032 < x && x < 1097 && 565 < y && y < 630) return 0;
        if (1204 < x && x < 1268 && 565 < y && y < 630) return 1;
        if (1032 < x && x < 1097 && 645 < y && y < 710) return 2;
        if (1204 < x && x < 1268 && 645 < y && y < 710) return 3;
        return -1;
    }

    public static int checkexitsave(double x, double y) {
        if (1038 < x && x < 1128 && 12 < y && y < 41) return 0;
        if (1164 < x && x < 1254 && 12 < y && y < 41) return 1;
        return -1;
    }

    public static float checkdir(double x1, double y1, double x2, double y2) {
        if (abs(x1 - x2) <= 30) {
            if ((y1 - y2) >= 30) return +2f;
            if ((y2 - y1) >= 30) return -2f;
        }
        if (x1 - x2 > 30 && y1 - y2 > 30) return -3.5f;
        if (x2 - x1 > 30 && y1 - y2 > 30) return -0.5f;
        if (abs(y1 - y2) <= 30) {
            if ((x1 - x2) >= 30) return -1f;
            if ((x2 - x1) >= 30) return 1f;
        }
        if (x1 - x2 > 30 && y2 - y1 > 30) return .5f;
        if (x2 - x1 > 30 && y2 - y1 > 30) return 3.5f;

        return 0f;
    }

    public static float titleNOtospriteXY(int tn, char c) {
        int i = tn / 17;
        int j = tn - i * 17;
        if (c == 'x') {
            return -9.995f + 0.938f * j;
        } else {
            return 4.685f - .938f * i;
        }

    }

    public static int spriteXYtotitleNO(float xpos, float ypos) {
        float f1 = (xpos + 9.995f) / 0.938f;
        int j = (int) f1;
        if (f1 > 0)
            j = Math.round(f1);

        float f2 = (ypos - 4.685f) / -0.938f;
        int i = (int) f1;
        if (f1 > 0) i = Math.round(f2);
        return (i * 17 + j);

    }

    public static int bonusstatsupdate(double x, double y) {
        if (1248 < x && x < 1274) {
            if (100 < y && y < 113) return 1;
            if (116 < y && y < 130) return -1;
            if (180 < y && y < 193) return 2;
            if (196 < y && y < 209) return -2;
            if (218 < y && y < 231) return 3;
            if (234 < y && y < 247) return -3;
            if (257 < y && y < 270) return 4;
            if (273 < y && y < 286) return -4;
            if (294 < y && y < 307) return 5;
            if (310 < y && y < 323) return -5;
            if (331 < y && y < 344) return 6;
            if (347 < y && y < 360) return -6;
            if (371 < y && y < 384) return 7;
            if (387 < y && y < 400) return -7;
            if (410 < y && y < 423) return 8;
            if (426 < y && y < 439) return -8;
        }

        return 0;
    }

    public static int checkrectanglepos(double x, double y) {
        sizex = 1.8f;
        sizey = 0.5f;
        int i;
        for (i = 0; i < 8; i++) {
            if (((recval('x', i) + windowx - sizex) * ratiox < x) && (x < (recval('x', i) + windowx + sizex) * ratiox) && (((-recval('y', i) + windowy - sizey) * ratioy) < y) && y < ((-recval('y', i) + windowy + sizey) * ratioy)) {
                //  System.out.println("   "+(i));
                return i;
            }

        }

        return -1;
    }

    public static int checkededpppos(double x, double y) {
        sizex = 0.8f;
        sizey = 0.28f;
        int i;
        for (i = 0; i < 3; i++) {
            if (((edpval('x', i) + windowx - sizex) * ratiox < x) && (x < (edpval('x', i) + windowx + sizex) * ratiox) && (((-edpval('y', i) + windowy - sizey) * ratioy) < y) && y < ((-edpval('y', i) + windowy + sizey) * ratioy)) {

                if (i == 0) return 11;
                if (i == 1) return 13;
                if (i == 2) return 17;
            }
        }
        return 0;
    }

}
