package com.example.ghostchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ghostchat.databinding.ActivityPhoneNumberVerificationBinding;

public class PhoneNumberVerification extends AppCompatActivity {
    ActivityPhoneNumberVerificationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityPhoneNumberVerificationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

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