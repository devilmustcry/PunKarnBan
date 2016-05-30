package com.sandstorm.softspec.punkarnban.Models.Recruit;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Created by FTTX on 5/30/2016 AD.
 */
public class Dean extends Recruit {


    public Dean() { super ("Dean",8000,650000); }

    @Override
    public void levelUp() {
        plusLevel();
        setPrice(FormulaCalculator.getInstance().calculate(650000, 1.05, getLevel()));
        setDPS(FormulaCalculator.getInstance().calculate(8000, 1.02, getLevel()));
    }
}
