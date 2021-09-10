package com.example.project_oderapp.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_oderapp.ADAPTER.ItemFood_OrderDetail;
import com.example.project_oderapp.MODEL.Bill;
import com.example.project_oderapp.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OrderDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView txt_date, txt_subtotal, txt_delivery, txt_total, txt_paymentmethod, txt_code, txt_username,txt_addressshipping, txt_phonenumber;
    ImageView img_bankingMethod, cancle;
    FirebaseUser mUser ;
    AppBarLayout appBarLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        InitView();
        InitInformation();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public void InitView() {
        cancle = findViewById(R.id.oderdetail_cancle);
        appBarLayout = findViewById(R.id.oderDetail_appbar);
        toolbar = findViewById(R.id.oderdetail_toolbar);
        recyclerView = findViewById(R.id.orderdetail_recyclerViewfood);
        txt_date = findViewById(R.id.oderdetail_date);
        txt_subtotal = findViewById(R.id.orderdetail_subtotal);
        txt_delivery=  findViewById(R.id.orderdetail_Pricedeliver);
        txt_total = findViewById(R.id.orderdetail_total);
        txt_paymentmethod = findViewById(R.id.orderdetail_paymentMethod);
        txt_code = findViewById(R.id.orderdetail_code);
        txt_username = findViewById(R.id.orderdetail_username);
        txt_addressshipping = findViewById(R.id.orderdetail_address);
        txt_phonenumber = findViewById(R.id.orderdetail_phonenumber);
        img_bankingMethod =  findViewById(R.id.orderdetail_iconpm);
    }
    public void InitInformation()
    {
        appBarLayout.setOutlineProvider(null);
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayShowTitleEnabled(false);
        Bundle bundle = getIntent().getExtras();
        Bill bill = (Bill) bundle.getSerializable("bill");
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        txt_date.setText(bill.getDate());
        txt_subtotal.setText(bill.getSubtotal());
        txt_delivery.setText(bill.getPrice_deliver());
        txt_total.setText(bill.getTotal());
        txt_paymentmethod.setText(bill.getPayment_method());
        txt_code.setText(bill.getCode());
        txt_username.setText(bill.getName());
        txt_addressshipping.setText(bill.getAddress());

        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ItemFood_OrderDetail(this, bill.getProducts()));
    }
}