package com.sandstorm.softspec.punkarnban.Models.Works;

import android.util.Log;

/**
 * Created by FTTX on 5/28/2016 AD.
 */
public class HomeworkFactory implements WorkFactory {

    final private String [] workName = {"Math","Phy","Eng","Chem","Bio"};
    private int index = 0;
    private int gold = 10;
    private int point = 10;
    private int exp = 1;

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
