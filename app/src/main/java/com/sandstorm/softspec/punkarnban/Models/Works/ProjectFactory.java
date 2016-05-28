package com.sandstorm.softspec.punkarnban.Models.Works;

import com.sandstorm.softspec.punkarnban.Models.Skill.*;

import java.lang.Math;

/**
 * Factory for create a project
 * Created by FTTX on 5/28/2016 AD.
 */
public class ProjectFactory implements WorkFactory {

    /**
     * Array for random project name
     */
    final private String [] projectName = {"Math","Phy","Chem","Eng","Bio"};

    /**
     * gold for this project
     */
    private int gold = 100;

    /**
     * point for this project
     */
    private int point = 100;

    /**
     * knowledge for this project
     */
    private int exp = 5;

    /**
     * time for this project
     */
    private int time = 60;


    /**
     *
     * @param level that will use to create work
     * @return Project create from level
     */
    @Override
    public Work create(int level) {

        return new Project(projectName[ (int) Math.round(Math.random()*4)], level*100, gold,point,exp,time);

    }

    @Override
    public String getName() {
        return "project";
    }
}
