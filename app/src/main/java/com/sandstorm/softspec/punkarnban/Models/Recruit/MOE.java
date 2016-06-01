package com.sandstorm.softspec.punkarnban.Models.Recruit;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Created by FTTX on 6/1/2016 AD.
 */
public class MOE extends Recruit {

    public MOE() {
        super("MOE",96000,10000000);
    }

    @Override
    public void levelUp() {
        plusLevel();
        setAttribute();
    }

    @Override
    public void setAttribute() {
        if(getLevel() == 0) {
            setPrice(10000000);
            setDPS(96000);
        }
        else {
            setPrice(FormulaCalculator.getInstance().calculate(10000000, 1.05, getLevel() , 1000000 * getLevel()));
            setDPS(FormulaCalculator.getInstance().calculate(96000, 1.02, getLevel() , 500000*getLevel()));
        }
    }
}
