package com.sandstorm.softspec.punkarnban.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.R;

import java.util.List;

/**
 * Created by FTTX on 5/27/2016 AD.
 */
public class RecruitAdapter extends ArrayAdapter<Recruit> {

    public RecruitAdapter(Context context, int resource, List<Recruit> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.recruit_cell,null);
        }

        final Recruit recruit = getItem(position);

        TextView name = (TextView) v.findViewById(R.id.recruit_name);
        name.setText(recruit.getName());

        Button hire = (Button) v.findViewById(R.id.recruit_hire);
        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.getInstance().hire(recruit.getName());
                Log.i("Hiring",recruit.getName());
            }
        });

        return v;

    }
}
