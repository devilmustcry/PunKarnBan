package com.sandstorm.softspec.punkarnban.Models.Player;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Items.Item;
import com.sandstorm.softspec.punkarnban.Models.Skill.Skill;
import com.sandstorm.softspec.punkarnban.Models.Skill.SkillManager;
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
    private int knowledge;
    private SkillManager skillManager;



    public Player(String name) {
        this.name = name;

        items = new ArrayList<Item>();
        stationery = new Stationery();
        skillManager = new SkillManager();
        setWpt(stationery.getWPT());
        knowledge = 0;



    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void usedItem(int index) {
        this.items.get(index).effect();

        this.items.remove(index);
    }



    public void levelUpStationary() {
        knowledge-=stationery.getPrice();
        stationery.levelUp();
        setWpt(stationery.getWPT());

    }

    public int tap() {
        return wpt+skillManager.getWptFromSkill();
    }



    public void setWpt(int wpt) {

        this.wpt = wpt;

    }

    public void gainKnowledge(int knowledge) {
        this.knowledge+=knowledge;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public Stationery getStationery() {
        return stationery;
    }

    public void decreaseKnowledge(int knowledge) {
        this.knowledge-=knowledge;
    }

    public SkillManager getSkillManager(){
        return this.skillManager;
    }

    public void setSkill(String name) {
        skillManager.setSkillStrategy(name);
    }





}
