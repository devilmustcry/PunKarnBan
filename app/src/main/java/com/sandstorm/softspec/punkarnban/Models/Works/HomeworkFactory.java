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
        return new Homework(getWorkName()[index++],calculate(hp,1.10,level,20*level),calculate(exp,1.10,level,15*level));
    }

    public int calculate(int a1,double ratio,int level,int constant) {
       return FormulaCalculator.getInstance().calculate(a1,ratio,level,constant);
    }

    @Override
    public String getName() {
        return "homework";
    }
}
