package com.sandstorm.softspec.punkarnban.Models.Recruit;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class Nerd extends Recruit {



    // init friend name Frank with DPS 10.
    public Nerd() {
        super("Nerd", 1,10);
    }

    @Override
    public void levelUp() {
        plusLevel();
        setPrice(FormulaCalculator.getInstance().calculate(10, 1.05, getLevel()));
        setDPS(FormulaCalculator.getInstance().calculate(1,1.02,getLevel()));
    }
}
