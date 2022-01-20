package com.example.ghostchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ghostchat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}