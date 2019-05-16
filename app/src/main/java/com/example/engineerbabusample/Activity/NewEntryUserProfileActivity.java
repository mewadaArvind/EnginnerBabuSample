package com.example.engineerbabusample.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.engineerbabusample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewEntryUserProfileActivity extends AppCompatActivity {

    @BindView(R.id.titi_tv)
    EditText titiTv;
    @BindView(R.id.save_button)
    Button saveButton;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry_user_profile);
        ButterKnife.bind(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(titiTv.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = titiTv.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
