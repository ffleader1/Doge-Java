package com.thecherno.flappy.customvariable;

import static java.lang.Math.abs;

/**
 * Created by fflea_000 on 3/19/2017.
 */
public class Formation {
    public int[] monno = new int[3];

    public Formation() {

    }


    private Formation formationG1() {
        Formation form = new Formation();
        form.monno[0] = 0;
        form.monno[1] = 0;
        form.monno[2] = 0;
        return form;
    }

    private Formation formationG2() {
        Formation form = new Formation();
        form.monno[0] = 0;
        form.monno[1] = 1;
        form.monno[2] = 2;
        return form;
    }

    private Formation formationG3() {
        Formation form = new Formation();
        form.monno[0] = 1;
        form.monno[1] = -1;
        form.monno[2] = 1;
        return form;
    }

    private Formation formationD1() {
        Formation form = new Formation();
        form.monno[0] = 2;
        form.monno[1] = 2;
        form.monno[2] = 2;
        return form;
    }

    private Formation formationD2() {
        Formation form = new Formation();
        form.monno[0] = 3;
        form.monno[1] = 2;
        form.monno[2] = 3;
        return form;
    }

    private Formation formationD3() {
        Formation form = new Formation();
        form.monno[0] = 4;
        form.monno[1] = -1;
        form.monno[2] = 4;
        return form;
    }

    private Formation formationL1() {
        Formation form = new Formation();
        form.monno[0] = 6;
        form.monno[1] = 6;
        form.monno[2] = 6;
        return form;
    }

    private Formation formationL2() {
        Formation form = new Formation();
        form.monno[0] = 5;
        form.monno[1] = -1;
        form.monno[2] = 5;
        return form;
    }

    private Formation formationL3() {
        Formation form = new Formation();
        form.monno[0] = 4;
        form.monno[1] = -1;
        form.monno[2] = 5;
        return form;
    }

    private Formation formationR1() {
        Formation form = new Formation();
        form.monno[0] = 2;
        form.monno[1] = 7;
        form.monno[2] = 2;
        return form;
    }

    private Formation formationR2() {
        Formation form = new Formation();
        form.monno[0] = 7;
        form.monno[1] = 2;
        form.monno[2] = 7;
        return form;
    }

    private Formation formationR3() {
        Formation form = new Formation();
        form.monno[0] = 8;
        form.monno[1] = -1;
        form.monno[2] = 8;
        return form;
    }

    private Formation formationS1() {
        Formation form = new Formation();
        form.monno[0] = 9;
        form.monno[1] = 9;
        form.monno[2] = 9;
        return form;
    }

    private Formation formationS2() {
        Formation form = new Formation();
        form.monno[0] = 9;
        form.monno[1] = 1;
        form.monno[2] = 9;
        return form;
    }

    private Formation formationS3() {
        Formation form = new Formation();
        form.monno[0] = 8;
        form.monno[1] = -1;
        form.monno[2] = 1;
        return form;
    }

    private Formation formationBOSS() {
        Formation form = new Formation();
        form.monno[0] = -1;
        form.monno[1] = 10;
        form.monno[2] = -1;
        return form;
    }

    private Formation formationF1() {
        Formation form = new Formation();
        form.monno[0] = 1;
        form.monno[1] = 1;
        form.monno[2] = 1;
        return form;
    }

    private Formation formationF2() {
        Formation form = new Formation();
        form.monno[0] = -1;
        form.monno[1] = 11;
        form.monno[2] = -1;
        return form;
    }

    private Formation formationI1() {
        Formation form = new Formation();
        form.monno[0] = 8;
        form.monno[1] = 8;
        form.monno[2] = 8;
        return form;
    }

    private Formation formationI2() {
        Formation form = new Formation();
        form.monno[0] = 12;
        form.monno[1] = 13;
        form.monno[2] = 12;
        return form;
    }

    private Formation formationM1() {
        Formation form = new Formation();
        form.monno[0] = 4;
        form.monno[1] = 5;
        form.monno[2] = 4;
        return form;
    }

    private Formation formationM2() {
        Formation form = new Formation();
        form.monno[0] = 5;
        form.monno[1] = 4;
        form.monno[2] = 5;
        return form;
    }

    private Formation formationM3() {
        Formation form = new Formation();
        form.monno[0] = -1;
        form.monno[1] = 14;
        form.monno[2] = -1;
        return form;
    }


    public Formation getformation(char c) {
        Formation form = new Formation();
        if (c == 'g' || c == 'G') {
            long l = abs(System.currentTimeMillis() % 100);
            if ((l % 100) > 70) {
                form = formationG1();
            } else if ((l % 100) > 20) {
                form = formationG2();
            } else form = formationG3();

        }
        if (c == 'd' || c == 'D') {

            long l = abs(System.currentTimeMillis() % 100);
            if ((l % 100) > 50) {
                form = formationD1();
            } else if ((l % 100) > 20) {
                form = formationD2();

            } else form = formationD3();

        }
        if (c == 'l' || c == 'L') {

            long l = abs(System.currentTimeMillis() % 100);
            if ((l % 100) > 40) {
                form = formationL1();
            } else if ((l % 100) > 20) {
                form = formationL2();
            } else form = formationL3();

        }
        if (c == 'r' || c == 'R') {

            long l = abs(System.currentTimeMillis() % 100);
            if ((l % 100) > 60) {
                form = formationR1();
            } else if ((l % 100) > 20) {
                form = formationR2();
            } else form = formationR3();

        }
        if (c == 's' || c == 'S') {

            long l = abs(System.currentTimeMillis() % 100);
            if ((l % 100) > 50) {
                form = formationS1();
            } else if ((l % 100) > 20) {
                form = formationS2();
            } else form = formationS3();

        }
        if (c == 'F') {

            long l = abs(System.currentTimeMillis() % 100);// l=1;

            if ((l % 100) > 75) {
                form = formationF1();
            } else form = formationF2();


        }
        if (c == 'I') {

            long l = abs(System.currentTimeMillis() % 100); // l=1;

            if ((l % 100) > 75) {
                form = formationI1();
            } else form = formationI2();


        }
        if (c == 'M') {

            long l = abs(System.currentTimeMillis() % 100); //l=1;

            if ((l % 100) > 87) {
                form = formationM1();
            } else if ((l % 100) > 75) {
                form = formationM2();
            } else form = formationM3();


        }
        if (c == '!') {
            form = formationBOSS();

        }

        return form;
    }


}
