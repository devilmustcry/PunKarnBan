package com.sandstorm.softspec.punkarnban.Models.Weapon;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

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
        wpt =  3;
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

    public void setLevel(int level) {
        this.level = level;
        setAttribute();
    }

    public long getPrice(){
        return Math.round(price);
    }

    public int getWPT(){
        return wpt;
    }

    public void levelUp(){
        level++;
        setAttribute();
    }
    private void setAttribute() {
        if(level == 1){
            price = 100;
            wpt = 5;
        }
        else {
            price = FormulaCalculator.getInstance().calculate(100,1.5,level, 100 * level);
            wpt = FormulaCalculator.getInstance().calculate(5,1.1,level, 50 * level);
        }


    }


    public String getFullName(){
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
