package com.sandstorm.softspec.punkarnban.Models.Recruit;

import com.sandstorm.softspec.punkarnban.Utility.FormulaCalculator;

/**
 * Created by FTTX on 5/30/2016 AD.
 */
public class Senior extends Recruit {



    public Senior() { super("Senior", 30,1500); }

    @Override
    public void levelUp() {
        plusLevel();
        setAttribute();
    }

    @Override
    public void setAttribute() {
        if(getLevel() == 0) {
            setPrice(1500);
            setDPS(30);
        }
        else {
            setPrice(FormulaCalculator.getInstance().calculate(1000, 1.05, getLevel() , 100 * getLevel()));
            setDPS(FormulaCalculator.getInstance().calculate(30, 1.1, getLevel() ,  50 * getLevel() ));
        }

    }
}
