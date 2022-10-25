package com.app_control_stock.hausestock.BaseDatos;

import android.provider.BaseColumns;

public class Estructura_BD {

    // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
    public Estructura_BD() {}


    public static final String TABLE_NAME = "ControlStock";
    public static final String TABLE_ID = "ControlStock";
    public static final String COLUMNA_ARTICULOS = "Articulo";
    public static final String COLUMNA_VENCIMIENTO = "FechaVencimiento";
    public static final String COLUMNA_CANTIDAD = "Cantidad";
    public static final String COLUMNA_UNIDAD = "UnidadMedida";
    public static final String COLUMNA_UBICACION = "Ubicacion";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura_BD.TABLE_NAME + " (" +
                    Estructura_BD.TABLE_ID + " INTEGER PRIMARY KEY AutoIncrement," +
                    Estructura_BD.COLUMNA_ARTICULOS + " TEXT," +
                    Estructura_BD.COLUMNA_VENCIMIENTO + " TEXT," +
                    Estructura_BD.COLUMNA_CANTIDAD + " TEXT," +
                    Estructura_BD.COLUMNA_UNIDAD + " TEXT," +
                    Estructura_BD.COLUMNA_UBICACION + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BD.TABLE_NAME;
}

