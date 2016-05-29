package com.sandstorm.softspec.punkarnban.Adapters;

;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sandstorm.softspec.punkarnban.Fragments.RecruitFragment;
import com.sandstorm.softspec.punkarnban.Fragments.StationeryFragment;
import com.sandstorm.softspec.punkarnban.Fragments.SkillFragment;

/**
 * Created by FTTX on 5/21/2016 AD.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;

    }

    public Fragment getItem(int position) {

        switch (position) {

            case 0 :
                Fragment shop = new StationeryFragment();
                return shop;
            case 1 :
                Fragment recuit = new RecruitFragment();
                return recuit;
            case 2 :
                Fragment skill = new SkillFragment();
                return skill;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
