package com.sandstorm.softspec.punkarnban.Models.Recruit;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class FriendFactory implements RecruitFactory {
    @Override
    public Recruit create() {
        return new Friend();
    }
}
