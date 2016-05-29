package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Factory that create work (Hell Factory!!!)
 * Created by FTTX on 5/28/2016 AD.
 */
public abstract class WorkFactory {

    /**
     * Create a work
     * @param level that will use to create work
     * @return work created from level
     */

    /**
     * Array for get name
     */
    final private String [] workName = {"Mathematics","Physic","English","Chemistry","Biology"};


    public abstract Work create(int level);
    public abstract String getName();

    public String [] getWorkName() {
        return workName;
    }

}
