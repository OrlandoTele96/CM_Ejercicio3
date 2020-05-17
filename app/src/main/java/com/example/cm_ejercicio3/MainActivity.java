package com.example.cm_ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray>{


    TextView tvTitle;
    ListView itemslist;
    String url;

    RequestQueue queue;
    JsonArrayRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);

        url =getResources().getString(R.string.url)+getResources().getString(R.string.items);

        tvTitle.setText(getResources().getString(R.string.itemslist));

        queue =Volley.newRequestQueue(this);
        request = new JsonArrayRequest(Request.Method.GET,url,null,this,this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        Log.d(getResources().getString(R.string.logresponse),response.toString());

        JSONObject jsonObject;

        try {
                for(int i=0;i<response.length();i++)
                {
                    jsonObject = response.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    Log.d(getResources().getString(R.string.logproduct),name+id);
                }
        }catch (JSONException e)
        {

        }
    }
}
