package com.thecherno.flappy.customvariable;

/**
 * Created by fflea_000 on 2/17/2017.
 */
public class Customvar {
    public static double MouseXpos;
    public static double MouseYpos;
    public static boolean Enable_BG_Scroll;
    public static long lastcheck = System.currentTimeMillis();
    public static long lastcheck2 = System.currentTimeMillis();
    public static long lastcheck3 = System.currentTimeMillis();


    private static float[] edpx = new float[3];

    private static float[] recx = new float[8];
    private static float[] recy = new float[8];


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static float[] MonXPos = new float[3];
    private static float[] MonYPos = new float[3];
    private static float[] BmenulocX = new float[9];
    private static float[] BmenulocY = new float[9];


    public static float MonPos(char c, int i) {
        if (c == 'x') {
            MonXPos[0] = 4f;
            MonXPos[1] = 7f;
            MonXPos[2] = 5.5f;
            return MonXPos[i];
        } else {
            MonYPos[0] = -1f;
            MonYPos[1] = 0.25f;
            MonYPos[2] = 1.5f;
            return MonYPos[i];
        }


    }

    public static char getTerrainChar(int i) {
        switch (i) {
            case -4:
                return '>';
            case -3:
                return '!';
            case -2:
                return '<';
            case -1:
                return '?';
            case 0:
                return 'g';
            case 1:
                return 'G';
            case 2:
                return 'd';
            case 3:
                return 'D';
            case 4:
                return 'l';
            case 5:
                return 'L';
            case 6:
                return 'r';
            case 7:
                return 'R';
            case 8:
                return 's';
            case 9:
                return 'S';
            case 10:
                return 'M';
            case 11:
                return 'I';
            case 12:
                return 'F';
            case 13:
                return 'W';
            default:
                return '0';


        }
    }

    public static String datapath(int i) {
        if (i == 1) return "res/RPG/MapData/data1.txt";
        if (i == 2) return "res/RPG/MapData/data2.txt";
        if (i == 3) return "res/RPG/MapData/data3.txt";
        if (i == 4) return "res/RPG/MapData/data4.txt";
        if (i == 5) return "res/RPG/MapData/data5.txt";
        if (i == 6) return "res/RPG/MapData/data6.txt";
        if (i == 7) return "res/RPG/MapData/data7.txt";
        return "res/RPG/MapData/data8.txt";
    }

    public static float battleMenulocation(char c, int i) {
        if (c == 'x') {
            BmenulocX[0] = -7f;
            BmenulocX[1] = -7f;
            BmenulocX[2] = -7f;
            BmenulocX[3] = -2f;
            BmenulocX[4] = -2f;
            BmenulocX[5] = -2f;
            BmenulocX[6] = 3f;
            BmenulocX[7] = 3f;
            BmenulocX[8] = 3f;
            return BmenulocX[i];
        } else {
            BmenulocY[0] = -3.05f;
            BmenulocY[1] = -4.05f;
            BmenulocY[2] = -5.05f;
            BmenulocY[3] = -3.05f;
            BmenulocY[4] = -4.05f;
            BmenulocY[5] = -5.05f;
            BmenulocY[6] = -3.05f;
            BmenulocY[7] = -4.05f;
            BmenulocY[8] = -5.05f;
            return BmenulocY[i];
        }


    }

    public static float recval(char c, int f) {

        if (c == 'x') {

            recx[0] = -4.91f;
            recx[1] = -1.08f;
            recx[2] = -4.91f;
            recx[3] = -1.08f;
            recx[4] = -4.91f;
            recx[5] = -1.08f;
            recx[6] = -4.91f;
            recx[7] = -1.08f;

            return recx[f];//- 0.205f;
        } else {
            recy[0] = 3.30f;
            recy[1] = 3.30f;
            recy[2] = 1.77f;
            recy[3] = 1.77f;
            recy[4] = .20f;
            recy[5] = .20f;
            recy[6] = -1.34f;
            recy[7] = -1.34f;
            return recy[f];
        }

    }

    public static float edpval(char c, int f) {

        if (c == 'x') {
            edpx[0] = -4.92f;
            edpx[1] = -2.995f;
            edpx[2] = -1.09f;
            return edpx[f];//- 0.205f;
        }
        return -2.64f;

    }


}


