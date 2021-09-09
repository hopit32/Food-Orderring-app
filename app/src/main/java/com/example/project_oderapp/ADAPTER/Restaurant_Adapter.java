package com.example.project_oderapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.ACTIVITY.DetailRestaurant;
import com.example.project_oderapp.MODEL.Restaurant;
import com.example.project_oderapp.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_Adapter extends RecyclerView.Adapter<Restaurant_Adapter.RestaurantItemViewHolder> {
    Context context;
    List<Restaurant> restaurantList = new ArrayList<>();

    public Restaurant_Adapter(Context context, List<Restaurant> restaurantList)
    {
        this.context = context;
        this.restaurantList = restaurantList;
    }
    @NonNull
    @Override
    public RestaurantItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant,parent,false);
        return new Restaurant_Adapter.RestaurantItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantItemViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.address.setText(restaurant.getAddress());
        holder.distance.setText(restaurant.getDistance());
        holder.name.setText(restaurant.getName());
        holder.rate.setText(restaurant.getRate());
        Picasso.with(context)
                .load(restaurant.getImage()).resize(200,0).into(holder.image);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DetailRestaurant.class);
                intent.putExtra("restaurant",restaurant);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class RestaurantItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView name,distance,address,rate;
       ImageView image;
        public RestaurantItemViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.res_layout);
            name = itemView.findViewById(R.id.itemrestaurant_name);
            distance = itemView.findViewById(R.id.itemrestaurant_distance);
            address = itemView.findViewById(R.id.itemrestaurant_address);
            rate = itemView.findViewById(R.id.itemrestaurant_rate);
            image = itemView.findViewById(R.id.itemrestaurant_image);
        }
    }
}
