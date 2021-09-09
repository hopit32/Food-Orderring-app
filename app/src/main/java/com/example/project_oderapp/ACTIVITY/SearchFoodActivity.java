package com.example.project_oderapp.ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_oderapp.ADAPTER.HomeProduct_Adapter;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class SearchFoodActivity extends AppCompatActivity {
    EditText edt_Search;
    RecyclerView recyclerView ;
    TextView txt_count;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");
        InitView();





        edt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                     FirebaseSearch(editable.toString());
            }
        });

    }
    private void FirebaseSearch(String text)
    {


            Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("Product").orderByChild("name").startAt(text).endAt(text + "\uf8ff");
            FirebaseRecyclerOptions<Product> options =
                    new FirebaseRecyclerOptions.Builder<Product>()
                            .setQuery(firebaseSearchQuery, Product.class)
                            .build();
            adapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
                @NonNull

                @Override
                public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_inrestaurant, parent, false);
                    return new ProductViewHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
                    holder.setDetail(getApplicationContext(), model.getName(), model.getCalories(), model.getPrice(), model.getImage(), model.getDescription());
                   holder.txt_showdetail.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent intent = new Intent(getApplicationContext(), DetailProduct.class);
                           intent.putExtra("product", model);
                           startActivity(intent);
                       }
                   });
                }

                @Override
                public void onDataChanged() {
                    super.onDataChanged();
                }

                @Override
                public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
                    txt_count.setText(String.valueOf(adapter.getItemCount()) + " dishes found");
                    super.onChildChanged(type, snapshot, newIndex, oldIndex);
                }
            };

            adapter.startListening();
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);

    }
    public class ProductViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        TextView txt_showdetail;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_showdetail = itemView.findViewById(R.id.itemFoodinREs_showdetail);
            mView =itemView;
        }
        public void setDetail(Context context, String namefood, String calo_food, String price_food, String img_food, String des_food)
        {


              TextView name =  mView.findViewById(R.id.itemFoodinREs_name);
              TextView calories = mView.findViewById(R.id.itemFoodinREs_calories);
              TextView price = mView.findViewById(R.id.itemFoodinREs_price);
            ImageView imageView = mView.findViewById(R.id.itemFoodinREs_image);
            TextView description = mView.findViewById(R.id.itemFoodinREs_description);

            name.setText(namefood);
            calories.setText(calo_food);
            price.setText("$"+price_food);
            Picasso.with(context).load(img_food).into(imageView);
            description.setText(des_food);

        }
    }



    public void InitView()
    {
        edt_Search = findViewById(R.id.searchview_edtSearch);
        recyclerView = findViewById(R.id.searchview_recyclerView);
        txt_count = findViewById(R.id.searchview_countfood);
    }

    @Override
    protected void onStart() {
       // adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
       // adapter.stopListening();
        super.onStop();
    }
}