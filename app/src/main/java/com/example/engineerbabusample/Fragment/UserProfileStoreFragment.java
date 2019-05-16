package com.example.engineerbabusample.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
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
 */
public class UserProfileStoreFragment extends Fragment implements FragmentStructure {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.button_save)
    Button buttonSave;
    Unbinder unbinder;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_mobile)
    EditText etMobile;
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
        db = new DatabaseHelper(context);
    }

    @Override
    public void configurationFragment() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
                createNote(etName.getText().toString()
                        , etEmail.getText().toString()
                        ,etMobile.getText().toString());
            }
        });
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String note, String email, String mobile) {
        // inserting note in db and getting
        // newly inserted note id
//        boolean isInserted =  db.insertData(note, email, mobile);
//        Toast.makeText(context, "log : " + isInserted, Toast.LENGTH_SHORT).show();

    }

    public  void AddData() {
        buttonSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.insertData(etName.getText().toString(),
                                etEmail.getText().toString(),
                                etMobile.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(context,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(context,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
//        btnviewAll.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = myDb.getAllData();
//                        if(res.getCount() == 0) {
//                            // show message
//                            showMessage("Error","Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("Id :"+ res.getString(0)+"\n");
//                            buffer.append("Name :"+ res.getString(1)+"\n");
//                            buffer.append("Surname :"+ res.getString(2)+"\n");
//                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
//                        }
//
//                        // Show all data
//                        showMessage("Data",buffer.toString());
//                    }
//                }
//        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
