package com.sandstorm.softspec.punkarnban.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Adapters.SkillAdapter;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Skill.*;
import com.sandstorm.softspec.punkarnban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTTX on 5/26/2016 AD.
 */
public class SkillFragment extends Fragment {

    private ListView skillListView;
    private List<Skill> skills;

    private SkillAdapter skillAdapter;

    private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.skill_layout, container, false);
        setContent();
        return v;
    }

    public void setContent() {
        skillListView = (ListView)v.findViewById(R.id.skill_listview);
        skills = new ArrayList<Skill>();
        skillAdapter = new SkillAdapter(v.getContext() , R.layout.skill_cell , skills);
        skillListView.setAdapter(skillAdapter);
        setList();


    }

    private void setList(){
        skills.clear();

        for(Skill skill : Game.getInstance().getPlayer().getSkillManager().getSkillList()){
            skills.add(skill);
        }

        skillAdapter.notifyDataSetChanged();
    }

}
