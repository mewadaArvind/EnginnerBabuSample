package com.example.engineerbabusample.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.engineerbabusample.DataModel.DataModelUser;
import com.example.engineerbabusample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactShowAdapter extends RecyclerView.Adapter<ContactShowAdapter.MyViewHolder> {

    private Context context;
    private List<DataModelUser> dataModelSQLS;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_mobile)
        TextView tvUserMobile;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public ContactShowAdapter(Context context, List<DataModelUser> list) {
        this.dataModelSQLS = list;
        this.context = context;
    }

    @Override
    public ContactShowAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user_contact_connected, parent, false);
        return new ContactShowAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactShowAdapter.MyViewHolder holder, int position) {
        DataModelUser note = dataModelSQLS.get(position);
        if (note != null) {
            holder.tvUserMobile.setText("User Mobile : "+note.getMobile()+"");
        }
    }

    @Override
    public int getItemCount() {
        return dataModelSQLS.size();
    }

}

