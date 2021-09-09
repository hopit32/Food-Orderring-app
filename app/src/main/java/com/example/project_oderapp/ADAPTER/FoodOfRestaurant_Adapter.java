package com.example.project_oderapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.ACTIVITY.DetailProduct;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodOfRestaurant_Adapter extends RecyclerView.Adapter<FoodOfRestaurant_Adapter.ItemViewHolder> {
    Context context;
    ArrayList<Product> list;

    public FoodOfRestaurant_Adapter(Context context, ArrayList<Product> list)
    {
        this.context = context;
        this.list= list;
    }
    @NonNull
    @Override
    public FoodOfRestaurant_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food_inrestaurant,parent, false);
        return new FoodOfRestaurant_Adapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOfRestaurant_Adapter.ItemViewHolder holder, int position) {
        Product product = list.get(position);
        Picasso.with(context)
                .load(product.getImage()).resize(200,0).into(holder.imagefood);
        holder.namefood.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        holder.desciption.setText(list.get(position).getDescription());
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
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imagefood;
        TextView namefood, price, desciption, showdetail;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imagefood = itemView.findViewById(R.id.itemFoodinREs_image);
            namefood = itemView.findViewById(R.id.itemFoodinREs_name);
            price = itemView.findViewById(R.id.itemFoodinREs_price);
            desciption = itemView.findViewById(R.id.itemFoodinREs_description);
            showdetail = itemView.findViewById(R.id.itemFoodinREs_showdetail);
        }
    }
}
