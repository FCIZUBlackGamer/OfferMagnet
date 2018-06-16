package com.example.hp.offermagnet;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class One extends Fragment implements ItemClickListener {
    public static FloatingActionButton fab;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList<DataItem> dataItems;

    public One() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_offer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewOffer);
        dataItems = new ArrayList<DataItem>();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));



        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Data ...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://192.168.1.4/GetOffers.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String s = URLEncoder.encode(response,"ISO-8859-1");
                            response = URLDecoder.decode(s,"UTF-8");
                        }catch (UnsupportedEncodingException e){
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                DataItem item = new DataItem(
                                        object.getString("id"),
                                        object.getString("title"),
                                        object.getString("description"),
                                        object.getString("profile_picture"),
                                        object.getString("date_from"),
                                        object.getString("date_to"),
                                        object.getString("price"),
                                        object.getString("likes"),
                                        object.getInt("rate"),
                                        object.getString("product_image"),
                                        object.getString("phone"),
                                        object.getString("people")
                                );
                                dataItems.add(item);
                            }
                            adapter = new MyRecyclerViewAdapter(dataItems,getActivity());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put("city","cairo");
                return stringStringHashMap;
            }

        };
        Volley.newRequestQueue(getActivity()).add(stringRequest);

        //recyclerView.setAdapter(adapter);
//        adapter.setClickListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fab = (FloatingActionButton) view.findViewById(R.id.fab_Offer);
        final boolean[] check = {false};
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check[0] = !check[0];
                if (check[0] == true) {
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
