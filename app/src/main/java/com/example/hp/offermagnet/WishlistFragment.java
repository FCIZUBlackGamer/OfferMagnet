package com.example.hp.offermagnet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WishlistFragment extends Fragment {


    public WishlistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_wishlist, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recyclerviewOffer);
        ArrayList<DataItem> dataItems=new ArrayList<DataItem>();
//        dataItems.add(new DataItem("Pizza", "buy one and git one free", R.drawable.ellipse));
//        dataItems.add(new DataItem("shose", "discound 10%", R.drawable.ellipse));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(dataItems,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

}
