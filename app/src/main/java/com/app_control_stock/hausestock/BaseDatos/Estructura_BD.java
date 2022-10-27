package com.app_control_stock.hausestock.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app_control_stock.hausestock.VerStocks.AdaptadorStock;

import java.util.ArrayList;

public class Estructura_BD extends Helper_BD{

    static Context context;
    // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
    public Estructura_BD(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarStock(String Articulos, int Cantidad, String UnidadMedida, String FechaVencimiento, String Ubicacion) {

        long id = 0;

        try {
            Helper_BD dbHelper = new Helper_BD(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Articulo", Articulos);
            values.put("Cantidad", Cantidad);
            values.put("UnidadMedida", UnidadMedida);
            values.put("FechaVencimiento", FechaVencimiento);
            values.put("Ubicacion", Ubicacion);

            id = db.insert(TABLE_STOCK, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    @NonNull
    public static ArrayList<AdaptadorStock> mostrarContactos() {
        ArrayList<AdaptadorStock> listaContactos = new ArrayList<>();
    try {
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        AdaptadorStock stock;
        Cursor cursorStock;

        cursorStock = db.rawQuery("SELECT * FROM " + TABLE_STOCK + " ORDER BY Articulo ASC", null);

        if (cursorStock.moveToFirst()) {
            do {
                stock = new AdaptadorStock();
                stock.setArticulo(cursorStock.getString(1));
                stock.setCantidad(cursorStock.getInt(2));
                stock.setUnidadMedida(cursorStock.getString(3));
                stock.setUbicacion(cursorStock.getString(5));
                listaContactos.add(stock);
            } while (cursorStock.moveToNext());
        }

        cursorStock.close();
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
        return listaContactos;
    }

    public AdaptadorStock verContacto(int id) {

        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        AdaptadorStock stock = null;
        Cursor cursorStock;

        cursorStock = db.rawQuery("SELECT * FROM " + TABLE_STOCK + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorStock.moveToFirst()) {
            stock = new AdaptadorStock();
            stock.setArticulo(cursorStock.getString(0));
            stock.setCantidad(cursorStock.getInt(1));
            stock.setUnidadMedida(cursorStock.getString(2));
            stock.setUbicacion(cursorStock.getString(3));
        }

        cursorStock.close();

        return stock;
    }

    public boolean editarContacto(int id, int Cantidad, String UnidadMedida, String FechaVencimiento, String Ubicacion) {

        boolean correcto = false;

        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_STOCK + " SET Articulo = '" + Cantidad + "', UnidadMedida = '" + UnidadMedida + "', FechaVencimiento = '" + FechaVencimiento + "', Ubicacion = '" + Ubicacion + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id) {

        boolean correcto = false;

        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_STOCK + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}

