package com.example.project_oderapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;

import java.util.ArrayList;

public class ItemFood_OrderDetail extends RecyclerView.Adapter<ItemFood_OrderDetail.ItemViewHolder> {
    Context context;
    ArrayList<Product> list = new ArrayList<>();
    public  ItemFood_OrderDetail(Context context, ArrayList<Product> list)
    {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ItemFood_OrderDetail.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemfood_indetail_bill, parent,false);
        return new ItemFood_OrderDetail.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemFood_OrderDetail.ItemViewHolder holder, int position) {
        Product product = list.get(position);
         holder.name.setText(product.getName());
         holder.count.setText(product.getNumberinCart()+" x ");
        double x = product.getNumberinCart() * Double.parseDouble(product.getPrice());
        double y = Math.round(x)*100/100.0;
        holder.price.setText(String.valueOf(y));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView count, price,name;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.itemfood_orderdetail_count);
            price = itemView.findViewById(R.id.itemfood_orderdetail_price);
            name = itemView.findViewById(R.id.itemfood_orderdetail_name);
        }
    }
}
