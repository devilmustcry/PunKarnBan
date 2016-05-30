package com.sandstorm.softspec.punkarnban.Utility;

/**
 * Created by Zen on 5/30/16.
 */
public class FormulaCalculator {

    private static FormulaCalculator calculator;

    private FormulaCalculator() {

    }

    public static FormulaCalculator getInstance() {
        if (calculator==null)
            calculator = new FormulaCalculator();
        return calculator;
    }

    public int calculate(int a1,double ratio,int level) {
        return (int)Math.ceil(a1 * Math.pow(ratio,level));
    }

}
