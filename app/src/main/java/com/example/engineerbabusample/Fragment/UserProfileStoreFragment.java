package com.example.engineerbabusample.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.engineerbabusample.DataBaseHelper.DatabaseHelper;
import com.example.engineerbabusample.Interface.FragmentStructure;
import com.example.engineerbabusample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Created by arvind mewada
 * user data input and
 * save local data base in sql lite helper
 */
public class UserProfileStoreFragment extends Fragment implements FragmentStructure {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.username_text_input_layout)
    TextInputLayout usernameTextInputLayout;
    @BindView(R.id.etUser_email)
    EditText etUserEmail;
    @BindView(R.id.user_email_text_input_layout)
    TextInputLayout userEmailTextInputLayout;
    @BindView(R.id.etUser_mobile)
    EditText etUserMobile;
    @BindView(R.id.user_mobile_text_input_layout)
    TextInputLayout userMobileTextInputLayout;
    @BindView(R.id.butom_save_user_date)
    Button butomSaveUserDate;
    Unbinder unbinder;

    private Context context;
    private DatabaseHelper db;

    public UserProfileStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        getBundleFragment();
        View view = inflater.inflate(R.layout.fragment_user_profile_store, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializationFragment();
        configurationFragment();
        populationFragment();
        onClickFragment();
        return view;
    }

    @Override
    public void initializationFragment() {
        // data base helper initialization
        db = new DatabaseHelper(context);
    }

    @Override
    public void configurationFragment() {

    }

    @Override
    public void populationFragment() {

    }

    @Override
    public void onClickFragment() {
        addData();
    }

    @Override
    public void getBundleFragment() {

    }

    /**
     * insert user data in data base
     * button save onclick
     * */
    public void addData() {
        butomSaveUserDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validation()) {
                            boolean isInserted = db.insertData(etUsername.getText().toString(),
                                    etUserEmail.getText().toString(),
                                    etUserMobile.getText().toString());
                            if (isInserted) {
                                etUsername.setText("");
                                etUserEmail.setText("");
                                etUserMobile.setText("");
                                Toast.makeText(context, "Data Inserted", Toast.LENGTH_LONG).show();
                            }
                    }else
                        Toast.makeText(context, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /**
     * validation check
     * @Param name
     * @Param email
     * @Param mobile
     * */
    private boolean validation() {
        if (etUsername.getText().length() == 0 ) {
            etUsername.setError("Enter Name !!!");
            return false;
        }else if(etUserEmail.getText().length() == 0 ){
            etUserEmail.setError("Enter Email !!!");
            return false;
        } else if(etUserMobile.getText().length() == 0
                && etUserMobile.getText().length() != 10) {
            etUserMobile.setError("Enter Mobile !!!");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
