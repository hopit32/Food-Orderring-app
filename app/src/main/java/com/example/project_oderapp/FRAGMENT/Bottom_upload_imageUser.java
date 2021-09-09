package com.example.project_oderapp.FRAGMENT;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_oderapp.INTERFACE.SendLinkImage;
import com.example.project_oderapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_upload_imageUser extends BottomSheetDialogFragment implements View.OnClickListener {

    TextView txt_uploadFromCamera;
    TextView txt_uploadFromGallery;
    SendLinkImage sendLinkImage;

public Bottom_upload_imageUser(SendLinkImage sendLinkImage)
{
    this.sendLinkImage = sendLinkImage;
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_uploadimg_layout, container,false);
        //Mapping
        txt_uploadFromCamera = view.findViewById(R.id.txt_upload_profile);
        txt_uploadFromGallery= view.findViewById(R.id.txt_gallery_profile);

        txt_uploadFromGallery.setOnClickListener(this::onClick);

        return view;

    }

    @Override
    public void onClick(View view) {
         switch (view.getId())
         {
             case R.id.txt_gallery_profile:
                 Intent intent =  new Intent();
                 intent.setType("image/*");
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(intent,1);

         }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode==RESULT_OK && data!=null)
        {
            Uri imageURI= data.getData();
            sendLinkImage.send(imageURI);
        }

    }
}
