package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Factory that create work (Hell Factory!!!)
 * Created by FTTX on 5/28/2016 AD.
 */
public interface WorkFactory {

    /**
     * Create a work
     * @param level that will use to create work
     * @return work created from level
     */
    public Work create(int level);
    public String getName();

}
