package com.sandstorm.softspec.punkarnban.Models.Weapon;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Stationery {
//    private static Stationery instance;
    private int level;
    private int wpt;
    private int price;

    private final String [] names  = {"Rusty pencil", "Standard pencil" , "Silver pencil" , "Gold pencil" , "Diamond pencil",
            "Embedded Computer pencil" , "Heavenly pencil" , "Mjolnir's pencil" ,
            "The greatest ever made pencil" , "Pen"};

    public Stationery(){
        level = 1;
        wpt =  15;
        price = 100;
    }

//    public static Stationery getInstance(){
//        if(instance == null){
//            instance = new Stationery();
//        }
//        return instance;
//    }

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
        price = (int)Math.ceil(100 * Math.pow(1.15 , level));
        wpt = (int)Math.ceil(15 * Math.pow(1.01 , level));
    }


    public String getFullName(){
        Log.i("Level",level+"");
        String levelShow = level%10+"";
        if(level>=90)
            levelShow = (level-90) +"";
        return getName(level) + " lv." + levelShow;
    }

    public String getName(int level){

        if(level>=90)
            return names[9];
        return names[((int) Math.floor(level / 10.0))];

    }




}
