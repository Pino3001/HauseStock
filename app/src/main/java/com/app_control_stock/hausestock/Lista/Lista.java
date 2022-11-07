package com.app_control_stock.hausestock.Lista;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Lista extends Activity {

    RecyclerView listaArticulos;
    RecyclerLista adapter;
    ArrayList<ListaAdapter> compras;
    private EditText editorTexto;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaArticulos = findViewById(R.id.lista_recycler);
        listaArticulos.setLayoutManager(new LinearLayoutManager(this));
        compras = new ArrayList<>();
        Estructura_BD bd = new Estructura_BD(Lista.this);
        compras = bd.MostrarLista();

        RecyclerLista.OnNoteListener OnNoteListener = new RecyclerLista.OnNoteListener() {
            @Override
            public void onNoteClick(int position) {
                bd.eliminar_de_lista(compras.get(position).getId());
                compras.remove(position);
                adapter.notifyDataSetChanged();
            }
        };

        RecyclerLista.OnNoteListener OnNoteListener2 = new RecyclerLista.OnNoteListener() {
            @Override
            public void onNoteClick(int position) {
                boolean nuevo =!compras.get(position).isCkick();
                bd.editarCheck(compras.get(position).getId(),nuevo );
                compras.get(position).setCkick(nuevo);
                adapter.notifyDataSetChanged();
            }
        };
        adapter = new RecyclerLista(compras, OnNoteListener,OnNoteListener2);
        listaArticulos.setAdapter(adapter);



        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editorTexto = findViewById(R.id.text_imput_lista);

                if(editorTexto.getVisibility() == v.VISIBLE){ //si es Visible lo pones Gone
                    editorTexto.setVisibility(v.GONE);
                    editorTexto.setFocusable(true);
                }else{ // si no es Visible, lo pones
                    editorTexto.setVisibility(v.VISIBLE);
                }

                editorTexto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if(actionId == EditorInfo.IME_ACTION_GO){
                            agregar(v);
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

    }

    public void agregar(View v) {
         if(!editorTexto.getText().toString().equals("") ) {
             Estructura_BD db = new Estructura_BD(Lista.this);
             int i = (int) db.insertLista(editorTexto.getText().toString(), false);
             Toast.makeText(getApplicationContext(),"Comprar: " + editorTexto.getText().toString(),Toast.LENGTH_SHORT).show();
             compras.add(new ListaAdapter(i, editorTexto.getText().toString(), false ));
             editorTexto.setText("");
             adapter.notifyDataSetChanged();
         }
    }
}