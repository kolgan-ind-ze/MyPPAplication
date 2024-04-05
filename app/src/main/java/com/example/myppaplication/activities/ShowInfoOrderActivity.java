package com.example.myppaplication.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myppaplication.R;
import com.example.myppaplication.classes.Orders;
import com.example.myppaplication.database.DataBaseManager;
import com.example.myppaplication.databinding.ActivityShowInfoOrderBinding;
import com.example.myppaplication.databinding.ActivityUpdateOrderBinding;

public class ShowInfoOrderActivity extends AppCompatActivity {
    private ActivityShowInfoOrderBinding binding;
    DataBaseManager dbManager;
    private ActivityResultLauncher<String> getContent;
    final Bitmap[] selectedImage = new Bitmap[1];
    Orders order, editOrder;
    Orders showOrder = new Orders();
    int id = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowInfoOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbManager = new DataBaseManager(this);
        dbManager.openDb();
        Intent intent = getIntent();
        order = (Orders) intent.getSerializableExtra("order");
        showOrder = dbManager.getOrderForUser(order.getId());

        binding.showName.setText(showOrder.getName());
        binding.showDescription.setText("Размеры: " + showOrder.getSize() + "\n" +
                "Исп.металл: " + showOrder.getMetal() + "\n" +
                "Цвет покраски: " + showOrder.getColor());

        byte[] imageData = dbManager.getImageOrder(showOrder.getId());
        if (imageData != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            binding.showPlan.setImageBitmap(bitmap);
        }

        binding.showDate.setText(showOrder.getDate());
        String originalString = showOrder.getMetal();
        StringBuilder resultString = new StringBuilder();

        for (int i = 0; i < originalString.length(); i++) {
            char currentChar = originalString.charAt(i);
            if (currentChar == ' ') {
                resultString.append(currentChar).append("\n");
            } else {
                resultString.append(currentChar);
            }
        }
        String formattedString = resultString.toString();
        binding.showMetal.setText(formattedString);
        binding.buttonGotoMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Отправлено", Toast.LENGTH_SHORT).show();
            }
        });
    }
}