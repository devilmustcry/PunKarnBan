package com.sandstorm.softspec.punkarnban.Models.Game;

import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;

/**
 * Created by FTTX on 5/7/2016 AD.
 */
public class Game {

    private Player player;
    private Work work;
    private int level;


    public Game() {
        player = new Player("Name");
        level = getLevelFromSaved();
        work = initWork(level);

    }

    public int getLevelFromSaved() {
        
        return 0;
    }

    public Work initWork(int level) {


        return null;
    }

    public void tap() {
        player.tap();
    }









}
