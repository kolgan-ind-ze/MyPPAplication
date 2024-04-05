package com.example.myppaplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myppaplication.R;
import com.example.myppaplication.classes.Users;
import com.example.myppaplication.database.DataBaseManager;

import org.w3c.dom.Text;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Users> users;
    DataBaseManager dbManager;

    public UsersAdapter(List<Users> users, Context context, DataBaseManager dbManager){
        this.users = users;
        this.inflater = LayoutInflater.from(context);
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_users, parent, false);
        return new UsersAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        Users user = users.get(position);
        holder.userCompName.setText("Наименование компании: " + user.getCompanyName());
        holder.userCompINN.setText("ИНН: " + user.getCompanyInn());
        holder.userCompOGRN.setText("ОГРН: " + user.getCompanyOgrn());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                builder.setTitle("Выберите действие");
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbManager.openDb();
                        dbManager.deleteUser(user);
                        dbManager.closeDb();
                    }
                });
                builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userCompName, userCompINN, userCompOGRN;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userCompName = itemView.findViewById(R.id.userCompanyName);
            userCompINN = itemView.findViewById(R.id.userCompanyINN);
            userCompOGRN = itemView.findViewById(R.id.userCompanyOGRN);
        }
    }
}
