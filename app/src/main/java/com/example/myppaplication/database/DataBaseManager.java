package com.example.myppaplication.database;

import static com.example.myppaplication.database.DataBaseConst.ORDERS_TABLE_NAME;
import static com.example.myppaplication.database.DataBaseConst.ORDER_COLOR;
import static com.example.myppaplication.database.DataBaseConst.ORDER_DATE;
import static com.example.myppaplication.database.DataBaseConst.ORDER_ID;
import static com.example.myppaplication.database.DataBaseConst.ORDER_MAKE;
import static com.example.myppaplication.database.DataBaseConst.ORDER_METAL;
import static com.example.myppaplication.database.DataBaseConst.ORDER_NAME;
import static com.example.myppaplication.database.DataBaseConst.ORDER_PLAN;
import static com.example.myppaplication.database.DataBaseConst.ORDER_PRICE;
import static com.example.myppaplication.database.DataBaseConst.ORDER_SIZE;
import static com.example.myppaplication.database.DataBaseConst.USERID_ORDER;
import static com.example.myppaplication.database.DataBaseConst.USERS_TABLE_NAME;
import static com.example.myppaplication.database.DataBaseConst.USER_COMPANY_INN;
import static com.example.myppaplication.database.DataBaseConst.USER_COMPANY_NAME;
import static com.example.myppaplication.database.DataBaseConst.USER_COMPANY_OGRN;
import static com.example.myppaplication.database.DataBaseConst.USER_ID;
import static com.example.myppaplication.database.DataBaseConst.USER_LOGIN;
import static com.example.myppaplication.database.DataBaseConst.USER_PASSWORD;
import static com.example.myppaplication.database.DataBaseConst.USER_ROLE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myppaplication.classes.Orders;
import com.example.myppaplication.classes.Users;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context){
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }

    public void openDb(){
        db = dbHelper.getReadableDatabase();
    }

    public void closeDb(){
        db.close();
    }

    @SuppressLint("Range")
    public Users checkUser(String login, String password){
        Users user = new Users();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME + " WHERE " + USER_LOGIN + " =  + '"
                + login + "'" + " AND " + USER_PASSWORD + " =  + '" + password + "'", null);
        if (cursor.moveToFirst()){
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
            user.setLogin(cursor.getString(cursor.getColumnIndex(USER_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
            user.setCompanyName(cursor.getString(cursor.getColumnIndex(USER_COMPANY_NAME)));
            user.setCompanyInn(cursor.getString(cursor.getColumnIndex(USER_COMPANY_INN)));
            user.setCompanyOgrn(cursor.getString(cursor.getColumnIndex(USER_COMPANY_OGRN)));
            user.setRole(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ROLE))));
        }
        cursor.close();
        return user;
    }

    @SuppressLint("Range")
    public List<Users> getUsers(){
        List<Users> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + USERS_TABLE_NAME + " WHERE " + USER_ROLE + " =  + '" + 0 +"'", null);
        while (cursor.moveToNext()){
            Users user = new Users();
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
            user.setLogin(cursor.getString(cursor.getColumnIndex(USER_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
            user.setCompanyName(cursor.getString(cursor.getColumnIndex(USER_COMPANY_NAME)));
            user.setCompanyInn(cursor.getString(cursor.getColumnIndex(USER_COMPANY_INN)));
            user.setCompanyOgrn(cursor.getString(cursor.getColumnIndex(USER_COMPANY_OGRN)));
            user.setRole(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ROLE))));
            users.add(user);
        }
        return users;
    }

    public void addUser(Users user){
        ContentValues cv = new ContentValues();
        cv.put(USER_LOGIN, user.getLogin());
        cv.put(USER_PASSWORD, user.getPassword());
        cv.put(USER_COMPANY_NAME, user.getCompanyName());
        cv.put(USER_COMPANY_INN, user.getCompanyInn());
        cv.put(USER_COMPANY_OGRN, user.getCompanyOgrn());
        cv.put(USER_ROLE, user.getRole());
        db.insert(USERS_TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Orders> getOrders(){
        List<Orders> orders = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT " + ORDER_ID + ", " + ORDER_NAME + ", " + ORDER_PRICE + ", " + ORDER_DATE + " FROM " + ORDERS_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Orders order = new Orders();
            order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_ID))));
            order.setName(cursor.getString(cursor.getColumnIndex(ORDER_NAME)));
            order.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_PRICE))));
            order.setDate(cursor.getString(cursor.getColumnIndex(ORDER_DATE)));
            orders.add(order);
        }
        return orders;
    }

    @SuppressLint("Range")
    public List<Orders> getOrderUser(int userId) {
        List<Orders> orders = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT " + ORDER_ID + ", " + ORDER_NAME + ", " + ORDER_PRICE + ", " + ORDER_DATE + " FROM " + ORDERS_TABLE_NAME +
                " WHERE " + USERID_ORDER + " =  + '" + userId +"'", null);
        while (cursor.moveToNext()) {
            Orders order = new Orders();
            order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_ID))));
            order.setName(cursor.getString(cursor.getColumnIndex(ORDER_NAME)));
            order.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_PRICE))));
            order.setDate(cursor.getString(cursor.getColumnIndex(ORDER_DATE)));
            orders.add(order);
        }
        cursor.close();
        return orders;
    }

    @SuppressLint("Range")
    public Orders getOrderForUser(int id) {
        Orders order = new Orders();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ORDERS_TABLE_NAME +
                " WHERE " + ORDER_ID + " = " + id, null);
        while (cursor.moveToNext()) {
            order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_ID))));
            order.setName(cursor.getString(cursor.getColumnIndex(ORDER_NAME)));
            order.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_PRICE))));
            order.setSize(cursor.getString(cursor.getColumnIndex(ORDER_SIZE)));
            order.setMetal(cursor.getString(cursor.getColumnIndex(ORDER_METAL)));
            order.setColor(cursor.getString(cursor.getColumnIndex(ORDER_COLOR)));
            order.setPlan(cursor.getBlob(cursor.getColumnIndex(ORDER_PLAN)));
            order.setDate(cursor.getString(cursor.getColumnIndex(ORDER_DATE)));
            order.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USERID_ORDER))));
        }
        cursor.close();
        return order;
    }

    @SuppressLint("Range")
    public byte[] getImageOrder(int orderId){
        byte[] imagedata = null;
        Cursor cursor = db.rawQuery("SELECT " + ORDER_PLAN + " FROM " +
                ORDERS_TABLE_NAME + " WHERE " + ORDER_ID + " = '" + orderId + "'", null);
        if (cursor.moveToFirst()){
            imagedata = cursor.getBlob(0);
        }
        cursor.close();
        return imagedata;
    }

    public void deleteOrder(Orders order){
        db.delete(ORDERS_TABLE_NAME, ORDER_ID + " = " + order.getId(), null);
    }

    public void deleteUser(Users user){
        db.delete(USERS_TABLE_NAME, USER_ID + " = " + user.getId(), null);
    }

    public void addOrder(Orders order, byte[] imageData){
        ContentValues cv = new ContentValues();
        cv.put(ORDER_NAME, order.getName());
        cv.put(ORDER_PRICE, order.getPrice());
        cv.put(ORDER_SIZE, order.getSize());
        cv.put(ORDER_METAL, order.getMetal());
        cv.put(ORDER_COLOR, order.getColor());
        cv.put(ORDER_PLAN, imageData);
        cv.put(ORDER_DATE, order.getDate());
        cv.put(ORDER_MAKE, order.getMake());
        cv.put(USERID_ORDER, order.getUserId());
        db.insert(ORDERS_TABLE_NAME, null, cv);
    }

    public void updateOrder(Orders order, byte[] imageData){
        ContentValues cv = new ContentValues();
        cv.put(ORDER_NAME, order.getName());
        cv.put(ORDER_PRICE, order.getPrice());
        cv.put(ORDER_SIZE, order.getSize());
        cv.put(ORDER_METAL, order.getMetal());
        cv.put(ORDER_COLOR, order.getColor());
        cv.put(ORDER_PLAN, imageData);
        cv.put(ORDER_DATE, order.getDate());
        cv.put(ORDER_MAKE, order.getMake());
        cv.put(USERID_ORDER, order.getUserId());
        db.update(ORDERS_TABLE_NAME, cv, ORDER_ID + " = " + order.getId(), null);
    }
}
