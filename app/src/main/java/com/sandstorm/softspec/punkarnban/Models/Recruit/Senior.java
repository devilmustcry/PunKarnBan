package com.sandstorm.softspec.punkarnban.Models.Recruit;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Created by FTTX on 5/30/2016 AD.
 */
public class Senior extends Recruit {



    public Senior() { super("Senior", 30,1000); }

    @Override
    public void levelUp() {
        plusLevel();
        setPrice(FormulaCalculator.getInstance().calculate(1000, 1.05, getLevel()));
        setDPS(FormulaCalculator.getInstance().calculate(30, 1.02, getLevel()));
    }
}
