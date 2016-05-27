package com.sandstorm.softspec.punkarnban.Models.Game;

import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Recruit.FriendFactory;
import com.sandstorm.softspec.punkarnban.Models.Recruit.RecruitFactory;
import com.sandstorm.softspec.punkarnban.Models.Recruit.TeacherFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Homework;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;

import java.util.Observable;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Game extends Observable {

    private Player player;
    private Work work;
    private Integer process;
    private int level;

    public Game() {
        player = new Player("Name");
        level = getLevelFromSaved();
        work = initWork();
        process = 0;
    }

    public int getLevelFromSaved() {
        return 1;
    }

    public Work initWork() {
        if(work == null) {
            work = new Homework("Test",level*20 , 100 , 10 , 1);
        }
        else{
            work.setGold(work.getGold());
            work.setHp(level * 20);
            work.setPoint(work.getPoint());
            work.setKnowledge(work.getKnowledge());
        }
        return work;
    }

    public void tap() {
        int tap = player.tap();
        process+=tap;
        if(process >= work.getHp()) {
            levelUp();
            Work newWork = initWork();
            setChanged();
            notifyObservers(newWork);
            process = 0;
        }
        else {
            setChanged();
            notifyObservers(process);
        }
    }

    private void levelUp() {
        level++;
    }

    public RecruitFactory getFactory(String name){
        if(name.equalsIgnoreCase("teacher")){
            return new TeacherFactory();
        } else if(name.equalsIgnoreCase("friend")){
            return new FriendFactory();
        }
        return null;
    }

}
