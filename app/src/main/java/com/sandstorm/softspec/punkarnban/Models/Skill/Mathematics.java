package com.sandstorm.softspec.punkarnban.Models.Skill;

/**
 * Created by Zen on 5/28/16.
 */
public class Mathematics implements Skill{

    private String name;
    private int level;
    private int priceOfNextLevel;
    private int skill_wpt;


    public Mathematics(){
        name = "Mathematics";
        level = 0;
        priceOfNextLevel = 20;
        skill_wpt = 1;
    }


    public void levelUp(){
        level++;
        priceOfNextLevel = (int)Math.ceil(20 * Math.pow(1.01 , level));
        skill_wpt +=  (int)Math.ceil(Math.pow(1.01 , level));
    }

    public String getName(){
        return  name;
    }

    public int getLevel(){
        return level;
    }

    public int getPriceOfNextLevel() {
        return priceOfNextLevel;
    }

    public int getWpt() {
        return skill_wpt;
    }



    @Override
    public int getApplySkillWPT(){
        return skill_wpt;
    }
}
