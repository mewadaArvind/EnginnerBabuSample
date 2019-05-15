package com.example.enginnerbabusample.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.enginnerbabusample.Fragment.UserListShowFragment;
import com.example.enginnerbabusample.Fragment.UserProfileStoreFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UserProfileStoreFragment userProfileStoreFragment = new UserProfileStoreFragment();
                return userProfileStoreFragment;
            case 1:
                UserListShowFragment userListShowFragment = new UserListShowFragment();
                return userListShowFragment;
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

