package com.example.enginnerbabusample.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.enginnerbabusample.Adapter.MyAdapter;
import com.example.enginnerbabusample.Interface.ActivityStructure;
import com.example.enginnerbabusample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileMainActivity extends AppCompatActivity implements ActivityStructure {


    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;

    private Context context = this;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentValueActivity();
        setContentView(R.layout.activity_user_profile_main);
        ButterKnife.bind(this);
        initiationActivity();
        configurationActivity();
        populationActivity();
        onclickListenerActivity();
    }

    @Override
    public void initiationActivity() {
        initializationTabLayout();
    }


    @Override
    public void configurationActivity() {
        adapterConfiguration();
        actionBarNavigationBar();
    }

    private void actionBarNavigationBar() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, activityMain, R.string.Open, R.string.Close);
        activityMain.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onclickListenerActivity() {
        tabLayoutOnClickListener();
    }

    @Override
    public void populationActivity() {
        navigationDrawer();
    }


    private void navigationDrawer() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.menu_update_profile:

                        break;
                    case R.id.menu_about_us:

                        break;
                    case R.id.menu_contact_list:
                        Intent intentm =new Intent(context,UserContactNumberListActivity.class);
                        startActivity(intentm);
                        break;
                    case R.id.menu_log_out:

                        break;
                    case R.id.menu_view_location:
                        Intent intent  = new Intent(context, MyLocationActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getIntentValueActivity() {


    }

    private void initializationTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Add New User"));
        tabLayout.addTab(tabLayout.newTab().setText("Show User List"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }


    private void adapterConfiguration() {
        final MyAdapter adapter = new MyAdapter(context, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    private void tabLayoutOnClickListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
