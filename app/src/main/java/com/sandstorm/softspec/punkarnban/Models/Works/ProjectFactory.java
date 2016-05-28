package com.sandstorm.softspec.punkarnban.Models.Works;

import com.sandstorm.softspec.punkarnban.Models.Skill.*;

import java.lang.Math;

/**
 * Created by FTTX on 5/28/2016 AD.
 */
public class ProjectFactory implements WorkFactory {

    final private String [] projectName = {"Math","Phy","Chem","Eng","Bio"};
    private int gold = 100;
    private int point = 100;
    private int exp = 5;
    private int time = 60;


    @Override
    public Work create(int level) {
        return new Project(projectName[ (int) Math.round(Math.random()*4)], level*100, gold,point,exp,time);

    }
}
