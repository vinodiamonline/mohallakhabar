package com.vinnovation.mohallakhabar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Weather extends AppCompatActivity {

    String weatherurl;
    RequestQueue mQueue;
    ImageView iv;
    TextView tvsummary;
    TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Get reference of widgets from XML layout
        iv = (ImageView) findViewById(R.id.imageViewweather);
        tvsummary = (TextView) findViewById(R.id.txtWeather);
        tvDetail = (TextView) findViewById(R.id.txtweatherdetail);

        weatherurl= "http://api.openweathermap.org/data/2.5/weather?q=Noida,in&appid=d65196a1f5545b774786d4a1455b9ae8";
        mQueue = Volley.newRequestQueue(this);

        fillWeatherData();
    }
    public void fillWeatherData() {
        tvsummary.setText("");
        tvDetail.setText("");

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, weatherurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // JSON code
                        try {
                            JSONArray jaWeather = response.getJSONArray("weather");
                            JSONObject jweather = jaWeather.getJSONObject(0);
                            tvDetail.append("Main " + jweather.getString("main") + "\n");
                            tvDetail.append("Description " + jweather.getString("description") + "\n");

                            JSONObject joMain = response.getJSONObject("main");
                            tvsummary.append("Temp " + joMain.getString("temp") + "\n");
                            tvsummary.append("Pressure " + joMain.getString("pressure") + "\n");
                            tvsummary.append("Humidity " + joMain.getString("humidity") + "\n");
                            tvsummary.append("Temp Min " + joMain.getString("temp_min") + "\n");
                            tvsummary.append("Temp Max " + joMain.getString("temp_max") + "\n");

                            JSONObject joWind = response.getJSONObject("wind");
                            tvDetail.append("Wind Speed " + joWind.getString("speed") + "\n");
                            tvDetail.append("Wind degree " + joWind.getString("deg") + "\n");


                            JSONObject joSys = response.getJSONObject("sys");

                            tvDetail.append("Sunrise " + joSys.getString("sunrise") + "\n");
                            tvDetail.append("Sunset " + joSys.getString("sunset") + "\n");
                            
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(jsonReq);
    }
}
