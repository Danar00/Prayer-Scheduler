package com.example.prayerschedule;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoSolatActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    String url;
    //https://time.siswadi.com/pray/Jakarta
    ProgressDialog pDialog;
    TextView fajr, dzhur, asr, maghrib, isha, location, date, day, sunrise, time;
    EditText search_edit;
    Button search_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_solat);

        location = findViewById(R.id.tv_location);
        date = findViewById(R.id.tv_date);
        time = findViewById(R.id.tv_time);

        fajr = findViewById(R.id.tv_fajr);
        sunrise = findViewById(R.id.tv_sunrise);
        dzhur = findViewById(R.id.tv_dhuhr);
        asr = findViewById(R.id.tv_asr);
        maghrib = findViewById(R.id.tv_maghrib);
        isha = findViewById(R.id.tv_isya);
        search_edit = findViewById(R.id.et_search);
        search_button = findViewById(R.id.btn_search);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isTextReady = search_edit.getText().toString().trim();
                if (isTextReady.isEmpty()){
                    Toast.makeText(InfoSolatActivity.this, "please enter the city", Toast.LENGTH_SHORT).show();
                }else {
                    url = "http://muslimsalat.com/"+isTextReady+".json?key=f88927b3b6486822e9722dec6ebd5776";
                    //"http://muslimsalat.com/"+isTextReady+".json?key=f88927b3b6486822e9722dec6ebd5776"
                    //"https://time.siswadi.com/pray/"+isTextReady;

                    searchLocation();
                }
            }
        });

    }

    private void searchLocation(){
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String mLocation = response.get("query").toString();
                            String mCountry = response.get("country").toString();
                            String address = mLocation + " " + mCountry;
                            String mTime = response.get("qibla_direction").toString();
                            String mDate = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();
                            String mFajr = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mSunrise = response.getJSONArray("items").getJSONObject(0).get("shurooq").toString();
                            String mDzhur = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMagrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();

                            location.setText(address);
                            date.setText(mDate);
                            time.setText(mTime);

                            fajr.setText(mFajr);
                            sunrise.setText(mSunrise);
                            dzhur.setText(mDzhur);
                            asr.setText(mAsr);
                            maghrib.setText(mMagrib);
                            isha.setText(mIsha);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(InfoSolatActivity.this, "Error", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                pDialog.hide();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }




}
