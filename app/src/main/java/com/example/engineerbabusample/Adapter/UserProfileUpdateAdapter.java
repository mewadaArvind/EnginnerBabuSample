package com.example.engineerbabusample.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DataModel.DataModelUser;
import com.example.engineerbabusample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileUpdateAdapter extends RecyclerView.Adapter<UserProfileUpdateAdapter.MyViewHolder> {

    private Context context;
    private List<UserDataModel> dataModelSQLS;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_email)
        TextView tvUserEmail;
        @BindView(R.id.tv_user_mobile)
        TextView tvUserMobile;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public UserProfileUpdateAdapter(Context context, List<UserDataModel> list) {
        this.dataModelSQLS = list;
        this.context = context;
    }

    @Override
    public UserProfileUpdateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user_connected, parent, false);

        return new UserProfileUpdateAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserProfileUpdateAdapter.MyViewHolder holder, int position) {
        UserDataModel note = dataModelSQLS.get(position);
        if (note != null) {
            holder.tvUserName.setText("User Name : "+note.getWord()+"");
        }
    }

    @Override
    public int getItemCount() {
        return dataModelSQLS.size();
    }

}

