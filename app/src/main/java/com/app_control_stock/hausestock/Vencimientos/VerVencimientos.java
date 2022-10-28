package com.app_control_stock.hausestock.Vencimientos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.R;

import java.util.ArrayList;

public class VerVencimientos extends AppCompatActivity {

    RecyclerView listaVencimientos;
    ArrayList<AdaptadorVencimientos> listaArrayVencimientos;
    RecyclerVencimiento adapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vencimientos);
        listaVencimientos = findViewById(R.id.recycler_vencimientos);
        listaVencimientos.setLayoutManager(new LinearLayoutManager(this));

        listaArrayVencimientos = new ArrayList<>();

        Estructura_BD bd = new Estructura_BD(VerVencimientos.this);
        adapter = new RecyclerVencimiento(bd.mostrarVencimientos());
        listaVencimientos.setAdapter(adapter);

    }
}

