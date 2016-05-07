package com.sandstorm.softspec.punkarnban.Models.Skill;

/**
 * Created by Warata on 5/7/16 AD.
 */
public class Skill {

    private String name;
    private int level;
    private int price;

    public Skill(String name){
        this.name = name;
        level = 1;
    }

    public void levelUp(){
        level++;
    }

    public int getLevel(){
        return this.level;
    }

    public int getPrice(){
        return 100*level;
    }

}
