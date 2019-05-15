package com.example.enginnerbabusample.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.enginnerbabusample.Adapter.ContactShowAdapter;

import com.example.enginnerbabusample.DataBaseHelper.DatabaseHelper;
import com.example.enginnerbabusample.DataModel.DataModelUser;
import com.example.enginnerbabusample.Interface.ActivityStructure;
import com.example.enginnerbabusample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserContactNumberListActivity extends AppCompatActivity implements ActivityStructure {

    Context context = this;
    ContactShowAdapter contactShowAdapter;
    List<DataModelUser> list = new ArrayList<>();
    @BindView(R.id.rv_contact_list)
    RecyclerView rvContactList;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contact_number_list);
        ButterKnife.bind(this);
        initiationActivity();
        configurationActivity();
    }

    @Override
    public void initiationActivity() {
        db = new DatabaseHelper(context);
    }

    @Override
    public void configurationActivity() {
        contactShowAdapter = new ContactShowAdapter(context,list);
        rvContactList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        rvContactList.setAdapter(contactShowAdapter);
        notifyAdapter();
    }

    private void notifyAdapter() {
        list.addAll(db.getAllData());
        contactShowAdapter.notifyDataSetChanged();
    }

    @Override
    public void onclickListenerActivity() {

    }

    @Override
    public void populationActivity() {

    }

    @Override
    public void getIntentValueActivity() {

    }
}
