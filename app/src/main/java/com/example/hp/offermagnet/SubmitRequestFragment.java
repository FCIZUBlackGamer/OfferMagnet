package com.example.hp.offermagnet;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SubmitRequestFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DatePickerDialog.OnDateSetListener datePicker;
    Button btnSetDate;
    TextView txtDate;
    private String mParam1;
    private String mParam2;


    public SubmitRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView txtMeters;
    SeekBar seekbar;
    EditText txtTitle;
    Spinner spnCategory;
    EditText txtDesc;
    Button btnSubmit;
    String date;
    int a = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_request, container, false);
        txtMeters = (TextView) view.findViewById(R.id.textKilo);
        seekbar = (SeekBar) view.findViewById(R.id.barLocation);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtMeters.setText(seekbar.getProgress() + " kilo maeters");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        txtTitle = (EditText) view.findViewById(R.id.title);
        txtDesc = (EditText) view.findViewById(R.id.description);
        txtTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (txtTitle.getText().length() == 0) {
                    txtTitle.setError("required");
                }
            }
        });
        txtDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (txtDesc.getText().length() == 0) {
                    txtDesc.setError("required");
                }
            }
        });
     /*   if(spnCategory.getSelectedItem().toString().trim().equals("Category")){
            spnCategory.setFocusable(true);
        }*/


        btnSetDate = (Button) view.findViewById(R.id.btnSetDate);
        txtDate = (TextView) view.findViewById(R.id.txtDate);
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        v.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , datePicker
                        , year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = month + "-" + dayOfMonth + "-" + year;
                txtDate.setText(date);
            }
        };
        btnSubmit = (Button) view.findViewById(R.id.submitRequest);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtTitle.getText().toString().equals("") || txtDesc.getText().toString().equals("") || date.equals("")){
                    Toast.makeText(getContext().getApplicationContext(), "InValid", Toast.LENGTH_SHORT).show();
                }else {
                    submitReq();
                }

            }
        });
        return view;
    }



    public void submitReq() {
        final String URL = "http://192.168.1.26/InsertRequest.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {

                    if (response.contains("Request Approved!")) {
                        Toast.makeText(getContext().getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                        Fragment done = new Task_is_complete();
                        FragmentManager ft = getFragmentManager();
                        ft.beginTransaction().replace(R.id.frameRequest, done).commit();
                    }else {
                        Toast.makeText(getContext().getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                Toast.makeText(getContext().getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", txtTitle.getText().toString());
                params.put("dis", txtDesc.getText().toString());
                params.put("validate_date", date);
                params.put("city", "zagazig");

                params.put("cat_id", 1 + "");
                params.put("user_id", 1 + "");

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}
