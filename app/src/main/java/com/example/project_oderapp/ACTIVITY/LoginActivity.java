package com.example.project_oderapp.ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project_oderapp.MainActivity;
import com.example.project_oderapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
EditText edtEmail, edtPassword;
TextView txtLogin,txtSignup;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        InitView();
        txtLogin.setOnClickListener(this::onClick);
        txtSignup.setOnClickListener(this::onClick);
    }
    private void InitView()
    {
        edtEmail = findViewById(R.id.login_edtEmail);
        edtPassword = findViewById(R.id.login_edtPassword);
        txtLogin = findViewById(R.id.login_txtLogin);
        txtSignup = findViewById(R.id.login_txtSignup);

        //
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void login(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_txtLogin:
            {
                String email = edtEmail.getText().toString().trim();
                String password= edtPassword.getText().toString().trim();
                login(email,password);
                break;
            }

            case R.id.login_txtSignup:
            {
                Intent intent1 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            }
        }
    }


}