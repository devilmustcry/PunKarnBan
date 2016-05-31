package com.sandstorm.softspec.punkarnban.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Adapters.RecruitAdapter;
import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Nerd;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Teacher;
import com.sandstorm.softspec.punkarnban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTTX on 5/21/2016 AD.
 */
public class RecruitFragment extends Fragment {

    private ListView recruitListView;

    private List<Recruit> recruits;

    private RecruitAdapter recruitAdapter;


    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.recruit_layout, container, false);
        setListView();
        return v;
    }

    private void setListView() {
        recruitListView = (ListView) v.findViewById(R.id.recruit_listview);
        recruits = new ArrayList<Recruit>();
        recruitAdapter = new RecruitAdapter(v.getContext(),R.layout.recruit_cell,recruits);
        recruitListView.setAdapter(recruitAdapter);
        setList();

    }

    private void setList() {
        Log.i("Setting","setList");
        recruits.clear();
        for(Recruit recruit : Game.getInstance().getRecruits()) {
            recruits.add(recruit);
        }
        recruitAdapter.notifyDataSetChanged();

    }



}
