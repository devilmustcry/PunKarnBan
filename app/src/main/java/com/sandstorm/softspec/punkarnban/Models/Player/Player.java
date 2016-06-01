package com.sandstorm.softspec.punkarnban.Models.Player;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Skill.Skill;
import com.sandstorm.softspec.punkarnban.Models.Skill.SkillManager;
import com.sandstorm.softspec.punkarnban.Models.Weapon.Stationery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Memento.Memento;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Player {

//    private static Player instance;

    private int wpt;
    private Stationery stationery;
    private int knowledge;
    private SkillManager skillManager;



    public Player() {
        stationery = new Stationery();
        skillManager = new SkillManager();
        setWpt(stationery.getWPT());
        knowledge = 0;

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


    //-----------------------------------------------------------------Save/Load Code

    public Memento saveState() {
        return new PlayerMemento(stationery.getLevel(),skillManager.getSkillsLevels(),knowledge);
    }

    public void restore(Memento memento) {
        if(memento==null)
            return;
        if(memento.getClass()!=PlayerMemento.class)
            return;
        PlayerMemento m = (PlayerMemento) memento;
        this.knowledge = m.knowledge;
        stationery.setLevel(m.stationeryLevel);
        skillManager.setSkillsLevels(m.skillLevel);

    }

    public static class PlayerMemento extends Memento {

        private int stationeryLevel;

        private int [] skillLevel;

        private int knowledge;

        private PlayerMemento(int stationeryLevel,int [] skillLevel, int knowledge) {
            super("player");
            this.stationeryLevel = stationeryLevel;
            this.skillLevel = skillLevel;
            this.knowledge = knowledge;

        }

    }



}
