package com.sandstorm.softspec.punkarnban.Models.Game;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Friend;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.Models.Works.HomeworkFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Project;
import com.sandstorm.softspec.punkarnban.Models.Works.ProjectFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;
import com.sandstorm.softspec.punkarnban.Models.Works.WorkFactory;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller Class
 * Created by FTTX on 5/7/2016 AD.
 */
public class Game extends Observable {

    /**
     * Create factory
     */
    final private HomeworkFactory homeworkFactory = new HomeworkFactory();
    final private ProjectFactory projectFactory = new ProjectFactory();


    /**
     * Instance
     */
    private static Game instance;

    /**
     * Player that play this game
     */
    private Player player;
    /**
     * List of recruit in this game
     */
    private List<Recruit> recruits;
    /**
     * Work Factory
     */
    private WorkFactory workFactory;
    /**
     * Timer for recruit Task
     */
    private Timer recruitTimer;
    /**
     * Timer for project countdown.
     */
    private Timer projectTimer;

    /**
     * Work that present in the game
     */
    private Work work;
    /**
     * process of the work
     */
    private Integer process;
    /**
     * Level of the game
     */
    private int level;

    /**
     * Constructor
     */
    private Game() {
        player = new Player("Name");
        level = getLevelFromSaved();
        setDefaultFactory();
        work = initWork();
        process = 0;
        recruits = new ArrayList<Recruit>();

    }

    /**
     * set Default Factory (homework)
     */
    private void setDefaultFactory() {
        workFactory = homeworkFactory;

    }

    /**
     * Start timer for recruit
     */
    public void startRecruitTimer() {

        recruitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("Timer", "Work");
                for (Recruit recruit : recruits) {
                    process += recruit.getDPS();
                    instance.checkLevelUp();
                }

            }
        }, 0, 1000);


    }

    /**
     * Project limited timer
     * @param project : time limited for project
     */
    private void startProjectTimer(Project project) {

        projectTimer.schedule(new projectTimerTask(project), 0, 1000);

    }

    private void stopProjectTimer(){
        projectTimer.cancel();
//        projectTimer.purge();
        projectTimer = new Timer();
        Log.e("stop", "call");
    }



    /**
     * Get level from last saved
     * @return last level from saved
     */
    public int getLevelFromSaved() {
        return 999;
    }

    /**
     * Initialize new work
     * @return work
     */
    public Work initWork() {
        if(level%10 == 0)
            workFactory = getWorkFactory("project");
        else
            workFactory = getWorkFactory("homework");
        return workFactory.create(level);
    }

    /**
     * Tap
     * @return tap damage from player
     */
    public int tap() {
        int tap = player.tap();
        process+=tap;
        checkLevelUp();
        return tap;
    }

    private void checkLevelUp(){
        if(isDone()) {
            if(work.getClass() == Project.class){
                stopProjectTimer();
            }
            levelUp();
        }
        else {
            setChanged();
            notifyObservers(process);
        }
    }

    /**
     * Hire a recruit
     * @param name of the factory
     */
    public void hire(String name) {
        recruits.add(new Friend());
        if(recruitTimer == null) {
            recruitTimer = new Timer();
            startRecruitTimer();
        }

    }

    /**
     * Level up the stage
     */
    private void levelUp() {

        player.gainKnowledge(work.getKnowledge());

        level++;
        work = initWork();

        process = 0;
        if(workFactory.getName().equalsIgnoreCase("project")) {
            Project project = (Project) work;
            if(projectTimer == null)
                projectTimer = new Timer();
            startProjectTimer(project);
        }

        setChanged();
        notifyObservers(work);

        setChanged();
        notifyObservers(player);

    }



    /**
     * Get work factory
     * @param name of the factory
     * @return work factory
     */
    public WorkFactory getWorkFactory(String name) {
        if(name.equalsIgnoreCase("homework"))
            return homeworkFactory;
        else if(name.equalsIgnoreCase("project"))
            return projectFactory;
        return null;
    }

    /**
     * getInstance
     * @return game instance
     */
    public static Game getInstance() {
        if(instance == null)
            instance = new Game();
        return instance;
    }

    /**
     * check whether work is done or not
     * @return
     */
    private boolean isDone() {
        Log.i("Process",process+"");
        Log.i("HP",work.getHp()+"");
        if(process >= work.getHp()) {
            return true;
        }
        return false;
    }

    private class projectTimerTask extends TimerTask {

        private Project work;

        private projectTimerTask(Project work) {
            this.work = work;
        }

        @Override
        public void run() {
            work.setTime(work.getTime() - 1);
            setChanged();
            notifyObservers(work);
            if(work.getTime() == 0) {
                this.cancel();
                levelDown();
            }


        }
    }

    private void levelDown() {
        level-=10;
        levelUp();

    }

    public int getPresentLevel() {
        return level;
    }

    public Work getWork() {
        return work;
    }

    public Player getPlayer() {
        return player;
    }

    public void levelUpStationary() {
        if(player.getKnowledge()>=player.getStationery().getPrice())
            player.levelUpStationary();
        setChanged();
        notifyObservers(player);
    }
}
