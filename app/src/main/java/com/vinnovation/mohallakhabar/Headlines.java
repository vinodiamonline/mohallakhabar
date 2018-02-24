package com.vinnovation.mohallakhabar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Headlines extends AppCompatActivity {

    String newsurl;
    RequestQueue mQueue;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);

        // Get reference of widgets from XML layout
        lv = (ListView) findViewById(R.id.lv);

        newsurl = "https://newsapi.org/v2/top-headlines?country=in&apiKey=dda394e99a05430ba56db1aebdf105d7";
        mQueue = Volley.newRequestQueue(this);

        fillNewsData();
    }
    public void fillNewsData() {
        final List<String> news_list = new ArrayList<String>();
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, newsurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // JSON code
                        try {
                            JSONArray jarray = response.getJSONArray("articles");

                            for (int i = 0; i< jarray.length();i++) {
                                JSONObject title = jarray.getJSONObject(i);
                                news_list.add(title.getString("title"));
                            }

                            // Create an ArrayAdapter from List
                            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                                    (Headlines.this, R.layout.activity_listview, news_list);

                            // DataBind ListView with items from ArrayAdapter
                            lv.setAdapter(arrayAdapter);


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
