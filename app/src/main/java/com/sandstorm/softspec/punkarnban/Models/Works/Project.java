package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Project extends Work implements Workable {

    private int time;


    public Project(int hp, int point, int gold, int knowledge,int time) {
        super(hp, point, gold, knowledge);
        this.time = time;
    }

    @Override
    public int getGoldReward() {
        return super.getGold();
    }

    @Override
    public int getPointReward() {
        return super.getPoint();
    }

    @Override
    public int getKnowledgeReward() {
        return super.getKnowledge();
    }

    public int getTime() {
        return this.time;
    }
}
