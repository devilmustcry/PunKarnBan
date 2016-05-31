package com.sandstorm.softspec.punkarnban.Models.Skill;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Zen on 5/28/16.
 */
public class SkillManager {

    private Skill skill;
    private ArrayList<Skill> skillList;
    private final Skill math = new Mathematics();
    private final Skill chem = new Chemistry();
    private final Skill eng = new English();
    private final Skill phy = new Physics();
    private final Skill bio = new Biology();

    public SkillManager(){
        skillList = new ArrayList<Skill>();
        addSkillToList();
    }



    public void setSkillStrategy(String homework){
        skill = findSkill(homework);
    }

    public int getWptFromSkill(){

        return skill.getApplySkillWPT();
    }

    public void levelUp(String skillName){
        findSkill(skillName).levelUp();

    }

    public void addSkillToList(){

        skillList.clear();
        skillList.add(math);
        skillList.add(phy);
        skillList.add(chem);
        skillList.add(eng);
        skillList.add(bio);
    }

    public ArrayList<Skill> getSkillList(){
        return skillList;
    }

    public int [] getSkillsLevels() {
        return new int[]{math.getLevel(),phy.getLevel(),chem.getLevel(),eng.getLevel(),bio.getLevel()};
    }

    public void setSkillsLevels(int [] levels) {
        math.setLevel(levels[0]);
        phy.setLevel(levels[1]);
        chem.setLevel(levels[2]);
        eng.setLevel(levels[3]);
        bio.setLevel(levels[4]);
    }

    public Skill findSkill(String homework) {
        if(homework.equalsIgnoreCase("Mathematics")){
            return math;
        }
        else if(homework.equalsIgnoreCase("Biology")){
            return bio;
        }
        else if(homework.equalsIgnoreCase("English")){
            return eng;
        }
        else if(homework.equalsIgnoreCase("Chemistry")){
            return chem;
        }
        else if(homework.equalsIgnoreCase("Physics")){
            return phy;
        }
        return null;
    }
}
