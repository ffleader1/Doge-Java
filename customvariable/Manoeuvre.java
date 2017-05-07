package com.thecherno.flappy.customvariable;

/**
 * Created by fflea_000 on 3/14/2017.
 */
public class Manoeuvre {
    private String cmdname;
    private int Amount;
    private char Type;

    private int costHP;
    private int costMP;
    private int target = -99;

    public Manoeuvre() {

    }

    public void setcmdname(String name){
        cmdname=name;
    }

    public String getcmdname(){
        return cmdname;
    }

    public void setAmount(int i){
        Amount=i;
    }

    public int getAmount(){
        return Amount;
    }

    public void setType(char c){
        Type=c;
    }

    public char getType(){
        return Type;
    }

    public void setcostHP(int i){
        costHP=i;
    }

    public int getcostHP(){
        return costHP;
    }

    public void setcostMP(int i){
        costMP=i;
    }

    public int getcostMP(){
        return costMP;
    }

    public void settarget(int i){
        target=i;
    }

    public int gettarget(){
        return target;
    }


}
