package com.example.project_oderapp.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_oderapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_introduction;
    TextView txt_openLogin, txt_openSingup;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app);
        InitView();
        img_introduction.animate().translationY(-3000).setDuration(1000).setStartDelay(4000).start();
       txt_openLogin.setOnClickListener(this::onClick);
       txt_openSingup.setOnClickListener(this::onClick);
    }
    private void InitView()
    {
        img_introduction = findViewById(R.id.image_introduction);
        txt_openLogin = findViewById(R.id.startup_txtlogin);
        txt_openSingup = findViewById(R.id.startapp_txt_signup);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.startup_txtlogin:
               Intent intent  = new Intent(StartActivity.this, LoginActivity.class);
               startActivity(intent);
               overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
               break;

            case R.id.startapp_txt_signup:
                Intent intent1=  new Intent(StartActivity.this, SignUpActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    @Override
    protected void onStart() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null)
        {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }
}