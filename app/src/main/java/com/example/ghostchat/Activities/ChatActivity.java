package com.example.ghostchat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ghostchat.Adapters.MessagesAdapter;
import com.example.ghostchat.Models.Message;
import com.example.ghostchat.R;
import com.example.ghostchat.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    MessagesAdapter adapter;
    ArrayList<Message> messages;
    String senderRoom,receiverRoom;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        messages=  new ArrayList<>();
        adapter = new MessagesAdapter(this,messages);

        String name = getIntent().getStringExtra("name");
        String receiverUid = getIntent().getStringExtra("uid");
        String senderUid= FirebaseAuth.getInstance().getUid();
        database=FirebaseDatabase.getInstance();

        senderRoom=  senderUid+receiverUid;
        receiverRoom=  receiverUid+senderUid;

        binding.sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageTxt = binding.inputTxt.getText().toString();

                Date date = new Date();
                Message message = new Message(messageTxt,senderUid,date.getTime());
                binding.inputTxt.setText("");

                database.getReference().child("chats")
                        .child(senderRoom)
                        .child("messages")
                        .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        database.getReference().child("chats")
                                .child(receiverRoom)
                                .child("messages")
                                .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
                    }
                });
            }
        });

        getSupportActionBar().setTitle(name);
        getSupportActionBar(). setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}