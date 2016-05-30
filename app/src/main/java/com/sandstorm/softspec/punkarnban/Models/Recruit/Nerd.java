package com.sandstorm.softspec.punkarnban.Models.Recruit;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class Nerd extends Recruit {



    // init friend name Frank with DPS 10.
    public Nerd() {
        super("Nerd", 1);
    }

    @Override
    public void levelUp() {
        super.setLevel(getLevel() + 1);
        super.setPrice((int) Math.ceil(10 * Math.pow(1.05, getLevel())));
        super.setDPS((int)Math.ceil(getDPS() * Math.pow(1.02 , getLevel())));
    }
}
