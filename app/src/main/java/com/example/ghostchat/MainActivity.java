package com.example.ghostchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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