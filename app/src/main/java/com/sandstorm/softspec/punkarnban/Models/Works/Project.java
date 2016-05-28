package com.sandstorm.softspec.punkarnban.Models.Works;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Project extends Work {

    private int time;



    public Project(String name,int hp, int point, int gold, int knowledge,int time) {
        super(name,hp, point, gold, knowledge);
        this.time = time;
    }


    public int getGoldReward() {
        return super.getGold();
    }

    public int getPointReward() {
        return super.getPoint();
    }

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
