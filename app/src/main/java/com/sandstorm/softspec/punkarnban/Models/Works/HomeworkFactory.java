package com.sandstorm.softspec.punkarnban.Models.Works;

import android.util.Log;

/**
 * Homework Factory
 * Created by FTTX on 5/28/2016 AD.
 */
public class HomeworkFactory implements WorkFactory {

    /**
     * Array for get name
     */
    final private String [] workName = {"Math","Phy","Eng","Chem","Bio"};

    /**
     * Index
     */
    private int index = 0;

    /**
     * gold
     */
    private int gold = 10;

    /**
     * point
     */
    private int point = 10;

    /**
     * Knowledge
     */
    private int exp = 1;

    /**
     * create homework
     * @param level that will use to create work
     * @return homework created from level
     */
    @Override
    public Work create(int level) {
        if(index==5)
            index = 0;
        Log.i("Level when create",level+"");
        return new Homework(workName[index++], level*20, ++gold,point,exp);
    }

    @Override
    public String getName() {
        return "homework";
    }
}
