package com.sandstorm.softspec.punkarnban.Models.Skill;

/**
 * Created by Zen on 5/28/16.
 */
public class SkillManager {

    private Skill skill;
    private final Skill math = new Mathematics();
    private final Skill chem = new Chemistry();
    private final Skill eng = new English();
    private final Skill phy = new Physics();
    private final Skill bio = new Biology();

    public SkillManager(){

    }



    private void setSkillStrategy(Skill skill){
        this.skill = skill;
    }

    public int getWptFromSkill(String homework){
        if(homework.contains("Mathematics")){
            setSkillStrategy(math);
        }
        else if(homework.contains("Biology")){
            setSkillStrategy(bio);
        }
        else if(homework.contains("English")){
            setSkillStrategy(eng);
        }
        else if(homework.contains("Chemistry")){
            setSkillStrategy(chem);
        }
        else if(homework.contains("Physics")){
            setSkillStrategy(phy);
        }
        else{
            return 0;
        }

        return skill.getApplySkillWPT();
    }

    public void levelUp(String homework){
        if(homework.contains("Mathematics")){
            setSkillStrategy(math);
        }
        else if(homework.contains("Biology")){
            setSkillStrategy(bio);
        }
        else if(homework.contains("English")){
            setSkillStrategy(eng);
        }
        else if(homework.contains("Chemistry")){
            setSkillStrategy(chem);
        }
        else if(homework.contains("Physics")){
            setSkillStrategy(phy);
        }
        else{
            return;
        }

        skill.levelUp();
    }
}
