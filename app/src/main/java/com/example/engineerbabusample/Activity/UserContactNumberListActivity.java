package com.example.engineerbabusample.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.engineerbabusample.Adapter.ContactShowAdapter;

import com.example.engineerbabusample.DataBaseHelper.DatabaseHelper;
import com.example.engineerbabusample.DataModel.DataModelUser;
import com.example.engineerbabusample.Interface.ActivityStructure;
import com.example.engineerbabusample.R;

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
        setTitle("User Contact");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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
