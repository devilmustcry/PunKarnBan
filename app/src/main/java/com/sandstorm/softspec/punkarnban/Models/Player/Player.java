package com.sandstorm.softspec.punkarnban.Models.Player;

import com.sandstorm.softspec.punkarnban.Models.Items.Item;
import com.sandstorm.softspec.punkarnban.Models.Weapon.Stationery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Player {

//    private static Player instance;

    private String name;
    private int wpt;
    private List<Item> items;
    private Stationery stationery;


    public Player(String name) {
        this.name = name;

        items = new ArrayList<Item>();
        stationery = Stationery.getInstance();
        setWpt(stationery.getWPT());


    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void usedItem(int index) {
        this.items.get(index).effect();

        this.items.remove(index);
    }

    

    public void levelUpStationary() {
        stationery.levelUp();
        setWpt(stationery.getWPT());
    }

    public int tap() {
        return wpt;
    }

    public void setWpt(int wpt) {

        this.wpt = wpt;

    }







}
