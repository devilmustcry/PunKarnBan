package com.sandstorm.softspec.punkarnban.Models.Items;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public abstract class Item {

    private String name;

    private String description;

    private int cost;

    public Item(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;

    }


    public abstract void effect();



}
