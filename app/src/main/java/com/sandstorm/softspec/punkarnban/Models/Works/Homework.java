package com.sandstorm.softspec.punkarnban.Models.Works;

/**
 * Homework class
 * Created by FTTX on 5/7/2016 AD.
 */
public class Homework extends Work  {

    /**
     * Constructor
     * @param name
     * @param hp
     * @param knowledge
     */
    public Homework(String name,int hp, int knowledge) {
        super(name,hp, knowledge);
    }


    /**
     * get reward...
     * @return
     */

    public int getKnowledgeReward() {
        return super.getKnowledge();
    }

}
