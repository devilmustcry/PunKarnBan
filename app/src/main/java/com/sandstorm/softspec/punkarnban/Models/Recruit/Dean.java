package com.sandstorm.softspec.punkarnban.Models.Recruit;

/**
 * Created by FTTX on 5/30/2016 AD.
 */
public class Dean extends Recruit {


    public Dean() { super ("Dean",7); }

    @Override
    public void levelUp() {
        super.setLevel(getLevel() + 1);
        super.setPrice((int) Math.ceil(10 * Math.pow(1.05, getLevel())));
        super.setDPS((int)Math.ceil(getDPS() * Math.pow(1.02 , getLevel())));
    }
}
