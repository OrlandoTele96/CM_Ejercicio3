package com.example.cm_ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.cm_ejercicio3.model.Product;

public class ProductInfo_Activity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        product = (Product) bundle.getSerializable("selected");


        Log.d("ID","The id is ; "+product.getId());

    }
}
