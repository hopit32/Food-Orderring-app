package com.example.project_oderapp.ADAPTER;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_oderapp.ACTIVITY.DetailProduct;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.MainActivity;
import com.example.project_oderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class HomeProduct_Adapter extends RecyclerView.Adapter<HomeProduct_Adapter.ProductItemViewHolder> {
    List <Product> productList = new ArrayList<>();
    Context context;

    public  HomeProduct_Adapter(Context context, List<Product> productList)
    {
        this.context = context;
        this.productList = productList;
    }
    @NonNull
    @Override
    public HomeProduct_Adapter.ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_food, parent, false);

        return new HomeProduct_Adapter.ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProduct_Adapter.ProductItemViewHolder holder, int position) {
        Product product = productList.get(position);
        Picasso.with(context)
                .load(productList.get(position).getImage()).resize(200,0).into(holder.imagefood);
        holder.namefood.setText(productList.get(position).getName());
        holder.price.setText(productList.get(position).getPrice());
        holder.desciption.setText(productList.get(position).getDescription());
        holder.showdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DetailProduct.class);
/*
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View,String>(holder.imagefood,"imageFoodtransition");
                pairs[1] = new Pair<View,String>(holder.namefood,"nameFoodtransition");
                pairs[2] = new Pair<View,String>(holder.desciption,"desciptionFoodtransition");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pairs);*/
                intent.putExtra("product", product);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imagefood, showdetail;
        TextView namefood, price, desciption;
        public ProductItemViewHolder(@NonNull View itemView) {

            super(itemView);
            imagefood = itemView.findViewById(R.id.itemFroduct_image);
            namefood = itemView.findViewById(R.id.itemFroduct_name);
            price = itemView.findViewById(R.id.itemFroduct_price);
            desciption = itemView.findViewById(R.id.itemFroduct_description);
            showdetail = itemView.findViewById(R.id.itemProduct_showdetail);

        }
    }
}
