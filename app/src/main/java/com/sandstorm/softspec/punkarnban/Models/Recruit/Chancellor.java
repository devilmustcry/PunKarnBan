package com.sandstorm.softspec.punkarnban.Models.Recruit;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Created by FTTX on 5/30/2016 AD.
 */
public class Chancellor extends Recruit {

    public Chancellor() { super("Chancellor",42000,3000000);}

    @Override
    public void levelUp() {

        plusLevel();
        setAttribute();
    }

    @Override
    public void setAttribute() {
        if(getLevel() == 0) {
            setPrice(3000000);
            setDPS(42000);
        }
        else {
            setPrice(FormulaCalculator.getInstance().calculate(3000000, 1.05, getLevel()));
            setDPS(FormulaCalculator.getInstance().calculate(42000, 1.02, getLevel()));
        }

    }
}
