package com.example.engineerbabusample.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.engineerbabusample.Adapter.UserProfileUpdateAdapter;
import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DBUser.ViewModel.ViewModelUser;
import com.example.engineerbabusample.Interface.ActivityStructure;
import com.example.engineerbabusample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileUpdateActivity extends AppCompatActivity implements ActivityStructure {

    Context context = this;
    UserProfileUpdateAdapter userProfileUpdateAdapter;
    @BindView(R.id.rv_user_profile)
    RecyclerView rvUserProfile;
    List<UserDataModel> list = new ArrayList<>();
    ViewModelUser viewModelUser;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    @BindView(R.id.add_new)
    FloatingActionButton addNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        setContentView(R.layout.activity_profile_update);
        ButterKnife.bind(this);
        initiationActivity();
        configurationActivity();
        populationActivity();
        onclickListenerActivity();
        getIntentValueActivity();
    }

    @Override
    public void initiationActivity() {
        userProfileUpdateAdapter = new UserProfileUpdateAdapter(context, list);
        rvUserProfile.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvUserProfile.setAdapter(userProfileUpdateAdapter);
        notifyUsrProfile();

    }

    private void notifyUsrProfile() {
        viewModelUser.getmAllWords().observe(this, new Observer<List<UserDataModel>>() {
            @Override
            public void onChanged(@Nullable List<UserDataModel> userDataModels) {
                list.addAll(userDataModels);
                userProfileUpdateAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void configurationActivity() {

    }

    @Override
    public void onclickListenerActivity() {

    }

    @Override
    public void populationActivity() {
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewEntryUserProfileActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    public void getIntentValueActivity() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            UserDataModel word = new UserDataModel(data.getStringExtra(NewEntryUserProfileActivity.EXTRA_REPLY));
            viewModelUser.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
