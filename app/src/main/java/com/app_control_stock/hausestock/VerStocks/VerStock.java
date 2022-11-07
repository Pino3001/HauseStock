package com.app_control_stock.hausestock.VerStocks;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        RecyclerAdapterStock.OnNoteListener OnNoteListener = new RecyclerAdapterStock.OnNoteListener() {
            @Override
            public void onNoteClick(int position) {
                createCustomDialog(position).show();
            }
        };
        listaArrayArticulos = bd.mostrarStock();
        adapter = new RecyclerAdapterStock(listaArrayArticulos, OnNoteListener);
        listaArticulos.setAdapter(adapter);

    }
   public AlertDialog createCustomDialog( int i) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView tv;
        Estructura_BD bd = new Estructura_BD(VerStock.this);

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.alert_dialog_versctock, null);

        tv = (TextView) v.findViewById(R.id.articulo_alert);
        tv.setText(listaArrayArticulos.get(i).getArticulo());
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
                        bd.eliminarArticulo(listaArrayArticulos.get(i).getId());
                        listaArrayArticulos.remove(i);
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
