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

import com.example.project_oderapp.ACTIVITY.OrderDetailActivity;
import com.example.project_oderapp.MODEL.Bill;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ItemViewHolder> {
    Context context ;
    ArrayList<Bill> bills;
    public BillAdapter(Context context,ArrayList<Bill> bills)
    {
        this.context = context;
        this.bills = bills;
    }
    @NonNull
    @Override
    public BillAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itembill_hostory,parent,false);
        return new BillAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ItemViewHolder holder, int position) {
        Bill bill = bills.get(position);
        holder.date.setText(bill.getDate());
        holder.Price.setText(bill.getTotal());
        holder.payment_method.setText("("+bill.getPayment_method()+")");
        holder.nameFood.setText(bill.getProducts().get(0).getName()+"...");
        holder.show_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), OrderDetailActivity.class);
                intent.putExtra("bill",bill);
                context.startActivity(intent);
            }
        });
        holder.show_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), OrderDetailActivity.class);
                intent.putExtra("bill", bill);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView date, nameFood, Price, payment_method;
        ImageView show_detail;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.itembill_date);
            nameFood= itemView.findViewById(R.id.itembill_name);
            Price = itemView.findViewById(R.id.itembill_total);
            payment_method = itemView.findViewById(R.id.itembill_paymentmethod);
            show_detail = itemView.findViewById(R.id.itembill_showdetail);
        }
    }
}
