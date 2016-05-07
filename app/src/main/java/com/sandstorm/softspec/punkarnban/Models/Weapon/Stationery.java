package com.sandstorm.softspec.punkarnban.Models.Weapon;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Stationery {
    private Stationery instance;
    private int level;
    private long wpt;
    private String name;

    private Stationery(){
        level = 1;
        wpt = 1;
        name = "Standard pencil";
    }

    public Stationery getInstance(){
        if(instance == null){
            instance = new Stationery();
        }
        return instance;
    }

    public int getLevel(){
        return  level;
    }

    public long getWPT(){
        return wpt;
    }

    public void setLevel(int new_level){
        level = new_level;
    }

    public void setName(String new_name){
        name = new_name;
    }

    public void setWPT(long new_wpt){
        wpt = new_wpt;
    }

    public String getFullName(){
        return name + " lv. " + level;
    }




}
