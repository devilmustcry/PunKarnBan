package com.sandstorm.softspec.punkarnban.Models.Game;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Recruit.FriendFactory;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.Models.Recruit.RecruitFactory;
import com.sandstorm.softspec.punkarnban.Models.Recruit.TeacherFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Homework;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Game extends Observable {

    private static Game instance;

    private Player player;
    private List<Recruit> recruits;
    private RecruitFactory factory;
    private Timer timer;

    private Work work;
    private Integer process;
    private int level;

    private Game() {
        player = new Player("Name");
        level = getLevelFromSaved();
        work = initWork();
        process = 0;
        recruits = new ArrayList<Recruit>();
        timer = new Timer();
        startTimer();

    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("Timer","Work");
                for(Recruit recruit : recruits) {
                    process+= recruit.getDPS();
                    if(isDone())
                        levelUp();
                    else {
                        setChanged();
                        notifyObservers(process);
                    }
                }
            }
        },0,1000);

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
        if(isDone()) {
            levelUp();
        }
        else {
            setChanged();
            notifyObservers(process);
        }
    }

    public void hire(String name) {
        factory = getFactory(name);
        recruits.add(factory.create());
    }



    private void levelUp() {
        level++;
        Work newWork = initWork();
        process = 0;
        setChanged();
        notifyObservers(newWork);

    }

    public RecruitFactory getFactory(String name){
        if(name.equalsIgnoreCase("jittat")){
            return new TeacherFactory();
        } else if(name.equalsIgnoreCase("frank")){
            return new FriendFactory();
        }
        return null;
    }

    public static Game getInstance() {
        if(instance == null)
            instance = new Game();
        return instance;
    }

    private boolean isDone() {
        if(process>= work.getHp()) {
            return true;
        }
        return false;
    }

}
