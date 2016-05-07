package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Work {

    private int hp;

    private int point;

    private int gold;

    private int knowledge;


    public Work(int hp, int point, int gold, int knowledge) {
        this.hp = hp;
        this.point = point;
        this.gold = gold;
        this.knowledge = knowledge;
    }

    public int getHp() {
        return hp;
    }

    public int getPoint() {
        return point;
    }

    public int getGold() {
        return gold;
    }

    public int getKnowledge() {
        return knowledge;
    }
}
