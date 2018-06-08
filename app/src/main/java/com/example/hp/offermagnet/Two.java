package com.example.hp.offermagnet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Two extends Fragment implements ItemClickListener {


    public Two() {
        // Required empty public constructor
    }
    public static FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_request, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recyclerviewRequest);
        ArrayList<DataItemRequest> dataItems=new ArrayList<DataItemRequest>();
                dataItems.add(new DataItemRequest("Pizza", "buy one and git one free", R.drawable.ellipse));
        dataItems.add(new DataItemRequest("shose", "discound 10%", R.drawable.ellipse));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerRequestAdapter adapter=new RecyclerRequestAdapter(dataItems,getContext());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fab = (FloatingActionButton) view.findViewById(R.id.fab_Request);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment productDetailFragment = new SubmitRequestFragment();
                FragmentManager ft=getFragmentManager();
                ft.beginTransaction().replace(R.id.frame_request, productDetailFragment).commit();
                fab.setVisibility(View.GONE);
            }
        });
        return view;
    }


    @Override
    public void onClick(View view, int position) {
        Fragment productDetailFragment = new RequestDetailsFragment();
        FragmentManager ft = getFragmentManager();
        ft.beginTransaction().replace(R.id.frame_request, productDetailFragment).commit();
        fab.setVisibility(View.GONE);
    }
}
