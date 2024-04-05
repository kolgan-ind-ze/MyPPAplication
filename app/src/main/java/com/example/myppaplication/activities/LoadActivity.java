package com.example.myppaplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myppaplication.R;
import com.example.myppaplication.classes.Users;
import com.example.myppaplication.database.DataBaseManager;

public class LoadActivity extends AppCompatActivity {
    DataBaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadActivity.this, LoginActivity.class));
                finish();
            }
        }, 1000);
        //addStartData();
    }

    public void addStartData(){
        dbManager = new DataBaseManager(this);
        dbManager.openDb();
        dbManager.addUser(new Users("admin", "admin", "host", "admin", "admin", 1));
        dbManager.addUser(new Users("userTest", "userTest", "userTest", "userTest", "userTest", 0));
        dbManager.closeDb();
    }
}