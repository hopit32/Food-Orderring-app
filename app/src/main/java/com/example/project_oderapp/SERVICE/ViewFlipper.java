package com.example.project_oderapp.SERVICE;

import android.hardware.lights.LightsManager;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.project_oderapp.MODEL.Linkbanner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFlipper {
    private  static DatabaseReference databaseReference;
   public List<Linkbanner> linkbannerList = new ArrayList<>();
    public List<Linkbanner> linkbannerList2 = new ArrayList<>();
    private static ViewFlipper instance;

    public static ViewFlipper getInstance()
    {
        if (instance==null)
        {
            instance = new ViewFlipper();
        }
        return instance;
    }
    public List<Linkbanner> getViewFlipperUrl()
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


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       return linkbannerList2;
    }

}
