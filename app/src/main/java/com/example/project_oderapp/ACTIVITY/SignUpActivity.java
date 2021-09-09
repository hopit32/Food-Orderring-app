package com.example.project_oderapp.ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_oderapp.MainActivity;
import com.example.project_oderapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txt_login, txt_signup;
    EditText edtEmail, edtPassword;
    FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        InitView();

      //  txt_login.setOnClickListener(this::onClick);
        txt_signup.setOnClickListener(this::onClick);
        txt_login.setOnClickListener(this::onClick);
    }
    private void InitView()
    {
        txt_login = findViewById(R.id.signup_txtLogin);
        txt_signup = findViewById(R.id.signup_txtSignup);
        edtEmail = findViewById(R.id.signup_edtEmail);
        edtPassword = findViewById(R.id.signup_edtPassword);

        //
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void finish() {
        super.finish();
    }
    private void SignUpwithEmail(String email,String password)
    {
         mAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful())
                         {
                             FirebaseUser mUser = mAuth.getCurrentUser();
                             String userId = mUser.getUid();
                             reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                             HashMap<String,String> hashMap = new HashMap<>();
                             hashMap.put("user_id",userId);
                             hashMap.put("user_username","");
                             hashMap.put("user_password",password);
                             hashMap.put("user_address","");
                             hashMap.put("user_numberphone","");
                             hashMap.put("user_address_getfood","");
                             hashMap.put("user_image","");
                             hashMap.put("user_email",email);

                             reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful())
                                     {
                                         Toast.makeText(getApplicationContext(), "Sign up Sucessfull", Toast.LENGTH_SHORT).show();
                                         Intent intent = new Intent(SignUpActivity.this,CompleteInfomation_Activity.class);
                                       //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                         overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                         startActivity(intent);
                                     }
                                 }
                             });



                         }
                     }
                 });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signup_txtSignup:
            {
                String user = edtEmail.getText().toString().trim();
                String password=  edtPassword.getText().toString().trim();
                SignUpwithEmail(user, password);
                break;
            }



            case R.id.signup_txtLogin:
            {
                Intent intent  = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            }
        }
    }
}