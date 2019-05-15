package com.example.enginnerbabusample.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enginnerbabusample.DataBaseHelper.DatabaseHelper;
import com.example.enginnerbabusample.DataModel.DataModelUser;
import com.example.enginnerbabusample.Adapter.UserAllProfileAdapter;
import com.example.enginnerbabusample.Interface.FragmentStructure;
import com.example.enginnerbabusample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListShowFragment extends Fragment implements FragmentStructure {


    @BindView(R.id.rv_user_profile_list)
    RecyclerView rvUserProfileList;
    Unbinder unbinder;
    private Context context;
    private UserAllProfileAdapter userAllProfileAdapter;
    List<DataModelUser> list = new ArrayList<>();
    private DatabaseHelper db;

    public UserListShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        getBundleFragment();
        View view = inflater.inflate(R.layout.fragment_user_list_show, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializationFragment();
        configurationFragment();
        populationFragment();
        onClickFragment();
        return view;
    }

    @Override
    public void initializationFragment() {
        db = new DatabaseHelper(context);
    }

    @Override
    public void configurationFragment() {
        userAllProfileAdapter = new UserAllProfileAdapter(context,list);
        rvUserProfileList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        rvUserProfileList.setAdapter(userAllProfileAdapter);
        notifyAdapter();
    }

    private void notifyAdapter() {
        list.addAll(db.getAllData());
        userAllProfileAdapter.notifyDataSetChanged();
    }

    @Override
    public void populationFragment() {

    }

    @Override
    public void onClickFragment() {

    }

    @Override
    public void getBundleFragment() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
