package com.example.hp.offermagnet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class One extends Fragment implements ItemClickListener{
    public static FloatingActionButton fab;

    public One() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_offer, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recyclerviewOffer);
        ArrayList<DataItem> dataItems=new ArrayList<DataItem>() ;
                dataItems.add( new DataItem("Pizza", "buy one and git one free", R.drawable.ellipse));
        dataItems.add( new DataItem("shose", "discound 10%", R.drawable.ellipse));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(dataItems,getContext());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    fab = (FloatingActionButton) view.findViewById(R.id.fab_Offer);
        final boolean[] check = {false};
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 check[0] =!check[0];
                 if (check[0] ==true) {
                     Fragment productDetailFragment = new SubmitOfferFragment();
                     FragmentManager ft = getFragmentManager();
                     ft.beginTransaction().replace(R.id.frame_offer, productDetailFragment).commit();
                     fab.setVisibility(View.GONE);
                 }


            }
        });


        return view;

    }


    @Override
    public void onClick(View view, int position) {
        Fragment productDetailFragment = new DetailsFragment();
        FragmentManager ft = getFragmentManager();
        ft.beginTransaction().replace(R.id.frame_offer, productDetailFragment).commit();
        fab.setVisibility(View.GONE);


    }
}
