package com.sandstorm.softspec.punkarnban.Models.Recruit;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class Recruit {
    private String name;
    private int DPS;
    public Recruit(String name, int DPS){
        this.name = name;
        this.DPS = DPS;
    }
    public String getName(){
        return name;
    }
    public int getDPS(){
        //change rate later
        return DPS;
    }

}
