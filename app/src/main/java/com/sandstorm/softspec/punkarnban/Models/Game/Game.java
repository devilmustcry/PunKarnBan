package com.sandstorm.softspec.punkarnban.Models.Game;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Recruit.FriendFactory;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.Models.Recruit.RecruitFactory;
import com.sandstorm.softspec.punkarnban.Models.Recruit.TeacherFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Homework;
import com.sandstorm.softspec.punkarnban.Models.Works.HomeworkFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Project;
import com.sandstorm.softspec.punkarnban.Models.Works.ProjectFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;
import com.sandstorm.softspec.punkarnban.Models.Works.WorkFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Game extends Observable {

    final private HomeworkFactory homeworkFactory = new HomeworkFactory();
    final private ProjectFactory projectFactory = new ProjectFactory();


    private static Game instance;

    private Player player;
    private List<Recruit> recruits;
    private RecruitFactory recruitFactory;
    private WorkFactory workFactory;
    private Timer timer;

    private Work work;
    private Integer process;
    private int level;

    private Game() {
        player = new Player("Name");
        level = getLevelFromSaved();
        setDefaultFactory();
        work = initWork();
        process = 0;
        recruits = new ArrayList<Recruit>();

    }

    private void setDefaultFactory() {
        workFactory = homeworkFactory;

    }

    public void startTimer() {

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

        return workFactory.create(level);
    }

    public int tap() {
        int tap = player.tap();
        process+=tap;
        if(isDone()) {
            levelUp();
        }
        else {
            setChanged();
            notifyObservers(process);
        }
        return tap;
    }

    public void hire(String name) {
        recruitFactory = getFactory(name);
        recruits.add(recruitFactory.create());
        if(timer == null) {
            timer = new Timer();
            startTimer();
        }

    }



    private void levelUp() {
        level++;
        work = initWork();
        process = 0;
        setChanged();
        notifyObservers(work);

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
        Log.i("Process",process+"");
        Log.i("HP",work.getHp()+"");
        if(process>= work.getHp()) {
            return true;
        }
        return false;
    }

}
