package com.example.project_oderapp.ACTIVITY;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_oderapp.ADAPTER.FoodCheckout_Adapter;
import com.example.project_oderapp.MANAGE.ManageCard;
import com.example.project_oderapp.MODEL.Bill;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.MODEL.User;
import com.example.project_oderapp.R;
import com.example.project_oderapp.SERVICE.UserSerVice;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Checkout_activity extends AppCompatActivity implements View.OnClickListener {
    TextView txt_username,txt_phonenumber,txt_address, txt_date,txt_totalItem, txt_PriceDeliver,txt_total,txt_HomeMethod, txt_BankingMethod, CHECK_OUT;
    FirebaseUser mUser;
    String idUserCurrent;
    Context context;
    RecyclerView recyclerView ;
    ManageCard manageCard;
    String Method = "cash";
    String IDUSER,DATE,TOTAL,DELIVER_PRICE,SUB_TOTAL,USER_NAME;
    private static Bill bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_one_checkout);
        manageCard = new ManageCard(this);
        InitView();
        InitUserInformation();
        InitInfoProduct();
        Caculator();
        txt_HomeMethod.setOnClickListener(this::onClick);
        txt_BankingMethod.setOnClickListener(this::onClick);
        CHECK_OUT.setOnClickListener(this::onClick);
    }
    private void InitView()
    {
        txt_username = findViewById(R.id.stepone_username);
        txt_phonenumber = findViewById(R.id.stepone_phonenumber);
        txt_address = findViewById(R.id.stepone_addressshipping);
        txt_date = findViewById(R.id.stepone_date);
        txt_totalItem = findViewById(R.id.stepone_totalItem);
        txt_PriceDeliver = findViewById(R.id.stepone_priceDeliver);
        txt_total = findViewById(R.id.stepone_total);
        txt_HomeMethod = findViewById(R.id.stepone_HomeMothod);
        txt_BankingMethod  = findViewById(R.id.stepone_BankingMethod);
        CHECK_OUT = findViewById(R.id.stepone_checkout);
        recyclerView = findViewById(R.id.stepone_recyclerViewFood);
    }
    public void InitUserInformation()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mUser= FirebaseAuth.getInstance().getCurrentUser();
                idUserCurrent = mUser.getUid();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(idUserCurrent);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        IDUSER = user.getUser_id();
                        USER_NAME= user.getUser_username();
                        txt_username.setText(user.getUser_username());
                        txt_address.setText(user.getUser_address_getfood());
                        txt_phonenumber.setText("- "+user.getUser_numberphone());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        thread.start();
        //
        Calendar calendar =  Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm");
        DATE=(simpleDateFormat2.format(calendar.getTime())+" Today ");
        DATE = DATE+(" - "+simpleDateFormat1.format(calendar.getTime()));
        txt_date.setText(DATE);

    }
    public void Caculator()
    {
        Double a = Math.round(manageCard.GetTotalItem())*100/100.0;
        SUB_TOTAL= String.valueOf(a);
        txt_totalItem.setText(SUB_TOTAL);
        DELIVER_PRICE= "3";

        Double y = Math.round(3+a)*100/100.00;
        TOTAL = String.valueOf(y);
        txt_total.setText("$"+TOTAL);
        CHECK_OUT.setText("Place oder - $"+TOTAL);
    }
    public void InitInfoProduct()
    {
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new FoodCheckout_Adapter(this,manageCard.getListCart() ));

    }
    public String getCode()
    {
        String x= String.valueOf(System.currentTimeMillis());
        String[] y = x.split(String.valueOf(x.charAt(5)),2);
        String code= y[0]+"-"+y[1];
        return code;


    }
    public void CHECK_OUT()
    {
        DatabaseReference databaseReference1;
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Bill").child(IDUSER).push();
        ArrayList<Product> list = manageCard.getListCart();
        bill = new Bill(txt_address.getText().toString(),USER_NAME,getCode(),IDUSER,Method,TOTAL,DATE,"",DELIVER_PRICE,list,SUB_TOTAL);


        HashMap hashMap = new HashMap();
        hashMap.put("subtotal",bill.getSubtotal());
        hashMap.put("address",bill.getAddress_Shipping());
        hashMap.put("name",bill.getName());
        hashMap.put("iduser",bill.getIduser());
        hashMap.put("price_deliver",bill.getPrice_deliver());
        hashMap.put("total",bill.getTotal());
        hashMap.put("payment_method",bill.getPayment_method());
        hashMap.put("date",bill.getDate());
        hashMap.put("note",bill.getDate());
        hashMap.put("product",list);
        hashMap.put("code",bill.getCode());

        //
      //  String x= UserSerVice.getInstance().getInfoUser().getUser_address();
      //  Toast.makeText(this,x,Toast.LENGTH_LONG).show();
        databaseReference1.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(Checkout_activity.this,OnPurchase.class);
                intent.putExtra("bill",bill);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                UpdateManaCart();
            }
        });

        //Update

    }
    public void UpdateManaCart()
    {
        InitInfoProduct();
        Caculator();
        manageCard.Clear();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.stepone_BankingMethod:
            {
                Method = "banking";
                //
                txt_BankingMethod.setBackgroundResource(R.drawable.boder_cluster_checkbox);
                txt_BankingMethod.setTextColor(ContextCompat.getColor(this, R.color.orange));
                //
                txt_HomeMethod.setBackgroundResource(R.drawable.boder_payment_method);
                txt_HomeMethod.setTextColor(ContextCompat.getColor(this, R.color.black));
                break;
            }
            case R.id.stepone_HomeMothod:
            {
                Method = "cash";
                txt_HomeMethod.setBackgroundResource(R.drawable.boder_cluster_checkbox);
                txt_HomeMethod.setTextColor(ContextCompat.getColor(this, R.color.orange));
                //
                txt_BankingMethod.setBackgroundResource(R.drawable.boder_payment_method);
                txt_BankingMethod.setTextColor(ContextCompat.getColor(this, R.color.black));
                break;
            }
            case R.id.stepone_checkout:
            {
              CHECK_OUT();

              break;

            }
        }

    }

    @Override
    public void onBackPressed() {
        String a = "";
        super.onBackPressed();
    }
}