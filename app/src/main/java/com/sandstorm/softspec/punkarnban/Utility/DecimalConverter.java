package com.sandstorm.softspec.punkarnban.Utility;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by FTTX on 5/30/2016 AD.
 */
public class DecimalConverter {

    private long number;

    public DecimalConverter(int number) {
        this.number = number;
    }

    public DecimalConverter(long number) {
        this.number = number;
    }

    public String convert() {
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        double converted = number;
        nf.setRoundingMode(RoundingMode.UNNECESSARY);

        if(number>=1000000) {
            converted = number / 1000000;
            nf.setMaximumFractionDigits(10);

            return String.format("%sM", nf.format(converted));
        }
        else if(number>=1000) {
            converted = number / 1000;
            nf.setMaximumFractionDigits(10);
            return String.format("%sK",nf.format(converted));

        }
        return String.format("%.0f",converted);

    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void setNumber(long number) {
        this.number = number;
    }

}
