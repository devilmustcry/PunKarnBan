package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Homework extends Work  {

    public Homework(String name,int hp, int point, int gold, int knowledge) {
        super(name,hp, point, gold, knowledge);
    }


    public int getPointReward() {
        return super.getPoint();
    }


    public int getKnowledgeReward() {
        return super.getKnowledge();
    }


    public int getGoldReward() {
        return super.getGold();
    }
}
