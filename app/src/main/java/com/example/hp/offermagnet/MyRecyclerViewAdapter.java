package com.example.hp.offermagnet;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.offermagnet.DataItem;

import java.util.ArrayList;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<DataItem> dataItem;
    Context context;
    private ItemClickListener clickListener;
    // data is passed into the constructor
    public MyRecyclerViewAdapter(ArrayList<DataItem> dataItem,Context context) {

        this.dataItem = dataItem;
        this.context=context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, null);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.txtTitle.setText(dataItem.get(position).title);
        holder.desc.setText(dataItem.get(position).desc);
        holder.usrImage.setImageResource(dataItem.get(position).imageUrl);
        //holder.btnDetails.setOnClickListener(new View.OnClickListener() {}
    }

    // total number of rows
    @Override
    public int getItemCount() {

        return dataItem.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    // stores and recycles views as they are scrolled off screen
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle, desc;
        ImageView usrImage;
        Button btnDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            desc = (TextView) itemView.findViewById(R.id.txtContent);
            usrImage = (ImageView) itemView.findViewById(R.id.userPhoto);
            btnDetails = (Button) itemView.findViewById(R.id.detailsButton);
            btnDetails.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }

    }

}