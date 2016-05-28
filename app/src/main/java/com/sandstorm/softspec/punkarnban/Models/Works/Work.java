package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public abstract class Work {

    private int hp;

    private int point;

    private int gold;

    private int knowledge;

    private String name;

    public Work(String name,int hp, int point, int gold, int knowledge) {
        this.hp = hp;
        this.point = point;
        this.gold = gold;
        this.knowledge = knowledge;
        this.name = name;
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
