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
        setAttribute();
    }

    @Override
    public void setAttribute() {
        if(getLevel() == 0) {
            setPrice(650000);
            setDPS(8000);
        }
        else {
            setPrice(FormulaCalculator.getInstance().calculate(650000, 1.05, getLevel() ,10000*getLevel() ));
            setDPS(FormulaCalculator.getInstance().calculate(8000, 1.02, getLevel() , 50000*getLevel() ));
        }

    }
}
