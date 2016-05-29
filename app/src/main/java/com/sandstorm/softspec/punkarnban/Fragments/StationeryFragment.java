package com.sandstorm.softspec.punkarnban.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Weapon.Stationery;
import com.sandstorm.softspec.punkarnban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTTX on 5/21/2016 AD.
 */
public class StationeryFragment extends Fragment {

    private ImageView stationeryImage;

    private TextView stationeryName;

    private Button stationeryUpgrade;
    private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.stationery_layout, container, false);
        setContentView();
        return v;
    }

    private void setContentView() {

        Stationery stationery = Game.getInstance().getPlayer().getStationery();
        stationeryImage = (ImageView) v.findViewById(R.id.stationery_image);
        int resource = getImage(stationery.getName(stationery.getLevel()));
        stationeryImage.setImageResource(resource);

        stationeryName = (TextView) v.findViewById(R.id.stationery_name);
        stationeryName.setText(stationery.getFullName());

        stationeryUpgrade = (Button) v.findViewById(R.id.stationery_upgrade);
        stationeryUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.getInstance().getPlayer().levelUpStationary();
                setContentView();
            }
        });

    }

    private int getImage(String name) {

        switch (name) {
           case "Rusty pencil" :
                return R.drawable.pen1;
            case "Standard pencil" :
                return R.drawable.pen2;
            case "Silver pencil" :
                return R.drawable.pen3;
            case "Gold pencil" :
                return R.drawable.pen4;
            case "Diamond pencil" :
                return R.drawable.pen5;
            case "Embedded Computer pencil" :
                return R.drawable.pen6;
            case "Heavenly pencil" :
                return R.drawable.pen7;
            case "Mjolnir's pencil" :
                return R.drawable.pen8;
            case "The greatest ever made pencil" :
                return R.drawable.pen9;
            case "Pen" :
                return R.drawable.pen10;

        }

        return R.drawable.pen1;
    }


}
