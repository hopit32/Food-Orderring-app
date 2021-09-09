package com.example.project_oderapp.FRAGMENT;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.ACTIVITY.SearchFoodActivity;
import com.example.project_oderapp.ACTIVITY.StartActivity;
import com.example.project_oderapp.ADAPTER.HomeCategory_Adapter;
import com.example.project_oderapp.ADAPTER.HomeProduct_Adapter;
import com.example.project_oderapp.ADAPTER.Restaurant_Adapter;
import com.example.project_oderapp.MANAGE.ManageCard;
import com.example.project_oderapp.MODEL.Category;
import com.example.project_oderapp.MODEL.Linkbanner;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.MODEL.Restaurant;
import com.example.project_oderapp.MODEL.User;
import com.example.project_oderapp.MainActivity;
import com.example.project_oderapp.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {
    TextView txt_useraddress, txt_username;
    EditText editText;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    List<Linkbanner> listbanner;
    ViewFlipper viewFlipper;
    ProgressBar progressBar;
    RelativeLayout layout_progressBar;
    List<Category> categoryList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    ArrayList<Product> product_inCart = new ArrayList<>();
    List<Restaurant>restaurantList = new ArrayList<>();
    ManageCard manageCard;
    RecyclerView recyclerView_Category, recyclerView_Product, recyclerView_Restaurant;
    Thread thread, thread2,thread_getInfo;
    private  static DatabaseReference databaseReference;
    private static List<Linkbanner> linkbannerList = new ArrayList<>();
    String IDUSER;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.main_menu,container,false);
        setHasOptionsMenu(true);

        toolbar = view.findViewById(R.id.toolbar_home);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        listbanner = new ArrayList<>();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //
        manageCard  = new ManageCard(getContext().getApplicationContext());
        txt_username= view.findViewById(R.id.home_username);
        txt_useraddress = view.findViewById(R.id.home_useraddress);
        editText = view.findViewById(R.id.home_searchView);
        layout_progressBar = view.findViewById(R.id.layout_progressBar);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        appBarLayout = view.findViewById(R.id.appBarLayout_home);
        recyclerView_Category = view.findViewById(R.id.home_recyclerView_category);
        recyclerView_Product = view.findViewById(R.id.home_recycleriew_mainfood);
        recyclerView_Restaurant=  view.findViewById(R.id.home_recycleriew_restaurant);
        appBarLayout.setOutlineProvider(null);
        //mapping
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SearchFoodActivity.class);
                startActivity(intent);
            }
        });


         GetInfoUser();
         setup_Banner();
         setCategory();
         setProduct();
         GetCart();
         setRestaurant();


        return view;
    }

    public void setup_Banner()
    {

        databaseReference = FirebaseDatabase.getInstance().getReference("Banner");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Linkbanner linkbanner = dataSnapshot.getValue(Linkbanner.class);
                    linkbannerList.add(linkbanner);
                }
                setUri(linkbannerList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setUri(List<Linkbanner> linkURi)
    {
            for (int x=0;x<linkURi.size();x++)
            {
                ImageView imageView = new ImageView(getView().getContext());
                Picasso.with(getView().getContext()).load(linkURi.get(x).getLink()).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewFlipper.addView(imageView);

            }

            viewFlipper.startFlipping();
            viewFlipper.setFlipInterval(5000);
            viewFlipper.setAutoStart(true);
            layout_progressBar.setVisibility(View.GONE);
            viewFlipper.setVisibility(View.VISIBLE);
        Animation anim_slide_in_right = AnimationUtils.loadAnimation(getView().getContext(),R.anim.slide_in_right);
        Animation anim_slide_out_left = AnimationUtils.loadAnimation(getView().getContext(), R.anim.slide_out_left);
        viewFlipper.setInAnimation(anim_slide_in_right);
        viewFlipper.setOutAnimation(anim_slide_out_left);
    }

   public void setCategory()
    {
         thread = new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference = FirebaseDatabase.getInstance().getReference("Category");
                databaseReference.addValueEventListener(new ValueEventListener() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            Category category =dataSnapshot.getValue(Category.class);
                            categoryList.add(category);
                        }
                        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                        recyclerView_Category.setLayoutManager(linearLayoutManager);
                        recyclerView_Category.setAdapter(new HomeCategory_Adapter(getContext(),categoryList));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        thread.start();
    }
    public void setProduct()
    {
                DatabaseReference databaseReference1;
                databaseReference1 = FirebaseDatabase.getInstance().getReference("Product");
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            Product product = dataSnapshot.getValue(Product.class);
                            productList.add(product);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        // GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2 );
                        recyclerView_Product.setLayoutManager(linearLayoutManager);
                        recyclerView_Product.setAdapter(new HomeProduct_Adapter(getContext(),productList));
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
   public void setRestaurant() {
        thread2 = new
                Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseReference databaseReference2;
                databaseReference2 = FirebaseDatabase.getInstance().getReference("Restaurant");
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                            restaurantList.add(restaurant);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        // GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2 );
                        recyclerView_Restaurant.setLayoutManager(linearLayoutManager);
                        recyclerView_Restaurant.setAdapter(new Restaurant_Adapter(getContext(), restaurantList));
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        thread2.start();




   }
    private void GetInfoUser()
    {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        IDUSER = firebaseUser.getUid();
        thread_getInfo = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(IDUSER);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        txt_username.post(new Runnable() {
                            @Override
                            public void run() {
                                txt_username.setText("Hi "+user.getUser_username()+" !");
                                txt_useraddress.setText(user.getUser_address_getfood());
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        thread_getInfo.start();


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void UpdateCart()
    {
        ArrayList<Product> products = manageCard.getListCart();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(IDUSER).child("cart");
        databaseReference.setValue(products);

        manageCard.Clear();

    }
    private void GetCart()
    {
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(IDUSER).child("cart");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Product product = dataSnapshot.getValue(Product.class);
                    product_inCart.add(product);
                }
                manageCard.UpdateCart(product_inCart);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.log_out_item1:
                UpdateCart();
                FirebaseAuth.getInstance().signOut();
                Intent intent =  new Intent(getActivity().getApplicationContext(), StartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        thread.interrupt();
        thread2.interrupt();
        Log.d("Home","OnDestroy");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d("Home","ONDETACH");
        super.onDetach();
    }

    @Override
    public void onStart() {
        Log.d("Home","START");
        super.onStart();
    }
}
