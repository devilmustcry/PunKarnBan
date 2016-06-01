package com.sandstorm.softspec.punkarnban.Models.Works;

import com.sandstorm.softspec.punkarnban.Models.Skill.*;
import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

import java.lang.Math;

/**
 * Factory for create a project
 * Created by FTTX on 5/28/2016 AD.
 */
public class ProjectFactory extends WorkFactory {




    /**
     * knowledge for this project
     */
    private int exp = 5;

    /**
     * time for this project
     */
    private int time = 60;

    private int hp = 25;


    /**
     *
     * @param level that will use to create work
     * @return Project create from level
     */
    @Override
    public Work create(int level) {

        return new Project(getWorkName()[ (int) Math.round(Math.random()*4)], FormulaCalculator.getInstance().calculate(hp,1.10,level,20*level)*10
                ,FormulaCalculator.getInstance().calculate(exp,1.10,level,15*level)*3,time);

    }

    @Override
    public String getName() {
        return "project";
    }
}
