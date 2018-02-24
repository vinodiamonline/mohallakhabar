package com.vinnovation.mohallakhabar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String weatherurl;
    public static JSONArray jarraynews;
    String newsurl;
    RequestQueue mQueue;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherurl= "http://api.openweathermap.org/data/2.5/weather?q=Noida,in&appid=d65196a1f5545b774786d4a1455b9ae8";
        newsurl = "https://newsapi.org/v2/top-headlines?country=in&apiKey=dda394e99a05430ba56db1aebdf105d7";
        mQueue = Volley.newRequestQueue(this);
     //   mTextView = (TextView) findViewById(R.id.textDescription);
    }

    public void onbtnclick(View vs) {
    //    final TextView mTextView = (TextView) findViewById(R.id.textDescription);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";
        url = "https://api.myjson.com/bins/190u51";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,response.length()-1));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void onbtnclickJson(View vs) {
        RequestQueue rqueue = Volley.newRequestQueue(this);

    //    final TextView mTextView = (TextView) findViewById(R.id.textDescription);
        // Instantiate the RequestQueue.
        String url = "https://api.myjson.com/bins/a0jrp";

// Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jarray = response.getJSONArray("employees");

                            for(int i = 0; i < jarray.length(); i++) {
                                JSONObject employee = jarray.getJSONObject(i);

                                String firstname = employee.getString("fname");
                                String pwd = employee.getString("password");

                                mTextView.append(firstname + ", " + pwd + "\n\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Display the first 500 characters of the response string.
                       // mTextView.setText("Response is: " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        rqueue.add(jsonRequest);
    }

    public void fetchWeather(View vs) {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, weatherurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // JSON code
                        try {
                            JSONArray jarray = response.getJSONArray("weather");

                            JSONObject jsonObject = jarray.getJSONObject(0);

                            String main = jsonObject.getString("main");
                            String desc = jsonObject.getString("description");

                            mTextView.setText("Main: " + main + " Description: " + desc);

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

    public void fetchNews(View vs) {
       Intent myIntent = new Intent(MainActivity.this, Headlines.class);
       MainActivity.this.startActivity(myIntent);
    }

    public void fetchCurrentWeather(View vs) {
        Intent myIntent = new Intent(MainActivity.this, Weather.class);
        MainActivity.this.startActivity(myIntent);
    }

}