package com.sandstorm.softspec.punkarnban.Models.Game;

import android.util.Log;

import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Chancellor;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Dean;
import com.sandstorm.softspec.punkarnban.Models.Recruit.MOE;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Nerd;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Senior;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Teacher;
import com.sandstorm.softspec.punkarnban.Models.Works.HomeworkFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Project;
import com.sandstorm.softspec.punkarnban.Models.Works.ProjectFactory;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;
import com.sandstorm.softspec.punkarnban.Models.Works.WorkFactory;
import com.sandstorm.softspec.punkarnban.Utility.DecimalConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import Memento.Memento;

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
     * Total Damage From Recruit
     */
    private int recruitDamage;

    /**
     * Constructor
     */
    private Game() {
        player = new Player();
        level = 1;
        setDefaultFactory();
        work = initWork();
        process = 0;
        recruits = new ArrayList<Recruit>();
        setDefaultRecruit();

    }

    private void setDefaultRecruit() {
        recruits.add(new Nerd());
        recruits.add(new Senior());
        recruits.add(new Teacher());
        recruits.add(new Dean());
        recruits.add(new Chancellor());
        recruits.add(new MOE());
        recruitDamage = 0;

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
        recruitTimer = new Timer();

        recruitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
//                for (Recruit recruit : recruits) {
//                    if(recruit.isHire())
//                        process += recruit.getDPS();
//                    instance.checkLevelUp();
//                }
                process+= recruitDamage;
                checkLevelUp();

            }
        }, 0, 1000);


    }

    public void stopTimer() {
        if(recruitTimer!=null) {
            recruitTimer.cancel();
            recruitTimer = null;
        }

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
    }



    /**
     * Get level from last saved
     * @return last level from saved
     */
    public int getLevelFromSaved() {
        return 1;
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
        Work work = workFactory.create(level);
        player.setSkill(work.getName());
        return work;
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

    /**
     * Check whether Level is done or not
     */

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
     * Level Up a recruit
     * @param name of the recruit that will be LevelUp
     */
    public void levelUpRecruit(String name) {

        int index = findRecruit(name);

        if(player.getKnowledge()>=recruits.get(index).getPrice()) {
            if(!recruits.get(index).isHire()) {
                recruits.get(index).hire();
            }
            player.decreaseKnowledge(recruits.get(index).getPrice());
            recruits.get(index).levelUp();
            setRecruitDamage();

            setChanged();
            notifyObservers(player);

            if(recruitTimer == null) {
                startRecruitTimer();
            }


        }


    }

    private void setRecruitDamageOutput() {
        recruitDamage = 0;
        for(Recruit recruit : recruits) {
            if(recruit.getLevel()!=0)
                recruitDamage+=recruit.getDPS();
        }
    }

    private void setRecruitDamage() {

        setRecruitDamageOutput();

        setChanged();
        notifyObservers("set");


    }

    private int findRecruit(String name) {
        int i = 0;
        for(Recruit recruit : recruits) {
            if(recruit.getName().equalsIgnoreCase(name))
                return i;
            else
                i++;
        }
        return -1;

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
        if(process >= work.getHp()) {
            return true;
        }
        return false;
    }

    public int[] getRecruitsLevel() {
        int [] recruitsLevel = new int [recruits.size()];
        for(int i =0;i<recruits.size();i++) {
            recruitsLevel[i] = recruits.get(i).getLevel();
        }

//        return new int []{recruits.get(0).getLevel(),recruits.get(1).getLevel(),recruits.get(2).getLevel(),recruits.get(3).getLevel(),recruits.get(4).getLevel()} ;
        return recruitsLevel;
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

        setChanged();
        notifyObservers("set");
    }

    public List<Recruit> getRecruits() {
        return recruits;
    }

    public void levelUpSkill(String name) {
        if(player.getKnowledge() >= player.getSkillManager().findSkill(name).getPriceOfNextLevel()) {
            player.decreaseKnowledge(player.getSkillManager().findSkill(name).getPriceOfNextLevel());
            player.getSkillManager().levelUp(name);
            setChanged();
            notifyObservers(player);

            setChanged();
            notifyObservers("set");
        }
    }

    public int getRecruitDamage() {
        return recruitDamage;
    }

    public int getCurrentProcess() {return process;}

    //------------------------------------------------------------------- Save/Load Code

//    public GameMemento saveState() {
//        return new GameMemento(level,getRecruitsLevel());
//    }

    public Memento saveState() { return new GameMemento(level,getRecruitsLevel(),process);}

    public void restore(Memento memento) {
        if(memento == null)
            return;
        if(memento.getClass()!=GameMemento.class){
            return;
        }
        GameMemento m = (GameMemento) memento;

        Log.i("Restoring", "Start Restoring game");
        if(m==null)
            return;
        this.level = m.level;
        this.process = m.process;
        Log.i("Restoring", "Restore Level to : " + level);

        for(int i =0;i<recruits.size();i++){
            this.recruits.get(i).setLevel(m.recruits[i]);
        }

        setRecruitDamageOutput();

    }


    public static class GameMemento extends Memento {
        private int level;
        private int [] recruits;
        private int process;


        private GameMemento(int level,int [] recruits,int process) {
            super("game");
            this.level = level;
            this.recruits = recruits;
            this.process = process;
        }
    }


}
