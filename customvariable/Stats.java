package com.thecherno.flappy.customvariable;

/**
 * Created by fflea_000 on 3/14/2017.
 */
public class Stats {
    private int level = 0;
    private int EXP = 0;
    private int HP = 0;
    private int MAXHP = 0;
    private int MP = 0;
    private int MAXMP = 0;
    private int ATK = 0;
    private int MAG = 0;
    private int DEF = 0;
    private int MDEF = 0;
    private int SPD = 0;
    private int LUCK = 0;
    private int POINTS = 0;
    private int PTSHP = 0;
    private int PTSMP = 0;
    private int PTSATK = 0;
    private int PTSMAG = 0;
    private int PTSDEF = 0;
    private int PTSMDEF = 0;
    private int PTSSPD = 0;
    private int PTSLUCK = 0;
    private boolean Blast;
    private boolean Cure;
    private boolean Meteor;
    private boolean Gravity;
    private boolean Absorb;
    private boolean Ultima;
    private boolean Flee;
    private boolean Heal;
    private boolean Quickdraw;
    private boolean Pound;
    private boolean Despair;
    private boolean Eden;
    private int mapnum;
    private int location;
    private int Achievement;
    private char fieldtype;

    public Stats() {
    }
    public void setEXP(int i){
        EXP=i;
    }

    public int getEXP(){
        return EXP;
    }

    public void alterEXP(int i){
        EXP+=i;
    }

    public void setHP(int i){
        HP=i;
    }

    public int getHP(){
        return HP;
    }

    public void alterHP(int i){
        HP+=i;
    }


    public void setlevel(int i){
        level=i;
    }

    public int getlevel(){
        return level;
    }
    public void alterlevel(int i){
        level+=i;
    }

    public void setMAXHP(int i){
        MAXHP=i;
    }

    public int getMAXHP(){
        return MAXHP;
    }

    public void alterMAXHP(int i){
        MAXHP+=i;
    }

    public void setMP(int i){
        MP=i;
    }

    public int getMP(){
        return MP;
    }

    public void alterMP(int i){
        MP+=i;
    }

    public void setMAXMP(int i){
        MAXMP=i;
    }

    public int getMAXMP(){
        return MAXMP;
    }

    public void alterMAXMP(int i){
        MAXMP+=i;
    }

    public void setATK(int i){
        ATK=i;
    }

    public int getATK(){
        return ATK;
    }

    public void setMAG(int i){
        MAG=i;
    }

    public int getMAG(){
        return MAG;
    }

    public void setDEF(int i){
        DEF=i;
    }

    public int getDEF(){
        return DEF;
    }

    public void setMDEF(int i){
        MDEF=i;
    }

    public int getMDEF(){
        return MDEF;
    }

    public void setSPD(int i){
        SPD=i;
    }

    public int getSPD(){
        return SPD;
    }

    public void setLUCK(int i){
        LUCK=i;
    }

    public int getLUCK(){
        return LUCK;
    }

    public void setPOINTS(int i){
        POINTS=i;
    }

    public int getPOINTS(){
        return POINTS;
    }

    public void alterPOINTS(int i){
        POINTS+=i;
    }

    public void setPTSHP(int i){
        PTSHP=i;
    }

    public int getPTSHP(){
        return PTSHP;
    }

    public void alterPTSHP(int i){
        PTSHP+=i;
    }

    public void setPTSMP(int i){
        PTSMP=i;
    }

    public int getPTSMP(){
        return PTSMP;
    }

    public void alterPTSMP(int i){
        PTSMP+=i;
    }

    public void setPTSATK(int i){
        PTSATK=i;
    }

    public int getPTSATK(){
        return PTSATK;
    }

    public void alterPTSATK(int i){
        PTSATK+=i;
    }

    public void setPTSMAG(int i){
        PTSMAG=i;
    }

    public int getPTSMAG(){
        return PTSMAG;
    }

    public void alterPTSMAG(int i){
        PTSMAG+=i;
    }

    public void setPTSDEF(int i){
        PTSDEF=i;
    }

    public int getPTSDEF(){    return PTSDEF;   }

    public void alterPTSDEF(int i){     PTSDEF+=i;   }

    public void setPTSMDEF(int i){    PTSMDEF=i;  }

    public int getPTSMDEF(){    return PTSMDEF;   }

    public void alterPTSMDEF(int i){     PTSMDEF+=i;   }

    public void setPTSSPD(int i){    PTSSPD=i;  }

    public int getPTSSPD(){    return PTSSPD;   }

    public void alterPTSSPD(int i){     PTSSPD+=i;   }

    public void setPTSLUCK(int i){    PTSLUCK=i;  }

    public int getPTSLUCK(){    return PTSLUCK;   }

    public void alterPTSLUCK(int i){     PTSLUCK+=i;   }

    public void setBlast(boolean b){Blast=b;}

    public boolean getBlast(){return Blast;}

    public void setCure(boolean b){Cure=b;}

    public boolean getCure(){return Cure;}

    public void setMeteor(boolean b){Meteor=b;}

    public boolean getMeteor(){return Meteor;}

    public void setGravity(boolean b){Gravity=b;}

    public boolean getGravity(){return Gravity;}

    public void setAbsorb(boolean b){Absorb=b;}

    public boolean getAbsorb(){return Absorb;}

    public void setUltima(boolean b){Ultima=b;}

    public boolean getUltima(){return Ultima;}

    public void setFlee(boolean b){Flee=b;}

    public boolean getFlee(){return Flee;}

    public void setHeal(boolean b){Heal=b;}

    public boolean getHeal(){return Heal;}

    public void setQuickdraw(boolean b){Quickdraw=b;}

    public boolean getQuickdraw(){return Quickdraw;}

    public void setPound(boolean b){Pound=b;}

    public boolean getPound(){return Pound;}

    public void setDespair(boolean b){Despair=b;}

    public boolean getDespair(){return Despair;}

    public void setEden(boolean b){Eden=b;}

    public boolean getEden(){return Eden;}

    public void setmapnum(int i){mapnum=i;}

    public int getmapnum(){return mapnum;}

    public void altermapnum(int i){     mapnum+=i;   }

    public void setlocation(int i){location=i;}

    public int getlocation(){return location;}


    public void setAchievement(int i){Achievement=i;}

    public int getAchievement(){return Achievement;}

    public void setfieldtype(char i){fieldtype=i;}

    public char getfieldtype(){return fieldtype;}


}

