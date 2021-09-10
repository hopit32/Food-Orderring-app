package com.example.project_oderapp.ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.project_oderapp.ACTIVITY.CartActivity;
import com.example.project_oderapp.ACTIVITY.StartActivity;
import com.example.project_oderapp.FRAGMENT.Card_Fragment;
import com.example.project_oderapp.FRAGMENT.Favorite_Faragment;
import com.example.project_oderapp.FRAGMENT.Home_Fragment;
import com.example.project_oderapp.FRAGMENT.List_Fragment;
import com.example.project_oderapp.FRAGMENT.Personal_Fragment;
import com.example.project_oderapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ImageView icon_home, icon_favorite, icon_list, icon_me;
    ImageView icon_home_2, icon_favorite_2, icon_list_2, icon_me_2;
    FrameLayout container;
    FloatingActionButton icon_cart;
    BottomAppBar bottomAppBar;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        InitBackground();

        icon_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.menu_icon_home:
                    Transitsion_fragment(new Home_Fragment());
                    break;
                case R.id.menu_icon_favotite:
                    Transitsion_fragment(new Favorite_Faragment());
                    break;
                case R.id.menu_icon_list:
                    Transitsion_fragment(new List_Fragment());
                    break;
                case R.id.menu_icon_me:
                    Transitsion_fragment(new Personal_Fragment());
                    break;

            }
            return true;
        }
    };

    public void Transitsion_fragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }
    public void InitBackground()
    {
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getFragmentManager().beginTransaction().add(R.id.main_container, new Home_Fragment()).commit();

    }
    public void InitView()
    {
        container = findViewById(R.id.main_container);
        bottomAppBar = findViewById(R.id.app_bar);
        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        icon_cart = findViewById(R.id.icon_cart);


    }


    @Override
    protected void onStart() {
        super.onStart();
    }

}