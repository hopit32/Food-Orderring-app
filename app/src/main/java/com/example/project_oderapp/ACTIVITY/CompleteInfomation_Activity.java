package com.example.project_oderapp.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_oderapp.FRAGMENT.Bottom_upload_imageUser;
import com.example.project_oderapp.INTERFACE.SendLinkImage;
import com.example.project_oderapp.MainActivity;
import com.example.project_oderapp.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class CompleteInfomation_Activity extends AppCompatActivity implements View.OnClickListener{
    EditText edtName,edtHome, edtAddressShiping,edtPhone,edtAddress;
    ImageView imgEdit, img_Avatar;
    TextView txt_upload;
    StorageReference mStorage;
    FirebaseUser mUser;
    DatabaseReference databaseReference;
    String image_uri_upload = "abc";
    private static String getImageUri="xyz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_infomation_personal);
        InitView();

        imgEdit.setOnClickListener(this::onClick);
        txt_upload.setOnClickListener(this::onClick);
    }
    private void InitView()
    {
       edtName = findViewById(R.id.complete_edtName);
       edtHome = findViewById(R.id.complete_edtHome);
       edtAddressShiping= findViewById(R.id.complete_Shippingaddress);
       edtPhone = findViewById(R.id.complete_edtPhone);
       imgEdit = findViewById(R.id.complete_upload);
       img_Avatar = findViewById(R.id.complete_image);
       txt_upload = findViewById(R.id.complete_txtGetStart);

       //
        mStorage = FirebaseStorage.getInstance().getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
    }

    public String FileExtension(Uri imageURI)
    {
        ContentResolver cR=  getApplicationContext().getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cR.getType(imageURI));
    }

    public void Image_uri_UPload(Uri imageUri)
    {

        StorageReference storageReference = mStorage.child(System.currentTimeMillis()+"."+FileExtension(imageUri));
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> result= taskSnapshot.getStorage().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                              image_uri_upload = uri.toString();
                            }
                        });
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {

            }
        });

    }

    public void Upload_Information(String img)
    {
        String name= edtName.getText().toString();
        String address=edtHome.getText().toString();
        String add_shipping = edtAddressShiping.getText().toString();
        String phone = edtPhone.getText().toString();
        //
        HashMap  hashMap = new HashMap<>();
        hashMap.put("user_username",name);
        hashMap.put("user_address",address);
        hashMap.put("user_numberphone",phone);
        hashMap.put("user_address_getfood",add_shipping);
        hashMap.put("user_image",img);
        databaseReference.child(mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
              //  Toast.makeText(getApplicationContext(), "Successfull",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.complete_upload:
            {
                Bottom_upload_imageUser bottom_upload_imageUser = new Bottom_upload_imageUser(new SendLinkImage() {
                    @Override
                    public void send(Uri uri) {
                                        img_Avatar.setImageURI(uri);
                                        Image_uri_UPload(uri);
                    }
                });
                bottom_upload_imageUser.show(getSupportFragmentManager(),"Bottom Sheet");
                break;
            }
            case R.id.complete_txtGetStart:
            {
                Upload_Information(image_uri_upload);
                Intent intent = new Intent(CompleteInfomation_Activity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}