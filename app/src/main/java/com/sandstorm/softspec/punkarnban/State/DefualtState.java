package com.sandstorm.softspec.punkarnban.State;

import android.widget.ImageView;

import com.sandstorm.softspec.punkarnban.Activities.MainActivity;
import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Weapon.Stationery;
import com.sandstorm.softspec.punkarnban.R;

/**
 * Created by FTTX on 5/30/2016 AD.
 */
public class DefualtState implements TapState {

    ImageView image;
    MainActivity activity;

    public DefualtState(MainActivity activity,ImageView image) {
        this.image = image;
        this.activity = activity;
    }

    @Override
    public void tap() {
        Stationery stationery = Game.getInstance().getPlayer().getStationery();
        int resource = getImage(stationery.getName(stationery.getLevel()));
        image.setImageResource(resource);
        activity.setState(new TappingState(activity,image));
    }

    private int getImage(String name) {

        switch (name) {
            case "Rusty pencil" :
                return R.drawable.student1_1;
            case "Standard pencil" :
                return R.drawable.student2_1;
            case "Silver pencil" :
                return R.drawable.student3_1;
            case "Gold pencil" :
                return R.drawable.student4_1;
            case "Diamond pencil" :
                return R.drawable.student5_1;
            case "Embedded Computer pencil" :
                return R.drawable.student6_1;
            case "Heavenly pencil" :
                return R.drawable.student7_1;
            case "Mjolnir's pencil" :
                return R.drawable.student8_1;
            case "The greatest ever made pencil" :
                return R.drawable.student9_1;
            case "Pen" :
                return R.drawable.student1_1;

        }
        return R.drawable.student1_1;
    }
}
