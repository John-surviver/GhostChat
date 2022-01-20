package com.example.ghostchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.ghostchat.databinding.ActivitySetupProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SetupProfileActivity extends AppCompatActivity {
    ActivitySetupProfileBinding binding;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri selectedImage;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySetupProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();


        getSupportActionBar().hide();


        binding.profilePicid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,48);
            }
        });

        binding.savebtnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(SetupProfileActivity.this);
                dialog.setMessage("Uploading..");
                dialog.setCancelable(false);
                dialog.show();
                String name = binding.inputNameid.getText().toString();

                if (name.isEmpty()){
                    binding.inputNameid.setError("Please Input your Name");
                    return;
                }
                if (selectedImage!=null){
                    StorageReference reference = storage.getReference().child("profiles").child(mAuth.getUid());
                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageUrl =uri.toString();

                                        String uid =mAuth.getUid();
                                        String phone =mAuth.getCurrentUser().getPhoneNumber();
                                        String name = binding.inputNameid.getText().toString();


                                        User user = new User(uid, name,phone,imageUrl);

                                        database.getReference()
                                                .child("users")
                                                .child("uid")
                                                .setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                dialog.dismiss();
                                                Intent intent = new Intent(SetupProfileActivity.this,MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });

                                    }
                                });

                            }
                        }
                    });
                }
                else {
                    String uid =mAuth.getUid();
                    String phone =mAuth.getCurrentUser().getPhoneNumber();
                    User user = new User(uid, name,phone,"No Image");

                    database.getReference()
                            .child("users")
                            .child("uid")
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    dialog.dismiss();
                                    Intent intent = new Intent(SetupProfileActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (data.getData()!= null){
                binding.profilePicid.setImageURI(data.getData());
                selectedImage=data.getData();
            }
        }
    }
}