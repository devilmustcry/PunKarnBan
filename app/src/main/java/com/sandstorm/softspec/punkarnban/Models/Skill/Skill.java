package com.sandstorm.softspec.punkarnban.Models.Skill;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Created by Zen on 5/28/16.
 */
public class Skill{

    private String name;
    private int level;
    private int priceOfNextLevel;
    private int skill_wpt;

    public Skill(String name) {
        this.name = name;
        level = 0;
        priceOfNextLevel = 20;
        skill_wpt = 5;
    }


    public int getApplySkillWPT() {
        return skill_wpt;
    }
    public String getName() {
        return name;
    }
    public int getPriceOfNextLevel() {
        return priceOfNextLevel;
    }

    public void levelUp() {
        level++;
        setAttribute();
    }

    public void setAttribute() {
        if(level == 0) {
            priceOfNextLevel = 20;
            skill_wpt = 5;
        }else {
            priceOfNextLevel = FormulaCalculator.getInstance().calculate(20, 1.15, level , level );
            skill_wpt = FormulaCalculator.getInstance().calculate(5, 1.1 , level , 5*level );
        }

    }

    public void setLevel(int level) {
        this.level = level;
        setAttribute();
    }
    public int getLevel() {
        return level;
    }

    public String getFullName() {
        return name + " Lv."+level;
    }





}
