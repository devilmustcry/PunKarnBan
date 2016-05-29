package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Super class for work
 * Created by FTTX on 5/7/2016 AD.
 */
public abstract class Work {

    /**
     * Work HP
     */
    private int hp;


    /**
     * Point gain from work
     */

    /**
     * Knowledge gain from work
     */
    private int knowledge;

    /**
     * Work name
     */
    private String name;

    /**
     * Constructor....
     * @param name
     * @param hp
     * @param knowledge
     */
    public Work(String name,int hp, int knowledge) {
        this.hp = hp;
        this.knowledge = knowledge;
        this.name = name;
    }

    /**
     *
     * @return work max hp
     */
    public int getHp() {
        return hp;
    }



    /**
     *
     * @return knowledge gained
     */
    public int getKnowledge() {
        return knowledge;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }


    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    /**
     *
     * @return work's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name well...
     */
    public void setName(String name) {
        this.name = name;
    }


}
