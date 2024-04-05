package com.example.myppaplication.database;

public class DataBaseConst {
    /** create database */
    public static final String DATA_BASE_NAME = "ucmr.db";

    /** create table users */
    public static final int DATA_BASE_VERSION = 1;
    public static final String USERS_TABLE_NAME = "Users";
    public static final String USER_ID = "id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_COMPANY_NAME = "companyName";
    public static final String USER_COMPANY_INN = "inn";
    public static final String USER_COMPANY_OGRN = "ogrn";
    public static final String USER_ROLE = "role";

    /** table orders */
    public static final String ORDERS_TABLE_NAME = "Orders";
    public static final String ORDER_ID = "id";
    public static final String ORDER_NAME = "name";
    public static final String ORDER_PRICE = "price";
    public static final String ORDER_SIZE = "size";
    public static final String ORDER_METAL = "metal";
    public static final String ORDER_COLOR = "color";
    public static final String ORDER_PLAN = "plan";
    public static final String ORDER_DATE = "date";
    public static final String ORDER_MAKE = "make";
    public static final String USERID_ORDER = "userId";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " +
            USERS_TABLE_NAME + " ( " + USER_ID + " INTEGER PRIMARY KEY, " + USER_LOGIN + " TEXT UNIQUE, " +
            USER_PASSWORD + " TEXT, " + USER_COMPANY_NAME + " TEXT, " + USER_COMPANY_INN + " TEXT UNIQUE, " +
            USER_COMPANY_OGRN + " TEXT UNIQUE, " + USER_ROLE + " INTEGER);";
    public static final String DELETE_TABLE_USERS = "DROP TABLE IF EXISTS " + USERS_TABLE_NAME;

    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE IF NOT EXISTS " +
            ORDERS_TABLE_NAME + " ( " + ORDER_ID + " INTEGER PRIMARY KEY, " +
            ORDER_NAME + " TEXT, " + ORDER_PRICE + " TEXT, " + ORDER_SIZE + " TEXT, " +
            ORDER_METAL + " TEXT, " + ORDER_COLOR + " TEXT, " + ORDER_PLAN + " BLOB, " +
            ORDER_DATE + " TEXT, " + ORDER_MAKE + " INTEGER, " + USERID_ORDER + " INTEGER, " +
            "FOREIGN KEY (" + USERID_ORDER + ") REFERENCES " + USERS_TABLE_NAME + "(" + USER_ID + "));";
    public static final String DELETE_TABLE_ORDERS = "DROP TABLE IF EXISTS " + ORDERS_TABLE_NAME;
}
