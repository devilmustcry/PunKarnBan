package com.sandstorm.softspec.punkarnban.Models.Works;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Homework Factory
 * Created by FTTX on 5/28/2016 AD.
 */
public class HomeworkFactory extends WorkFactory {


    /**
     * Index
     */
    private int index = 0;

    /**
     * Knowledge
     */
    private int exp = 16;

    /**
     * HP
     */
    private int hp = 25;

    /**
     * create homework
     * @param level that will use to create work
     * @return homework created from level
     */
    @Override
    public Work create(int level) {
        if(index==5)
            index = 0;
        return new Homework(getWorkName()[index++],calculate(hp,1.05,level),calculate(exp,1.015,level));
    }

    public int calculate(int a1,double ratio,int level) {
       return FormulaCalculator.getInstance().calculate(a1,ratio,level);
    }

    @Override
    public String getName() {
        return "homework";
    }
}
