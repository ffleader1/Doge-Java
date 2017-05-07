package com.thecherno.flappy.utils;

/**
 * Created by Anh on 3/16/2017.
 */

import java.io.*;

import static com.thecherno.flappy.customvariable.Customvar.datapath;

public class AccessMap {
    public static int readMapdata(char[][] mapdat, int filenum) throws Exception {
        int i, j, k = 0, loc = -1;
        float c;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(datapath(filenum)));
            for (i = 0; i < 12; i++) {
                for (j = 0; j < 17; j++) {
                    c = (float) reader.read();
                    if (c != 32 && c != 10 && c != 13) {
                        mapdat[i][j] = (char) c;
                        if (c != '0') k++;
                        if (c == '?') loc = (i * 17 + j);
                    } else j--;
                }
            }
            if (loc > -1) return loc;
            if (k == 0) return -2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void writeMapdata(char[][] mapdata, int filenum) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(datapath(filenum));
            bw = new BufferedWriter(fw);
            for (int i = 0; i <= 11; i++) {
                for (int j = 0; j < 17; j++) {
                    bw.write(mapdata[i][j]);
                    if (j != 16) bw.write(' ');
                    else bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void writeMapdata(int filenum) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(datapath(filenum));
            bw = new BufferedWriter(fw);
            for (int i = 0; i <= 11; i++) {
                for (int j = 0; j < 17; j++) {
                    bw.write('0');
                    if (j != 16) bw.write(' ');
                    else bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static int readAdjMap(int filenum, char field) {
        int i, j;
        int c;
        if (field == '>') {
            filenum++;

        } else if (field == '<') {
            filenum--;

        }
        if (filenum > 8) filenum = 1;
        if (filenum < 1) filenum = 8;


        try {
            BufferedReader reader = new BufferedReader(new FileReader(datapath(filenum)));

            for (i = 0; i < 12; i++) {
                for (j = 0; j < 17; j++) {
                    c = (int) reader.read();
                    if (c != 32 && c != 10 && c != 13) {

                        if (c == 60 && field == '>') {

                            return (i * 17 + j);
                        }
                        if (c == 62 && field == '<') {

                            return (i * 17 + j);
                        }
                    } else j--;

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
