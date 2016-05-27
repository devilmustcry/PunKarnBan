package com.sandstorm.softspec.punkarnban.Models.Recruit;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class TeacherFactory implements RecruitFactory {
    @Override
    public Recruit create() {
        return new Teacher();
    }
}
