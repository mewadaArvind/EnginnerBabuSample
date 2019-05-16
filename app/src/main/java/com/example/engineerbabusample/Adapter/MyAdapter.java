package com.example.engineerbabusample.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.engineerbabusample.Fragment.UserListShowFragment;
import com.example.engineerbabusample.Fragment.UserProfileStoreFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    public MyAdapter(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UserProfileStoreFragment();
            case 1:
                return new UserListShowFragment();
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}

