package com.example.project_oderapp.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project_oderapp.ADAPTER.FoodCart_Adapter;
import com.example.project_oderapp.INTERFACE.ChangeNumberItemListenner;
import com.example.project_oderapp.INTERFACE.Update_layout_Cart;
import com.example.project_oderapp.MANAGE.ManageCard;
import com.example.project_oderapp.R;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView_Item;
    TextView txt_subtotal, txt_Total, txt_pricedeliver, txt_Checkout,txt_ordernow;
    RelativeLayout layout_cart_empty;
    ManageCard manageCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        manageCard = new ManageCard(this);
        InitView();
        UpdateTotalPrice();
        SetRecyclerView();
        txt_Checkout.setOnClickListener(this::onClick);
        txt_ordernow.setOnClickListener(this::onClick);

    }
    private void SetRecyclerView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_Item.setLayoutManager(linearLayoutManager);
        recyclerView_Item.setAdapter(new FoodCart_Adapter(this, manageCard.getListCart(), new ChangeNumberItemListenner() {
            @Override
            public void change() {
                UpdateTotalPrice();
            }
        }, new Update_layout_Cart() {
            @Override
            public void setData() {
                layout_cart_empty.setVisibility(View.VISIBLE);
            }
        }));
    }
    private void InitView()
    {
        txt_ordernow = findViewById(R.id.Cart_txt_ordernow);
        layout_cart_empty = findViewById(R.id.Cart_layout_cart_empty);
        recyclerView_Item = findViewById(R.id.Card_recyclerViewItem);
        txt_Total = findViewById(R.id.Card_mainTotal);
        txt_subtotal = findViewById(R.id.Card_subtotal);
        txt_Checkout = findViewById(R.id.Card_checkout);
        txt_pricedeliver = findViewById(R.id.Card_deliverPrice);

    }
    public void UpdateTotalPrice()
    {
        Double subtotal = Math.round(manageCard.GetTotalItem())*100/100.0;
        txt_subtotal.setText(String.valueOf(subtotal));
        double price_deliver = Double.parseDouble(txt_pricedeliver.getText().toString());

        double total = Math.round(price_deliver + (Double)manageCard.GetTotalItem())*100 / 100.0;
        txt_Total.setText(String.valueOf(total));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Card_checkout:
            {
                Intent intent = new Intent(CartActivity.this, Checkout_activity.class);
                startActivity(intent);
                break;
            }
            case R.id.Cart_txt_ordernow:
            {
                onBackPressed();
            }
        }

    }

    @Override
    protected void onStart() {
        int x= manageCard.getListCart().size();
        if (x!=0) layout_cart_empty.setVisibility(View.GONE);
        super.onStart();
    }
}