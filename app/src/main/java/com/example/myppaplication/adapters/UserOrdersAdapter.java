package com.example.myppaplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myppaplication.R;
import com.example.myppaplication.activities.ShowInfoOrderActivity;
import com.example.myppaplication.activities.UpdateOrderActivity;
import com.example.myppaplication.classes.Orders;
import com.example.myppaplication.classes.Users;
import com.example.myppaplication.database.DataBaseManager;

import java.util.List;

public class UserOrdersAdapter extends RecyclerView.Adapter<UserOrdersAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<Orders> orders;
    DataBaseManager dbManager;

    public UserOrdersAdapter(List<Orders> orders, Context context, DataBaseManager dbManager){
        this.orders = orders;
        this.inflater = LayoutInflater.from(context);
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public UserOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_user_orders, parent, false);
        return new UserOrdersAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserOrdersAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Orders order = orders.get(position);
        holder.name.setText("Название: " + order.getName());
        holder.date.setText("Дата: " + order.getDate());
        holder.price.setText("Цена: " + order.getPrice() + " ₽");
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editOrderintent = new Intent(view.getContext(), UpdateOrderActivity.class);
                editOrderintent.putExtra("order", order);
                Log.d("ASDASD", order.getId() + "");
                view.getContext().startActivity(editOrderintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, price;
        Button edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userNameRequest);
            date = itemView.findViewById(R.id.userDateRequest);
            price = itemView.findViewById(R.id.userRequestPrice);
            edit = itemView.findViewById(R.id.buttonShowUserUpdate);
        }
    }
}
