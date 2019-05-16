package com.example.engineerbabusample.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engineerbabusample.Adapter.MyAdapter;
import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DBUser.ViewModel.ViewModelUser;
import com.example.engineerbabusample.Interface.ActivityStructure;
import com.example.engineerbabusample.R;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileMainActivity extends AppCompatActivity implements ActivityStructure {

    private Context context = this;

    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ImageView userProfileImage;
    private TextView userName;
    private  View hView;
    private  ViewModelUser viewModelUser;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentValueActivity();
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        setContentView(R.layout.activity_user_profile_main);
        ButterKnife.bind(this);
        initiationActivity();
        configurationActivity();
        populationActivity();
        onclickListenerActivity();
    }

    @Override
    public void initiationActivity() {
        // initiliazation tab layout
        FacebookSdk.sdkInitialize(context);
        initializationTabLayout();
        headerProfileSet();
    }

    private void headerProfileSet() {
        hView =  nv.getHeaderView(0);
        userName = (TextView) hView.findViewById(R.id.tv_user_profile_name);
        userProfileImage = (ImageView) hView.findViewById(R.id.iv_user);
        populationUserProfile();
    }

    private void populationUserProfile() {
        viewModelUser.getmAllWords().observe(this, new Observer<List<UserDataModel>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(@Nullable List<UserDataModel> userDataModels) {
                if(userDataModels != null && userDataModels.size() > 0){
                    for(UserDataModel userDataModel : userDataModels)
                    userName.setText(userDataModel.getWord());
                }
            }
        });
    }

    @Override
    public void configurationActivity() {
        adapterConfiguration();
        actionBarNavigationBar();
    }


    @Override
    public void onclickListenerActivity() {
        // tab layout on click
        tabLayoutOnClickListener();
    }

    @Override
    public void populationActivity() {
       // navigation drawer population
        navigationDrawer();
    }

    /**
     * action bat navigation drawer
     * on click action perform
     * */
    private void actionBarNavigationBar() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, activityMain, R.string.Open, R.string.Close);
        activityMain.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * navigation drawer initialization
     * @Param profile update activity
     * @Param about us activity
     * @Param User Contact NUmber List
     * @Param Log Out
     * @Param my location activity
     * */
    private void navigationDrawer() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                switch(id) {
                    case R.id.menu_update_profile:
                        intent = new Intent(context,ProfileUpdateActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_about_us:
                        intent = new Intent(context,AboutUsActivity.class);
                       startActivity(intent);
                        break;
                    case R.id.menu_contact_list:
                        intent =new Intent(context,UserContactNumberListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_log_out:
                        LoginManager.getInstance().logOut();
                        Toast.makeText(context, "Log out !!!", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case R.id.menu_view_location:
                        intent  = new Intent(context, MyLocationActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    /**
     * menu item set
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getIntentValueActivity() {
    }

    /**
     * initialization
     * two fragment
     * @Param Fragment name addNewUser
     * @Param fragment name ShowUserList
     * */
    private void initializationTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Add New User"));
        tabLayout.addTab(tabLayout.newTab().setText("Show User List"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    /**
     * fragment adapter
     * configuration
     * @Param user data enter
     * @Param user List Show
     * */
    private void adapterConfiguration() {
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    /**
     * tab layout on click
     * action perform
     * */
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
