package com.example.myppaplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myppaplication.R;
import com.example.myppaplication.classes.Users;
import com.example.myppaplication.database.DataBaseManager;

public class LoginActivity extends AppCompatActivity {
    EditText login, pass;
    Button loginGo;
    DataBaseManager dbManager;
    Users user = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.editLoginUser);
        pass = findViewById(R.id.editPasswordUser);
        loginGo = findViewById(R.id.autorization);
        dbManager = new DataBaseManager(this);
        loginGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLogin = login.getText().toString();
                String strPassword = pass.getText().toString();
                if(login.getText().toString().equals("") ||
                        pass.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                else{
                    try{
                        dbManager.openDb();
                        user = dbManager.checkUser(strLogin, strPassword);
                        Intent intent;
                        if (user.getId() != 0 ){
                            if (user.getRole() == 1){
                                intent = new Intent(LoginActivity.this, MainAdminActivity.class);
                                intent.putExtra(Users.class.getSimpleName(), user);
                                startActivity(intent);
                            }
                            else{
                                intent = new Intent(LoginActivity.this, MainUserActivity.class);
                                intent.putExtra(Users.class.getSimpleName(), user);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Вы ввели неправильный логин или пароль",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception exception){
                        Toast.makeText(LoginActivity.this, "Вы ввели неправильный логин или пароль",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}