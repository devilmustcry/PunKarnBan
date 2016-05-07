package com.sandstorm.softspec.punkarnban.Models.Items;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public abstract class Item {

    private String name;

    private String description;

    public Item(String name,String description) {
        this.name = name;
        this.description = description;
    }


    public abstract void effect();



}
