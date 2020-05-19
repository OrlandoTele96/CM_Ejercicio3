package com.example.cm_ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

        url =getResources().getString(R.string.url)+getResources().getString(R.string.items);
        products = new ArrayList<Product>();

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
                    String thumnail =jsonObject.getString("thumbnail_url");
                    String price = jsonObject.getString("price");
                    String provider = jsonObject.getString("provider");
                    String delivery = jsonObject.getString("delivery");
                    Product product = new Product(id,name,thumnail,price,provider,delivery);
                    products.add(product);
                    Log.d(getResources().getString(R.string.logproduct),name+id);
                }
                ProductAdapter adaptador = new ProductAdapter(products,getApplicationContext());
                lvItems.setAdapter(adaptador);

                lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(MainActivity.this,ProductInfo_Activity.class);
                        Log.d("SELECTION","product id : "+products.get(position).getId());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selected",products.get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
        }catch (JSONException e)
        {

        }
    }
}
