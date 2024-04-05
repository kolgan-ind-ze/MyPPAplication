package com.example.myppaplication.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myppaplication.adapters.AllOrdersAdapter;
import com.example.myppaplication.classes.Orders;
import com.example.myppaplication.database.DataBaseManager;
import com.example.myppaplication.databinding.FragmentMainAdminBinding;

import java.util.List;

public class MainAdminFragment extends Fragment {
    private FragmentMainAdminBinding binding;
    DataBaseManager dbManager;
    List<Orders> ordersList;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMainAdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dbManager = new DataBaseManager(getContext());
        dbManager.openDb();
        ordersList = dbManager.getOrders();
        binding.recViewAllOrders.setVisibility(View.VISIBLE);
        AllOrdersAdapter allOrdersAdapter = new AllOrdersAdapter(ordersList,
                getContext(), dbManager);
        binding.recViewAllOrders.setAdapter(allOrdersAdapter);
        allOrdersAdapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}