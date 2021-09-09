package com.example.project_oderapp.ACTIVITY;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_oderapp.ADAPTER.FoodOfRestaurant_Adapter;
import com.example.project_oderapp.ADAPTER.HomeProduct_Adapter;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.MODEL.Restaurant;
import com.example.project_oderapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailRestaurant extends AppCompatActivity {
    ImageView image;
    TextView res_name, res_address, res_rate, res_description;
    RecyclerView recyclerView_FoodofRes;
    DatabaseReference databaseReference;
    ArrayList<Product> productList;
    private static String idRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        InitView();
        InitInformation();
        LoadListFoodOfRestaurant();
    }
    public void InitView()
    {
        productList = new ArrayList<>();
        image = findViewById(R.id.detailrestaurant_image);
        res_name = findViewById(R.id.detailrestaurant_name);
        res_address = findViewById(R.id.itemrestaurant_address);
        res_rate = findViewById(R.id.detailrestaurant_rate);
        res_description = findViewById(R.id.detailrestaurant_description);
        recyclerView_FoodofRes = findViewById(R.id.detailrestaurant_recyclerView_Food);
    }
    public void InitInformation()
    {
        Bundle bundle = getIntent().getExtras();
        Restaurant restaurant = (Restaurant) bundle.getSerializable("restaurant");
        Picasso.with(this)
                .load(restaurant.getImage())
                .into(image);
        res_name.setText(restaurant.getName());
        res_rate.setText(restaurant.getRate());
        res_description.setText(restaurant.getAbout());
        idRes = restaurant.getId();

    }
    public void LoadListFoodOfRestaurant()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference = FirebaseDatabase.getInstance().getReference("Product");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            Product product = dataSnapshot.getValue(Product.class);
                            if (product.getRestaurant().equals(idRes))
                            {
                                productList.add(product);
                            }

                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailRestaurant.this, LinearLayoutManager.VERTICAL, false);
                        // GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2 );
                        recyclerView_FoodofRes.setLayoutManager(linearLayoutManager);
                        recyclerView_FoodofRes.setAdapter(new FoodOfRestaurant_Adapter(DetailRestaurant.this,productList));
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        thread.start();
    }
}