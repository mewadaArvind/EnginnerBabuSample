package com.example.engineerbabusample.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DBUser.ViewModel.ViewModelUser;
import com.example.engineerbabusample.Interface.ActivityStructure;
import com.example.engineerbabusample.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileUpdateActivity extends AppCompatActivity implements ActivityStructure {

    @BindView(R.id.sms_code_edt)
    EditText smsCodeEdt;
    @BindView(R.id.auth_text_input_layout)
    TextInputLayout authTextInputLayout;
    @BindView(R.id.start_auth_button)
    Button startAuthButton;
    @BindView(R.id.verify_auth_button)
    Button verifyAuthButton;
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

    private Context context = this;
    private ViewModelUser viewModelUser;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private String verificationid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
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
        //intialized firebase auth
        mAuth = FirebaseAuth.getInstance();
        backAfroAndTitleSet();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationid = s;
            }
        };
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Tag", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();


                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("Tag", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                smsCodeEdt.setError("Invalid code.");
                            }

                        }
                    }
                });
    }

    private boolean validatePhoneNumberAndCode() {
        String phoneNumber = etMobileName.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            etMobileName.setError("Invalid phone number.");
            return false;
        }
        return true;
    }

    private boolean validateSMSCode() {
        String code = smsCodeEdt.getText().toString();
        if (TextUtils.isEmpty(code)) {
            smsCodeEdt.setError("Enter verification Code.");
            return false;
        }
        return true;
    }

    private void backAfroAndTitleSet() {
        setTitle("User Profile");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @OnClick(R.id.start_auth_button)
    public void startAuthButton() {
        if (!validatePhoneNumberAndCode()) {
            return;
        }
        startPhoneNumberVerification("+91 "+etMobileName.getText().toString());
    }

    @OnClick(R.id.verify_auth_button)
    public void verifyAuthButton() {
        if (!validateSMSCode()) {
            return;
        }
        verifyPhoneNumberWithCode(verificationid, smsCodeEdt.getText().toString());
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
     */
    private void saveButtonOnClick() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (validation()) {
                    String s = etUsername.getText().toString();
                    UserDataModel word = new UserDataModel(s);
                    viewModelUser.insert(word);
                    etUsername.setText("");
                    etMobileName.setText("");
                    etEmailRname.setText("");
                    finish();
                } else {
                    Toast.makeText(context, "some thing went wrong !!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * validation user profile
     */
    private boolean validation() {
        if (etUsername.getText().length() == 0) {
            etUsername.setError("Enter Name");
            return false;
        } else if (etMobileName.getText().length() == 0 && etMobileName.getText().length() != 10) {
            etMobileName.setError("Enter valid mobile");
            return false;
        } else if (etEmailRname.getText().length() == 0) {
            etEmailRname.setError("enter email");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void getIntentValueActivity() {
    }

}
