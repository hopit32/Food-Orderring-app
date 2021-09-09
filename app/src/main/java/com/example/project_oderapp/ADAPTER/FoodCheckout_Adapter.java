package com.example.project_oderapp.ADAPTER;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodCheckout_Adapter extends RecyclerView.Adapter<FoodCheckout_Adapter.ItemViewHolder> {
    ArrayList<Product> list = new ArrayList<>();
   Context context;

   public FoodCheckout_Adapter(Context context , ArrayList<Product> list)
   {
       this.context = context;
       this.list = list;
   }
    @NonNull
    @Override
    public FoodCheckout_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemfood_incheckout, parent,false);
       return new FoodCheckout_Adapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCheckout_Adapter.ItemViewHolder holder, int position) {
       Product product = list.get(position);
        Picasso.with(context)
                .load(product.getImage()).resize(200,0).into(holder.image);

        holder.name.setText("x "+ product.getName());
        holder.count.setText(String.valueOf(product.getNumberinCart()));
        double x = product.getNumberinCart() * Double.parseDouble(product.getPrice());
        double y = Math.round(x)*100/100.0;
        holder.totalItem.setText(String.valueOf(y));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, count, totalItem;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemfoodCheckout_image);
            name = itemView.findViewById(R.id.itemfoodCheckout_name);
            count = itemView.findViewById(R.id.itemfoodCheckout_count);
            totalItem = itemView.findViewById(R.id.itemfoodCheckout_totalItem);
        }
    }
}
