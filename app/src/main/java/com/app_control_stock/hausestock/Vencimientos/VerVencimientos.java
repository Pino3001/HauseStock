package com.app_control_stock.hausestock.Vencimientos;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.R;
import com.app_control_stock.hausestock.VerStocks.VerStock;

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
        RecyclerVencimiento.OnNoteListener onNoteListener = new RecyclerVencimiento.OnNoteListener() {
            @Override
            public void onNoteClick(int position) {
                createCustomDialog(position).show();
            }
        };
        listaArrayVencimientos = bd.mostrarVencimientos();
        adapter = new RecyclerVencimiento(listaArrayVencimientos, onNoteListener);
        listaVencimientos.setAdapter(adapter);

    }
    public AlertDialog createCustomDialog(int i) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView tv;
        Estructura_BD bd = new Estructura_BD(VerVencimientos.this);

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.alert_dialog_versctock, null);

        tv = (TextView) v.findViewById(R.id.articulo_alert);
        tv.setText(listaArrayVencimientos.get(i).getArticulo());
        builder.setView(inflater.inflate(R.layout.alert_dialog_versctock, null));

        Button editar = (Button) v.findViewById(R.id.editar_alert_stock);
        Button eliminar = (Button) v.findViewById(R.id.eliminar_alert_stock);
        Button salir = (Button) v.findViewById(R.id.salir_alert_stock);

        builder.setView(v);
        alertDialog = builder.create();
        editar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                }
        );
        eliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        bd.eliminarArticulo(listaArrayVencimientos.get(i).getId());
                        listaArrayVencimientos.remove(i);
                        adapter.notifyDataSetChanged();

                    }
                }
        );
        salir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                }
        );
        return alertDialog;
    }
}

