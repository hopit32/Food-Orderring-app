package com.example.project_oderapp.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project_oderapp.MANAGE.ManageCard;
import com.example.project_oderapp.MANAGE.ShareReferenceDatabase;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

public class DetailProduct extends AppCompatActivity implements View.OnClickListener{
    ImageView image, img_mincount,img_pluscount,back;
    TextView txt_name,txt_price,txt_description,txt_location,txt_timedeliver, numberinCart;
    RelativeLayout lo_addtoCart;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    Product product;
    ManageCard manageCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.detail_food);
        InitView();
        SetInformation();
        setSupportActionBar(toolbar);

        //
        img_pluscount.setOnClickListener(this::onClick);
        img_mincount.setOnClickListener(this::onClick);
        lo_addtoCart.setOnClickListener(this::onClick);
        back.setOnClickListener(this::onClick);


    }

    private void InitView()
    {
        back = findViewById(R.id.detailFood_back);
        numberinCart = findViewById(R.id.detaiFood_numberinCart);
        img_mincount = findViewById(R.id.detailFood_mincount);
        img_pluscount = findViewById(R.id.detailFood_pluscount);
        lo_addtoCart = findViewById(R.id.layout_apptocart);
        toolbar = findViewById(R.id.toolbar_detailFood);
        image = findViewById(R.id.detailFood_image);
        txt_name = findViewById(R.id.detailFood_name);
        txt_price = findViewById(R.id.detailFood_price);
        txt_description = findViewById(R.id.detailFood_description);
        txt_location = findViewById(R.id.detailFood_location);
        txt_timedeliver = findViewById(R.id.detailFood_delivertime);
        appBarLayout = findViewById(R.id.detailFood_appBar);

        appBarLayout.setOutlineProvider(null);
        toolbar.setTitle("");

    }
    private void SetInformation()
    {
        manageCard = new ManageCard(this);
        Bundle bundle = getIntent().getExtras();
        product= (Product) bundle.getSerializable("product");
        Picasso.with(this).load(product.getImage()).into(image);
        txt_name.setText(product.getName());
        txt_price.setText(product.getPrice());
        txt_description.setText(product.getDescription());
        txt_location.setText(product.getLocation());
        txt_timedeliver.setText(product.getTimedeliver());
    }
    public void add()
    {
       int x= Integer.parseInt(numberinCart.getText().toString());
       numberinCart.setText(String.valueOf(x+1));
    }
    public void reduce()
    {
        int x= Integer.parseInt(numberinCart.getText().toString());
        if (x>1)
        {
            numberinCart.setText(String.valueOf(x-1));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.detailFood_mincount:
            {
                reduce();
                break;
            }
            case R.id.detailFood_pluscount:
            {
                add();
                break;
            }
            case R.id.layout_apptocart:
            {
                product.setNumberinCart(Integer.parseInt(numberinCart.getText().toString()));
                manageCard.InsertProduct(product);
                break;
            }
            case R.id.detailFood_back:
            {
                onBackPressed();
            }
        }
    }
}