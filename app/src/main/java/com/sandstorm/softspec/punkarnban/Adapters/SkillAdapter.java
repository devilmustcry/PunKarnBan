package com.sandstorm.softspec.punkarnban.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.Models.Skill.Skill;
import com.sandstorm.softspec.punkarnban.R;

import java.util.List;

/**
 * Created by Zen on 5/30/16.
 */
public class SkillAdapter extends ArrayAdapter<Skill> {

    public SkillAdapter(Context context, int resource, List<Skill> objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.skill_cell,null);
        }

        final Skill skill = getItem(position);

        TextView name = (TextView)v.findViewById(R.id.skill_name);
//        Log.i("Skill name",skill.getName());
        name.setText(skill.getName());


        TextView price = (TextView) v.findViewById(R.id.skill_price);
        price.setText(skill.getPriceOfNextLevel()+"");

        Button upgrade = (Button)v.findViewById(R.id.skill_upgrade_button);
        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.getInstance().levelUpSkill(skill.getName());
                notifyDataSetInvalidated();
            }
        });


        return v;
    }
}
