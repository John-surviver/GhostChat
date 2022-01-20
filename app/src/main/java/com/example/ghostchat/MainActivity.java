package com.example.ghostchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ghostchat.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseDatabase database;
    ArrayList<User> users;
    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        users = new ArrayList<>();
        usersAdapter= new UsersAdapter(this,users);
        binding.recyclerviewid.setAdapter(usersAdapter);


        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // users.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    User user = snapshot1.getValue(User.class);
                    users.add(user);
                }
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.searchid:
                Toast.makeText(MainActivity.this, "search clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.groupId:
                Toast.makeText(MainActivity.this, "group clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inviteid:
                Toast.makeText(MainActivity.this, "invite clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settingsid:
                Toast.makeText(MainActivity.this, "settings clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logoutid:
                Toast.makeText(MainActivity.this, "logout clicked", Toast.LENGTH_SHORT).show();
                break;



        }

        return super.onOptionsItemSelected(item);

    }
}