package com.sandstorm.softspec.punkarnban.Models.Recruit;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class Recruit {

    private String name;
    private int DPS;
    private int price;
    private int level;

    public Recruit(String name, int DPS){
        this.name = name;
        this.DPS = DPS;
        price = 10;
        level = 0;
    }
    public String getName(){

        return name;
    }
    public int getDPS(){
        //change rate later
        return DPS;
    }

    public void levelUp(){
        level++;
        price = (int)Math.ceil(10 * Math.pow(1.05 , level));
        DPS = (int)Math.ceil(DPS * Math.pow(1.02 , level));
    }

    public int getPrice(){
        return  price;
    }

    public int getLevel(){
        return level;
    }

}
