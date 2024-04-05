package com.example.myppaplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myppaplication.R;
import com.example.myppaplication.classes.Users;
import com.example.myppaplication.databinding.ActivityMainUserBinding;
import com.example.myppaplication.fragments.CreateOrderFragment;
import com.example.myppaplication.fragments.UserOrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainUserActivity extends AppCompatActivity {

    private ActivityMainUserBinding binding;
    Users user = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            user = (Users) arguments.getSerializable(Users.class.getSimpleName());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("keyUser", user.getId());

        UserOrdersFragment fragmentUserOrders = new UserOrdersFragment();
        fragmentUserOrders.setArguments(bundle);

        CreateOrderFragment fragmentCreateOrder = new CreateOrderFragment();
        fragmentCreateOrder.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentUserOrders)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();

        binding.navViewUser.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_user_orders){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragmentUserOrders)
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }
                if (item.getItemId() == R.id.navigation_create_order){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragmentCreateOrder)
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }
                return true;
            }
        });
    }
}