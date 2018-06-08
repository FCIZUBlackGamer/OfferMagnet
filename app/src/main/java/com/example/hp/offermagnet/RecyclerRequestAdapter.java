package com.example.hp.offermagnet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerRequestAdapter extends RecyclerView.Adapter<RecyclerRequestAdapter.ViewHolder> {

        private ArrayList<DataItemRequest> dataItem;
        Context context;
    public static ItemClickListener clickListener;

        // data is passed into the constructor
        public RecyclerRequestAdapter(ArrayList<DataItemRequest> dataItem, Context context) {

            this.dataItem = dataItem;
            this.context=context;

        }

        // inflates the row layout from xml when needed
        @Override
        public RecyclerRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_request_resource, null);

            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.txtTitle.setText(dataItem.get(position).title);
            holder.desc.setText(dataItem.get(position).desc);
            holder.usrImage.setImageResource(dataItem.get(position).imageUrl);
        }

        // total number of rows
        @Override
        public int getItemCount()
        {

            return dataItem.size();
        }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView txtTitle, desc;
            ImageView usrImage;
            Button btnDetails;

            public ViewHolder(View itemView) {
                super(itemView);

                txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
                desc = (TextView) itemView.findViewById(R.id.txtContent);
                usrImage = (ImageView) itemView.findViewById(R.id.userPhoto);
                btnDetails=(Button)itemView.findViewById(R.id.detailsRequButton) ;
                btnDetails.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
            }
        }
    }

