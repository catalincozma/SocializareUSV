package com.example.cozma.socializareusv.DataBase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cozma.socializareusv.CustomTextViews.TextViewOpenSansBold;
import com.example.cozma.socializareusv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cozma on 24.11.2016.
 */

public class MyAdapterDatabase extends RecyclerView.Adapter<MyAdapterDatabase.ViewHolder> {
    protected List<Client> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextViewOpenSansBold firstName, lastname, phone, university;

        public ViewHolder(View itemView) {
            super(itemView);
            firstName = (TextViewOpenSansBold) itemView.findViewById(R.id.element_student_name);
            lastname = (TextViewOpenSansBold) itemView.findViewById(R.id.element_student_lastName);
            phone = (TextViewOpenSansBold) itemView.findViewById(R.id.element_student_phone);
            university = (TextViewOpenSansBold) itemView.findViewById(R.id.element_student_university);
        }
    }

    public MyAdapterDatabase(List<Client> clients) {
        mDataSet = clients;
    }

    @Override
    public MyAdapterDatabase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_student, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /**get element from your dataset at this position*/
        /**replace the contents of the view with that element*/

        holder.firstName.setText(mDataSet.get(position).get_firstname());
        holder.lastname.setText(mDataSet.get(position).get_lastName());
        holder.phone.setText(mDataSet.get(position).get_phone());
        holder.university.setText(mDataSet.get(position).get_university());
    }

    @Override
    public int getItemCount() {
        int listCount= mDataSet.size();
        return listCount;
    }
    public void setFilter(List<Client> studentModels){
        mDataSet = new ArrayList<>();
        mDataSet.addAll(studentModels);
        notifyDataSetChanged();
    }
}


