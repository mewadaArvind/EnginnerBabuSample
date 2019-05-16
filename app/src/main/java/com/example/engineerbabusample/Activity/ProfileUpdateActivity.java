package com.example.engineerbabusample.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DBUser.ViewModel.ViewModelUser;
import com.example.engineerbabusample.Interface.ActivityStructure;
import com.example.engineerbabusample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileUpdateActivity extends AppCompatActivity implements ActivityStructure {

    private Context context = this;
    private ViewModelUser viewModelUser;

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.username_text_input_layout)
    TextInputLayout usernameTextInputLayout;
    @BindView(R.id.et_email_rname)
    EditText etEmailRname;
    @BindView(R.id.email_text_input_layout)
    TextInputLayout emailTextInputLayout;
    @BindView(R.id.et_mobile_name)
    EditText etMobileName;
    @BindView(R.id.mobile_text_input_layout)
    TextInputLayout mobileTextInputLayout;
    @BindView(R.id.save_button)
    Button saveButton;

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
        backAfroAndTitleSet();
    }

    private void backAfroAndTitleSet() {
        setTitle("User Profile");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public void configurationActivity() {

    }

    @Override
    public void onclickListenerActivity() {

    }

    @Override
    public void populationActivity() {
        saveButtonOnClick();
    }

    /**
     * user profile saved
     * name
    * */
    private void saveButtonOnClick() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(validation()) {
                    String s = etUsername.getText().toString();
                    UserDataModel word = new UserDataModel(s);
                    viewModelUser.insert(word);
                    etUsername.setText("");
                    etMobileName.setText("");
                    etEmailRname.setText("");
                    finish();
                }else {
                    Toast.makeText(context, "some thing went wrong !!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * validation user profile
     * */
    private boolean validation() {
        if(etUsername.getText().length()==0){
            etUsername.setError("Enter Name");
            return false;
        }else if(etMobileName.getText().length() == 0 && etMobileName.getText().length() != 10){
            etMobileName.setError("Enter valid mobile");
            return false;
        }else if(etEmailRname.getText().length() == 0){
            etEmailRname.setError("enter email");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void getIntentValueActivity() {
    }

}
