package com.thecherno.flappy.utils;

import com.thecherno.flappy.customvariable.Stats;

import java.io.*;


/**
 * Created by fflea_000 on 3/17/2017.
 */
public class readData {

    public static Stats readchardat() {
        Stats charstatus = new Stats();
        try {
            int toread = 36;

            int[] number = new int[toread];
            BufferedReader reader = new BufferedReader(new FileReader("res/RPG/CharData/chardat.txt"));
            for (int i = 0; i < toread; i++) {
                number[i] = Integer.parseInt(reader.readLine());
            }
            charstatus.setlevel (number[0]);
            charstatus.setEXP (number[1]);
            charstatus.setHP (number[2]);
            charstatus.setMAXHP( number[3]);
            charstatus.setMP ( number[4]);
            charstatus.setMAXMP ( number[5]);
            charstatus.setATK ( number[6]);
            charstatus.setMAG( number[7]);
            charstatus.setDEF ( number[8]);
            charstatus.setMDEF ( number[9]);
            charstatus.setSPD ( number[10]);
            charstatus.setLUCK ( number[11]);


            charstatus.setPOINTS (number[12]);
            charstatus.setPTSHP ( number[13]);
            charstatus.setPTSMP ( number[14]);
            charstatus.setPTSATK( number[15]);
            charstatus.setPTSMAG ( number[16]);
            charstatus.setPTSDEF(number[17]);
            charstatus.setPTSMDEF (number[18]);
            charstatus.setPTSSPD(number[19]);
            charstatus.setPTSLUCK ( number[20]);


            charstatus.setBlast  (number[21] != 0);
            charstatus.setCure ((number[22] != 0));
            charstatus.setMeteor ( (number[23] != 0));
            charstatus.setGravity( (number[24] != 0));
            charstatus.setAbsorb(number[25] != 0);
            charstatus.setUltima  (number[26] != 0);


            charstatus.setFlee (number[27] != 0);
            charstatus.setHeal  (number[28] != 0);
            charstatus.setPound (number[29] != 0);
            charstatus.setQuickdraw  (number[30] != 0);
            charstatus.setDespair  (number[31] != 0);
            charstatus.setEden  (number[32] != 0);

            charstatus.setmapnum ( number[33]);
            charstatus.setlocation ( number[34]);
            charstatus.setAchievement ( number[35]);

        } catch (IOException e) {

            e.printStackTrace();

        }
        return charstatus;


    }

    public static void writedata(Stats charstatus) {


        BufferedWriter bw = null;
        FileWriter fw = null;

        try {


            fw = new FileWriter("res/RPG/CharData/chardat.txt");
            bw = new BufferedWriter(fw);

            bw.write(new Integer(charstatus.getlevel()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getEXP()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getHP()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getMAXHP()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getMP()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getMAXMP()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getATK()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getMAG()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getDEF()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getMDEF()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getSPD() ).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getLUCK()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPOINTS()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSHP()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSMP()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSATK()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSMAG()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSDEF()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSMDEF()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSSPD()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getPTSLUCK()).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getBlast()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getCure()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getMeteor()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getGravity()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getAbsorb()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getUltima()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getFlee()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getHeal()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getPound()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getQuickdraw()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getDespair()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer((charstatus.getEden()) ? 1 : 0).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getmapnum()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getlocation()).toString());
            bw.newLine();
            bw.write(new Integer(charstatus.getAchievement()).toString());

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

    public static void writedata() {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter("res/RPG/CharData/chardat.txt");
            bw = new BufferedWriter(fw);

            bw.write("14");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("3150");
            bw.newLine();
            bw.write("3150");
            bw.newLine();
            bw.write("175");
            bw.newLine();
            bw.write("175");
            bw.newLine();
            bw.write("34");
            bw.newLine();
            bw.write("34");
            bw.newLine();
            bw.write("93");
            bw.newLine();
            bw.write("93");
            bw.newLine();
            bw.write("12");
            bw.newLine();
            bw.write("25");
            bw.newLine();
            bw.write("5");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("1");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("1");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("1");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("1");

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
}
