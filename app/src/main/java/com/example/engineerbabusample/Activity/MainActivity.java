package com.example.engineerbabusample.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.engineerbabusample.Interface.ActivityStructure;
import com.example.engineerbabusample.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ActivityStructure {

    private Context context = this;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.login_button)
    LoginButton loginButton;

    //  call back manager facebook
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentValueActivity();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        openUserProfileActivity();
//
//        initiationActivity();
//        configurationActivity();
//        populationActivity();
//        onclickListenerActivity();

    }

    @Override
    public void initiationActivity() {
        callBackManagerInitialization();
    }

    @Override
    public void configurationActivity() {

    }

    @Override
    public void onclickListenerActivity() {

        loginButtonOnClickListener();
    }

    @Override
    public void populationActivity() {

    }

    @Override
    public void getIntentValueActivity() {

    }


    /**
     * facebook initialization
     * callBack Manager
     */
    private void callBackManagerInitialization() {
        callbackManager = CallbackManager.Factory.create();
    }

    /**
     * login button onclick
     * registration call back
     * @Paramo success
     * @Param on cancel
     * @Paramon error
     */
    public void loginButtonOnClick() {
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                tvStatus.setText(""+loginResult.getAccessToken());
                Log.i("Success result : ", "" + loginResult);

            }

            @Override
            public void onCancel() {
                // App code
                Log.i("Cancel result : ", "Cancel" );
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                tvStatus.setText(""+exception.getMessage());
                Log.e("Success result : ", "Error" );
            }
        });
    }

    private void openUserProfileActivity() {
        Intent userProfile = new Intent(context, UserProfileMainActivity.class);
        startActivity(userProfile);
    }


    /**
     * face book login
     * button on click listener
     */
    private void loginButtonOnClickListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonOnClick();
            }
        });
    }



    /**
     * face book login
     * success result
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
