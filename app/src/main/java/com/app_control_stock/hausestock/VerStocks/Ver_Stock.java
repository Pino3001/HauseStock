package com.app_control_stock.hausestock.VerStocks;

import static com.app_control_stock.hausestock.BaseDatos.Estructura_BD.TABLE_STOCK;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.BaseDatos.Helper_BD;
import com.app_control_stock.hausestock.R;

import java.util.ArrayList;

public class Ver_Stock extends AppCompatActivity {

    RecyclerView listaArticulos;
    ArrayList<AdaptadorStock> listaArrayArticulos;
    RecyclerAdapterStock adapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verstock);
        listaArticulos = findViewById(R.id.recycler_view);
        listaArticulos.setLayoutManager(new LinearLayoutManager(this));

        listaArrayArticulos = new ArrayList<>();

        Estructura_BD bd = new Estructura_BD(Ver_Stock.this);
        adapter = new RecyclerAdapterStock(bd.mostrarContactos());
        listaArticulos.setAdapter(adapter);




                /*new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Collections.singletonList(misArticulos)) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(misArticulos);
                textView.setBackgroundColor(getColor(R.color.marino));
                textView.setTextColor(getColor(R.color.purple_fondo));
                textView.setTextSize(22);
                textView.setTypeface(Typeface.SERIF,Typeface.BOLD);
                return view;
            }
        };
        articulos = (RecyclerView) findViewById(R.id.recycler_articulos);
        articulos.setAdapter(adaptArticulos);



// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                Estructura_BD.COLUMNA_ARTICULOS,
                Estructura_BD.COLUMNA_CANTIDAD,
                Estructura_BD.COLUMNA_UNIDAD,
                Estructura_BD.COLUMNA_UBICACION
        };

// Filter results WHERE "title" = 'My Title'
        String selection = Estructura_BD.COLUMNA_ARTICULOS + " = ? ";
        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Estructura_BD.COLUMNA_ARTICULOS + " DESC";

        Cursor cursor = db.query(
                Estructura_BD.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        cursor.moveToFirst();
        while(cursor.moveToNext()) {
            articulos.setText(cursor.getString(0));
            cantidad.setText(cursor.getString(1));
            unidadM.setText(cursor.getString(2));
            ubicacion.setText(cursor.getString(3));
        }
        cursor.close();*/

    }
}
