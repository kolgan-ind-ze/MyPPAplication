package com.example.myppaplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myppaplication.classes.Users;
import com.example.myppaplication.database.DataBaseManager;
import com.example.myppaplication.databinding.FragmentAddClientBinding;

import java.util.List;

public class AddClientFragment extends Fragment {
    private FragmentAddClientBinding binding;
    DataBaseManager dbManager;
    Users newUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAddClientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dbManager = new DataBaseManager(getContext());
        binding.saveNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = binding.editLoginNewUser.getText().toString();
                String password = binding.editPasswordNewUser.getText().toString();
                String name = binding.editNameCompanyUser.getText().toString();
                String inn = binding.editInnUser.getText().toString();
                String ogrn = binding.editOgrnUser.getText().toString();
                if (login.equals("") || password.equals("") || name.equals("") ||
                inn.equals("") || ogrn.equals("")){
                    Toast.makeText(getContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try{
                        dbManager.openDb();
                        newUser = new Users();
                        newUser.setLogin(login);
                        newUser.setPassword(password);
                        newUser.setCompanyName(name);
                        newUser.setCompanyInn(inn);
                        newUser.setCompanyOgrn(ogrn);
                        newUser.setRole(0);
                        dbManager.addUser(newUser);
                        dbManager.closeDb();
                        Toast.makeText(getContext(), "Клиент сохранен", Toast.LENGTH_SHORT).show();
                    } catch (Exception exception){
                        Toast.makeText(getContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}