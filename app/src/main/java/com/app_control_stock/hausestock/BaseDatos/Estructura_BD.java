package com.app_control_stock.hausestock.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;

import com.app_control_stock.hausestock.Lista.ListaAdapter;
import com.app_control_stock.hausestock.Vencimientos.AdaptadorVencimientos;
import com.app_control_stock.hausestock.VerStocks.AdaptadorStock;

import java.util.ArrayList;

public class Estructura_BD extends Helper_BD{

    static Context context;

    public Estructura_BD(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    // Estructura de tablas Stock
    public long insertStock(String Articulos, int Cantidad, String UnidadMedida, String FechaVencimiento, String Ubicacion) {
        long id = 0;
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Articulo", Articulos);
        values.put("Cantidad", Cantidad);
        values.put("UnidadMedida", UnidadMedida);
        values.put("FechaVencimiento", FechaVencimiento);
        values.put("Ubicacion", Ubicacion);
        id = db.insert(TABLE_STOCK, null, values);

        return id;
    }

    @NonNull
    public static ArrayList<AdaptadorStock> mostrarStock() {
        ArrayList<AdaptadorStock> listaStock = new ArrayList<>();
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursorStock;

        try {
            cursorStock = db.rawQuery("SELECT * FROM " + TABLE_STOCK + " ORDER BY Articulo ASC", null);

            if (cursorStock.moveToFirst()) {
                do {
                    AdaptadorStock stock = new AdaptadorStock();
                    stock.setId(cursorStock.getInt(0));
                    stock.setArticulo(cursorStock.getString(1));
                    stock.setCantidad(cursorStock.getInt(2));
                    stock.setUnidadMedida(cursorStock.getString(3));
                    stock.setUbicacion(cursorStock.getString(5));
                    listaStock.add(stock);
                } while (cursorStock.moveToNext());
            }
        }finally {
            db.close();
        }

        cursorStock.close();
        return listaStock;
    }

    @NonNull
    public static ArrayList<AdaptadorVencimientos> mostrarVencimientos() {
        ArrayList<AdaptadorVencimientos> listaVencimientos = new ArrayList<>();
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        AdaptadorVencimientos vencimientos;
        Cursor cursorVencimiento;
        try {
            cursorVencimiento = db.rawQuery("SELECT * FROM " + TABLE_STOCK + " ORDER BY Articulo ASC", null);

            if (cursorVencimiento.moveToFirst()) {
                do {
                    vencimientos = new AdaptadorVencimientos();
                    vencimientos.setArticulo(cursorVencimiento.getString(1));
                    vencimientos.setVencimiento(cursorVencimiento.getString(4));
                    listaVencimientos.add(vencimientos);
                } while (cursorVencimiento.moveToNext());
                cursorVencimiento.close();
            }
        }catch (Exception ex) {
            ex.toString();
        }
        finally {
            db.close();
        }
        return listaVencimientos;
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

    public boolean eliminarArticulo(int id) {
        boolean correcto = false;
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_STOCK + " WHERE id = " + id );
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    //Estructuras de tablas Lista
    public long insertLista(String Item, boolean check) {
        long id;
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Item", Item);
        values.put("BOX", check? 1: 0);
        id = db.insert(TABLE_LISTA, null, values);

        return id;
    }

    public static ArrayList<ListaAdapter> MostrarLista() {
        ArrayList<ListaAdapter> arraylista = new ArrayList<>();
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursorLista;

        cursorLista = db.rawQuery("SELECT * FROM " + TABLE_LISTA + " ORDER BY id ASC", null);

        if (cursorLista.moveToFirst()) {
            do {
                ListaAdapter lista = new ListaAdapter(cursorLista.getInt(0),cursorLista.getString(1), cursorLista.getInt(2) == 1);
                arraylista.add(lista);
            } while (cursorLista.moveToNext());
        }

        cursorLista.close();
        return arraylista;
    }

    public boolean eliminar_de_lista(int id) {
        boolean correcto = false;
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_LISTA + " WHERE id = " + id + "");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean editarCheck(int id, boolean checke) {
        boolean correcto = false;
        Helper_BD dbHelper = new Helper_BD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        try {
            db.execSQL("UPDATE " + TABLE_LISTA + " SET Box = " + (checke?1:0) + " WHERE id=" + id );
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

