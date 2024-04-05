package com.example.myppaplication.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myppaplication.R;
import com.example.myppaplication.classes.Orders;
import com.example.myppaplication.classes.Users;
import com.example.myppaplication.database.DataBaseManager;
import com.example.myppaplication.databinding.FragmentAddClientBinding;
import com.example.myppaplication.databinding.FragmentCreateOrderBinding;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CreateOrderFragment extends Fragment {
    final Bitmap[] selectedImage = new Bitmap[1];
    private FragmentCreateOrderBinding binding;
    private ActivityResultLauncher<String> getContent;
    DataBaseManager dbManager;
    Random random = new Random();
    Orders newOrder;
    Calendar calendar = Calendar.getInstance();
    int userId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle args = getArguments();
        if (args != null){
            userId = getArguments().getInt("keyUser", 0);
        }

        dbManager = new DataBaseManager(getContext());

        binding.addPlanNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContent.launch("image/*");
            }
        });

        getContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                try {
                    InputStream inputStream = getContext().getContentResolver().openInputStream(result);
                    selectedImage[0] = BitmapFactory.decodeStream(inputStream);
                    Toast.makeText(getContext(), "Изображение загружено!", Toast.LENGTH_SHORT).show();


                } catch (Exception exception){
                    Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.addNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = 500000;
                int max = 1000000;
                int rndPrice = random.nextInt(max - min + 1) + min;
                int price = 450000 + rndPrice;
                Date resultDate = calendar.getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String resultDateAdd = dateFormat.format(resultDate);
                String name = binding.editNewNameOrder.getText().toString();
                String size = binding.editNewSizeOrder.getText().toString();
                String metal = binding.editNewMetalOrder.getText().toString();
                String color = binding.editNewColorOrder.getText().toString();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImage[0].compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                if (name.equals("") || size.equals("") || metal.equals("") || color.equals("") || byteArray.length == 0){
                    Toast.makeText(getContext(), "Заполните все поля или выберите изображение!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try{
                        dbManager.openDb();
                        newOrder = new Orders();
                        newOrder.setName(name);
                        newOrder.setPrice(price);
                        newOrder.setSize(size);
                        newOrder.setMetal(metal);
                        newOrder.setColor(color);
                        newOrder.setDate(resultDateAdd);
                        newOrder.setMake(0);
                        newOrder.setUserId(userId);
                        dbManager.addOrder(newOrder, byteArray);
                        dbManager.closeDb();
                        Toast.makeText(getContext(), "Заявка сохранена!", Toast.LENGTH_SHORT).show();
                    } catch (Exception exception){
                        Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return root;
    }
}