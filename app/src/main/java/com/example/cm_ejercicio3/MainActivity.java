package com.example.cm_ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm_ejercicio3.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray>{


    TextView tvTitle;
    ListView lvItems;
    ProgressBar pbConect;
    String url;

    RequestQueue queue;
    JsonArrayRequest request;

    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        lvItems = findViewById(R.id.lvItems);
        pbConect = findViewById(R.id.pbConect);

        url =getResources().getString(R.string.url)+getResources().getString(R.string.items);
        products = new ArrayList<Product>();

        tvTitle.setText(getResources().getString(R.string.itemslist));

        queue =Volley.newRequestQueue(this);
        request = new JsonArrayRequest(Request.Method.GET,url,null,this,this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConect.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        pbConect.setVisibility(View.GONE);
        Log.d(getResources().getString(R.string.logresponse),response.toString());

        JSONObject jsonObject;

        try {
                for(int i=0;i<response.length();i++)
                {
                    jsonObject = response.getJSONObject(i);
                    int id = jsonObject.getInt(getResources().getString(R.string.itemid));
                    String name = jsonObject.getString(getResources().getString(R.string.itemname));
                    String thumnail =jsonObject.getString(getResources().getString(R.string.itemthum));
                    String price = jsonObject.getString(getResources().getString(R.string.itemprice));
                    String provider = jsonObject.getString(getResources().getString(R.string.itemprovider));
                    String delivery = jsonObject.getString(getResources().getString(R.string.itemdelivery));
                    Product product = new Product(id,name,thumnail,price,provider,delivery);
                    products.add(product);
                    Log.d(getResources().getString(R.string.logproduct),getResources().getString(R.string.logproduct_msj)+id);
                }
                ProductAdapter adaptador = new ProductAdapter(products,getApplicationContext());
                lvItems.setAdapter(adaptador);

                lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        Log.d(getResources().getString(R.string.logselect),getResources().getString(R.string.logselect_msj)+products.get(position).getName()+getResources().getString(R.string.logselect_msj_2)+products.get(position).getId());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(getResources().getString(R.string.bundlelabel),products.get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
        }catch (JSONException e)
        {

        }
    }
}
