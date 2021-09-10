package com.example.project_oderapp.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_oderapp.MODEL.Bill;
import com.example.project_oderapp.R;
import com.google.android.material.appbar.AppBarLayout;

public class OnPurchase extends AppCompatActivity implements View.OnClickListener{
    TextView txt_username, txt_subtotal, txt_deliverprice, txt_total, txt_orderDetail,txt_continueShopping;
    private static Bill bill;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_purchase);
        InitView();
        InitInformation();
        txt_continueShopping.setOnClickListener(this::onClick);
        txt_orderDetail.setOnClickListener(this::onClick);

    }

    public void InitView() {
        appBarLayout = findViewById(R.id.purchase_appbar);
        txt_username = findViewById(R.id.purchase_username);
        txt_subtotal = findViewById(R.id.purchase_subtotal);
        txt_deliverprice = findViewById(R.id.purchase_deliver);
        txt_total = findViewById(R.id.purchase_total);
        txt_orderDetail = findViewById(R.id.purchase_order_detail);
        txt_continueShopping = findViewById(R.id.purchase_continueShopping);
    }

    public void InitInformation() {
        appBarLayout.setOutlineProvider(null);
        Bundle bundle = getIntent().getExtras();
        bill = (Bill) bundle.getSerializable("bill");
        txt_username.setText("Hey " + bill.getName());
        txt_total.setText("$" + bill.getTotal());
        txt_subtotal.setText("$" + bill.getSubtotal());
        txt_deliverprice.setText("$3");
    }


    @Override
    public void onBackPressed() {

        return;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.purchase_continueShopping:
            {
                Intent intent = new Intent(OnPurchase.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                break;


            }
            case R.id.purchase_order_detail:
            {
                Intent intent = new Intent(OnPurchase.this, OrderDetailActivity.class);
                intent.putExtra("bill",bill);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        }
    }
}