package com.example.myppaplication.fragments;

import static com.example.myppaplication.databinding.FragmentMainAdminBinding.inflate;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myppaplication.R;
import com.example.myppaplication.adapters.UserOrdersAdapter;
import com.example.myppaplication.classes.Orders;
import com.example.myppaplication.database.DataBaseManager;
import com.example.myppaplication.databinding.FragmentUserOrdersBinding;

import java.util.List;

public class UserOrdersFragment extends Fragment {
    private FragmentUserOrdersBinding binding;
    DataBaseManager dbManager;
    List<Orders> ordersList;
    int userId = 0;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userId = getArguments().getInt("keyUser", 0);
        dbManager = new DataBaseManager(getContext());
        dbManager.openDb();
        ordersList = dbManager.getOrderUser(userId);
        UserOrdersAdapter userOrdersAdapter = new UserOrdersAdapter(ordersList,
                getContext(), dbManager);
        binding.recViewUserOrders.setAdapter(userOrdersAdapter);
        userOrdersAdapter.notifyDataSetChanged();
        return root;
    }
}