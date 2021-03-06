package com.example.ghostchat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ghostchat.databinding.ActivityPhoneNumberVerificationBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneNumberVerification extends AppCompatActivity {
    ActivityPhoneNumberVerificationBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPhoneNumberVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(PhoneNumberVerification.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        binding.continueBtnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneNumberVerification.this,OTPactivity.class);
                intent.putExtra("phonenumber",binding.inputNumberid.getText().toString());
                startActivity(intent);
            }
        });
    }
}