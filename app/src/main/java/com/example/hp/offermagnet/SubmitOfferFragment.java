package com.example.hp.offermagnet;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.offermagnet.NavDrawer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SubmitOfferFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DatePickerDialog.OnDateSetListener DatePicker1,DatePicker2;



    public SubmitOfferFragment() {
        // Required empty public constructor
    }


    EditText txtTitle,txtDesc,producTitle ,txtPrice,productDesc;
    Spinner spnCategory;
    Button btnFrom,btnTo;
    TextView txtFrom,txtTo;
    Button btnOffer;
   String dateFrom,dateTo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_submit_offer, container, false);

        txtTitle=(EditText)view.findViewById(R.id.title);
        txtDesc=(EditText)view.findViewById(R.id.description);
        producTitle=(EditText)view.findViewById(R.id.productName);
        txtPrice=(EditText)view.findViewById(R.id.productPrice);
        productDesc=(EditText)view.findViewById(R.id.productDescription);
        spnCategory=(Spinner)view.findViewById(R.id.spinnerCategory);
        txtFrom =(TextView)view.findViewById(R.id.textFromDate);
        txtTo=(TextView)view.findViewById(R.id.textToDate);
        btnFrom =(Button)view.findViewById(R.id.btnFromDate);
        btnTo=(Button)view.findViewById(R.id.btnToDate);
         final DatePickerDialog.OnDateSetListener datePicker1,datePicker2;







        txtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtPrice.setError("your price should be at most one billion");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtPrice.getText().length()<=0){
                    txtPrice.setError("required,please enter your price");

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        productDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
productDesc.setError("Enter your description in 100 letter, please be specific.");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(productDesc.getText().length()==0){
                    productDesc.setError("required");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtTitle.setError("enter your title in 20 letters");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtTitle.getText().length()<=0){
                    txtTitle.setError("required");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        producTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(producTitle.getText().length()<=0){
                    producTitle.setError("Required");
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtDesc.getText().length()<=0)
                 txtDesc.setError("required");
                }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if(spnCategory.getSelectedItem().toString().trim().equals("Category")){
            spnCategory.setFocusable(true);

        }


        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(
                        view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , DatePicker1
                        ,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }

        });
        DatePicker1=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                dateFrom =month+"/" + dayOfMonth +"/"+year;
                txtFrom.setText(dateFrom);
            }
        };
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(
                        view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , DatePicker2
                        ,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });
        DatePicker2=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                 dateTo=month+"/" + dayOfMonth +"/"+year;
                txtTo.setText(dateTo);
            }
        };

             btnOffer=(Button)view.findViewById(R.id.btnOffer);
             btnOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOffer();

            }
        });



        return view;
    }

public void insertOffer(){
    StringRequest postRequest = new StringRequest(Request.Method.POST,"http://192.168.1.26/InsertOffer.php",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Toast.makeText(getContext().getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.getString("response").contains("User Added Successfuly!") ) {

                            Toast.makeText(getContext().getApplicationContext(), "connect", Toast.LENGTH_LONG).show();
                            Fragment productDetailFragment = new Offer_Done();
                            FragmentManager ft = getFragmentManager();
                            ft.beginTransaction().replace(R.id.offerFrame, productDetailFragment).commit();


                        } else if(jsonObject.getString("response").equals("OooPS ... something went wrong!") )

                            Toast.makeText(getContext().getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {


            if (error instanceof ServerError)
                Toast.makeText(getContext().getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
            else if (error instanceof TimeoutError)
                Toast.makeText(getContext().getApplicationContext(), "Connection Timed Out", Toast.LENGTH_SHORT).show();
            else if (error instanceof NetworkError)
                Toast.makeText(getContext().getApplicationContext(), "Bad Network Connection", Toast.LENGTH_SHORT).show();
        }



    })

    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError
        {
            Map <String,String> param=new HashMap<String, String>() ;
            param.put("title",txtTitle.getText().toString());
            param.put("city","Zagazig");

            param.put("date_from",dateFrom);
            param.put("date_to",dateTo);
            param.put("description",txtDesc.getText().toString());
            param.put("product_name",producTitle.getText().toString());
            param.put("price",txtPrice.getText().toString());


            param.put("product_desc",productDesc.toString());

            param.put("user_id",String.valueOf(1));
            param.put("cat_id",String.valueOf(1));




            return  param;

        }
    };

    Volley.newRequestQueue(getContext()).add(postRequest);

   }
}

