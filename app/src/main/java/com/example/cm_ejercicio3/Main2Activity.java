package com.example.cm_ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm_ejercicio3.model.Product;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>{


    Product product;
    String url;
    RequestQueue queue;
    JsonObjectRequest request;

    ImageView imProduct;
    TextView tvProductName,tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvProductName=findViewById(R.id.tvProductName);
        tvInfo = findViewById(R.id.tvInfo);

        imProduct = findViewById(R.id.imProduct);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        product = (Product) bundle.getSerializable(getResources().getString(R.string.bundlelabel));

        String id = Integer.toString(product.getId());

        url = getResources().getString(R.string.url)+getResources().getString(R.string.iteminfo_url)+id;

        Log.d("URL",url);
        queue = Volley.newRequestQueue(this);
        request = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {

        //Log.d(getResources().getString(R.string.logresponse),response.getString("imag_url"));


        try {
            tvProductName.setText(response.getString("name"));
            tvInfo.setText(response.getString("desc"));
            Picasso.with(Main2Activity.this).load(response.getString("imag_url")).into(imProduct);
        }
        catch (JSONException e)
        {

        }

    }
}
