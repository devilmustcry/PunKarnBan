package com.sandstorm.softspec.punkarnban.Models.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Stationery {
    private static Stationery instance;
    private int level;
    private int wpt;
    private int price;

    private final String [] names  = {"Rusty pencil", "Standard pencil" , "Silver pencil" , "Gold pencil" , "Diamond pencil",
            "Embedded Computer pencil" , "Heavenly pencil" , "Mjolnir's pencil" ,
            "The greatest ever made pencil" , "Pen"};

    private Stationery(){
        level = 1;
        wpt =  1;
        price = 5;
    }

    public static Stationery getInstance(){
        if(instance == null){
            instance = new Stationery();
        }
        return instance;
    }

    public int getLevel(){
        return level;
    }

    public long getPrice(){
        return Math.round(price);
    }

    public int getWPT(){
        return wpt;
    }

    public void levelUp(){
        level++;
        price = (int)Math.ceil(price * 1.02);
        wpt = (int)Math.ceil(5 * Math.pow(1.01 , level));
    }


    public String getFullName(){
        return getName(level) + " lv." + level%100;
    }

    private String getName(int level){
        return names[((int) Math.floor(level / 100.0))];

    }


}
