package com.example.myppaplication.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myppaplication.R;
import com.example.myppaplication.databinding.ActivityMainAdminBinding;
import com.example.myppaplication.fragments.AddClientFragment;
import com.example.myppaplication.fragments.CreateOrderFragment;
import com.example.myppaplication.fragments.MainAdminFragment;
import com.example.myppaplication.fragments.ShowClientsFragment;
import com.example.myppaplication.fragments.UserOrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainAdminActivity extends AppCompatActivity {

    private ActivityMainAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainAdminFragment mainAdminFragment = new MainAdminFragment();

        ShowClientsFragment showClientsFragment = new ShowClientsFragment();

        AddClientFragment addClientFragment = new AddClientFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_admin, mainAdminFragment)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();

        binding.navView.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener()      {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.navigation_main_admin){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_admin, mainAdminFragment)
                                    .addToBackStack(null)
                                    .setReorderingAllowed(true)
                                    .commit();
                        }
                        if (item.getItemId() == R.id.navigation_clients){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_admin, showClientsFragment)
                                    .addToBackStack(null)
                                    .setReorderingAllowed(true)
                                    .commit();
                        }
                        if (item.getItemId() == R.id.navigation_add_client){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_admin, addClientFragment)
                                    .addToBackStack(null)
                                    .setReorderingAllowed(true)
                                    .commit();
                        }
                        return true;
                    }
                });
    }

}