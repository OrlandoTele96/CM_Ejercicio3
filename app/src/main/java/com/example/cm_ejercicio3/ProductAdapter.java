package com.example.cm_ejercicio3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cm_ejercicio3.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private ArrayList<Product> products;
    Context context;

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.item,null);
        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvProvider = v.findViewById(R.id.tvProvider);
        TextView tvPrice = v.findViewById(R.id.tvPrice);
        ImageView ivProduct = v.findViewById(R.id.ivProduct);

        tvName.setText(products.get(position).getName());
        tvPrice.setText(products.get(position).getPrice());
        tvProvider.setText(products.get(position).getProvider());

        ivProduct.setPadding(15,15,15,15);

        Picasso.with(context).load(products.get(position).getThumnail_url() ).into(ivProduct);

        Log.d(v.getResources().getString(R.string.logimagen),  products.get(position).getThumnail_url());

        return v;
    }
}
