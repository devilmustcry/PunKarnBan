package com.sandstorm.softspec.punkarnban.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sandstorm.softspec.punkarnban.R;

/**
 * Created by FTTX on 5/26/2016 AD.
 */
public class SkillFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.skill_layout, container, false);
        setButton(v);
        return v;
    }

    public void setButton(View v) {

    }

}
