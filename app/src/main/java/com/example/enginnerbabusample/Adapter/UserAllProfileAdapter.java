package com.example.enginnerbabusample.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enginnerbabusample.DataModel.DataModelUser;

import com.example.enginnerbabusample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAllProfileAdapter extends RecyclerView.Adapter<UserAllProfileAdapter.MyViewHolder> {

    private Context context;
    private List<DataModelUser> dataModelSQLS;

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


    public UserAllProfileAdapter(Context context, List<DataModelUser> list) {
        this.dataModelSQLS = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user_connected, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataModelUser note = dataModelSQLS.get(position);
        if (note != null) {
            holder.tvUserName.setText("User Name : "+note.getName()+"");
            holder.tvUserEmail.setText("User Email Id : "+note.getEmail()+"");
            holder.tvUserMobile.setText("User Mobile : "+note.getMobile()+"");
        }
    }

    @Override
    public int getItemCount() {
        return dataModelSQLS.size();
    }

}
