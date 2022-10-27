package com.app_control_stock.hausestock.VerStocks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.R;

import java.util.ArrayList;

public class VerStock extends AppCompatActivity {

    RecyclerView listaArticulos;
    ArrayList<AdaptadorStock> listaArrayArticulos;
    RecyclerAdapterStock adapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verstock);
        listaArticulos = findViewById(R.id.recycler_view);
        listaArticulos.setLayoutManager(new LinearLayoutManager(this));

        listaArrayArticulos = new ArrayList<>();

        Estructura_BD bd = new Estructura_BD(VerStock.this);
        adapter = new RecyclerAdapterStock(bd.mostrarStock());
        listaArticulos.setAdapter(adapter);

    }
}
