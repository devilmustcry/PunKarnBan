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
        priceOfNextLevel = FormulaCalculator.getInstance().calculate(20,1.01,level);
        skill_wpt = FormulaCalculator.getInstance().calculate(5,1.01,level);
        Log.i("Skill Description : ",name + " " + priceOfNextLevel+ " " + skill_wpt);

    }

    public String getFullName() {
        return name + " Lv."+level;
    }



}
