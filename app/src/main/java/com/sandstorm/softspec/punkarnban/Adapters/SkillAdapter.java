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
        ImageView image = (ImageView)v.findViewById(R.id.skill_picture) ;

        TextView name = (TextView)v.findViewById(R.id.skill_name);
//        Log.i("Skill name",skill.getName());
        name.setText(skill.getFullName());

        switch (skill.getName()){
            case "Mathematics":
                image.setImageResource(R.drawable.math_sk);
                break;
            case "Chemistry" :
                image.setImageResource(R.drawable.chem_sk);
                break;
            case "English" :
                image.setImageResource(R.drawable.english_sk);
                break;
            case "Physics" :
                image.setImageResource(R.drawable.physic_sk);
                break;
            case "Biology" :
                image.setImageResource(R.drawable.bio_sk);
                break;

        }


        TextView price = (TextView) v.findViewById(R.id.skill_price);
        price.setText("Price : " + skill.getPriceOfNextLevel()+"");

        Button upgrade = (Button)v.findViewById(R.id.skill_upgrade_button);
        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.getInstance().levelUpSkill(skill.getName());
                notifyDataSetChanged();
            }
        });


        return v;
    }
}
