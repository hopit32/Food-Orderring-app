package com.example.project_oderapp.FRAGMENT;

import android.app.Fragment;
import io.reactivex.Observable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.ADAPTER.BillAdapter;
import com.example.project_oderapp.ADAPTER.ItemFood_OrderDetail;
import com.example.project_oderapp.MODEL.Bill;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.internal.Sleeper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
   private static ArrayList<Bill> list_bill= new ArrayList<>();

    TextView textView;
    String x,y;
    AppBarLayout appBarLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_layout,container, false);

        //InitView
        appBarLayout = view.findViewById(R.id.listFrag_appBar);
        appBarLayout.setOutlineProvider(null);
        textView= view.findViewById(R.id.test);
        recyclerView = view.findViewById(R.id.history_recyclerView);
        InitInfomation();

        return view;

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
   public void setUp(ArrayList<Bill> bills)
    {
             recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
             recyclerView.setAdapter(new BillAdapter(getContext(), bills));
    }
    public void InitInfomation() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Bill").child(firebaseUser.getUid());
          databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
              @RequiresApi(api = Build.VERSION_CODES.M)
              @Override
              public void onComplete(@NonNull Task<DataSnapshot> task) {
                  ArrayList<Bill> list_bill1= new ArrayList<>();
                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {

                        Bill bill = dataSnapshot.getValue(Bill.class);
                        bill.setProducts(getProduct(dataSnapshot.child("product")));
                        list_bill1.add(bill);


                    }
                    list_bill= list_bill1;
                    setUp(list_bill);



              }
          });


    }
    public ArrayList<Product> getProduct(DataSnapshot dataSnapshot)
    {
        ArrayList<Product> products = new ArrayList<>();

        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
        {
            Product product = dataSnapshot1.getValue(Product.class);
            products.add(product);
        }
        return products;
    }


}
