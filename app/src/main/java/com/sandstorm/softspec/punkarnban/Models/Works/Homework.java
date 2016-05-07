package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Homework extends Work implements Workable {

    public Homework(int hp, int point, int gold, int knowledge) {
        super(hp, point, gold, knowledge);
    }

    @Override
    public int getPointReward() {
        return super.getPoint();
    }

    @Override
    public int getKnowledgeReward() {
        return super.getKnowledge();
    }

    @Override
    public int getGoldReward() {
        return super.getGold();
    }
}
