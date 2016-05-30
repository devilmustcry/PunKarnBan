package com.sandstorm.softspec.punkarnban.Models.Works;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Project
 * Created by FTTX on 5/7/2016 AD.
 */
public class Project extends Work {

    /**
     * Time limited for project
     */
    private int time;

    /**
     * Constructor....
     * @param name
     * @param hp
     * @param knowledge
     * @param time
     */

    public Project(String name,int hp, int knowledge,int time) {
        super(name, hp, knowledge);
        this.time = time;
    }


    /**
     * get reward from super class
     * @return
     */

    public int getKnowledgeReward() {
        return super.getKnowledge();
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }



}
