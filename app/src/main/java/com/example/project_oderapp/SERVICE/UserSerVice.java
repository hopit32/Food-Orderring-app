package com.example.project_oderapp.SERVICE;

import android.net.wifi.rtt.CivicLocationKeys;

import androidx.annotation.NonNull;

import com.example.project_oderapp.MODEL.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserSerVice {
    public static User user;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    private static UserSerVice instance;
    public static UserSerVice getInstance()
    {
        if (instance ==null)
        {
            instance = new UserSerVice();
        }
        return instance;
    }
    public User getInfoUser()
    {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
           return user;
    }
}
