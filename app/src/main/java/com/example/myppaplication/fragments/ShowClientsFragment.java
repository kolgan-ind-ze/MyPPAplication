package com.example.myppaplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myppaplication.adapters.UsersAdapter;
import com.example.myppaplication.classes.Users;
import com.example.myppaplication.database.DataBaseManager;
import com.example.myppaplication.databinding.FragmentShowClientsBinding;

import java.util.List;

public class ShowClientsFragment extends Fragment {
    private FragmentShowClientsBinding binding;
    DataBaseManager dbManager;
    List<Users> usersList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShowClientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dbManager = new DataBaseManager(getContext());
        dbManager.openDb();
        usersList = dbManager.getUsers();
        if (usersList.isEmpty()){
            Toast.makeText(getContext(), "Клиенты отсутствуют", Toast.LENGTH_SHORT).show();
            binding.recViewAllUsers.setVisibility(View.GONE);
        }
        else{
            binding.recViewAllUsers.setVisibility(View.VISIBLE);
            UsersAdapter usersAdapter = new UsersAdapter(usersList,
                    getContext(), dbManager);
            binding.recViewAllUsers.setAdapter(usersAdapter);
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}