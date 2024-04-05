package com.example.myppaplication.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myppaplication.classes.Orders;
import com.example.myppaplication.database.DataBaseManager;
import com.example.myppaplication.databinding.ActivityUpdateOrderBinding;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateOrderActivity extends AppCompatActivity {
    private ActivityUpdateOrderBinding binding;
    DataBaseManager dbManager;
    private ActivityResultLauncher<String> getContent;
    final Bitmap[] selectedImage = new Bitmap[1];
    Orders order;
    Orders editOrder = new Orders();
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbManager = new DataBaseManager(this);
        dbManager.openDb();
        Intent intent = getIntent();
        order = (Orders) intent.getSerializableExtra("order");
        editOrder = dbManager.getOrderForUser(order.getId());

        binding.editNameEditOrder.setText(editOrder.getName());
        binding.editSizeEditOrder.setText(editOrder.getSize());
        binding.editMetalEditOrder.setText(editOrder.getMetal());
        binding.editColorEditOrder.setText(editOrder.getColor());


        binding.addPlanEditOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContent.launch("image/*");
            }
        });

        int orderId = editOrder.getId(); // Предполагается, что у заказа есть метод getId() для получения идентификатора
        byte[] imageData = dbManager.getImageOrder(orderId);

        if (imageData != null) {
            selectedImage[0] = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            // Отобразить изображение на экране, если необходимо
            // imageView.setImageBitmap(selectedImage[0]);
        }

        getContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(result);
                    selectedImage[0] = BitmapFactory.decodeStream(inputStream);
                    Toast.makeText(UpdateOrderActivity.this, "Изображение загружено!", Toast.LENGTH_SHORT).show();


                } catch (Exception exception){
                    Toast.makeText(UpdateOrderActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.updateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editNameEditOrder.getText().toString();
                String size = binding.editSizeEditOrder.getText().toString();
                String metal = binding.editMetalEditOrder.getText().toString();
                String color = binding.editColorEditOrder.getText().toString();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImage[0].compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                if (name.equals("") || size.equals("") || metal.equals("") || color.equals("") || byteArray.length == 0){
                    Toast.makeText(UpdateOrderActivity.this, "Заполните все поля ли выберите изображение!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try{
                        dbManager.openDb();
                        editOrder.setName(name);
                        editOrder.setPrice(editOrder.getPrice());
                        editOrder.setSize(size);
                        editOrder.setMetal(metal);
                        editOrder.setColor(color);
                        editOrder.setDate(editOrder.getDate());
                        editOrder.setMake(0);
                        editOrder.setUserId(editOrder.getUserId());
                        dbManager.updateOrder(editOrder, byteArray);
                        dbManager.closeDb();
                        Toast.makeText(UpdateOrderActivity.this, "Заявка сохранена!", Toast.LENGTH_SHORT).show();
                    } catch (Exception exception){
                        Log.d("SAVE", exception.toString());
                        Toast.makeText(UpdateOrderActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDb();
    }
}