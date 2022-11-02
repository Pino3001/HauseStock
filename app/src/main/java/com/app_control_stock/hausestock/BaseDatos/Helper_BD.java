package com.app_control_stock.hausestock.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper_BD extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "StockHogar.db";
    public static final String TABLE_STOCK = "t_stock";

    public Helper_BD(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("CREATE TABLE " + TABLE_STOCK + "(" +
               "id INTEGER PRIMARY KEY AUTOINCREMENT," +
               "Articulo TEXT NOT NULL," +
               "Cantidad int NOT NULL," +
               "UnidadMedida TEXT NOT NULL," +
               "FechaVencimiento TEXT," +
               "Ubicacion TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_STOCK);
        onCreate(db);
    }
}