package com.app_control_stock.hausestock.Lista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.app_control_stock.hausestock.R;
import com.example.TinyDB_implementacion.TinyDB;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Lista extends Activity {

    private int posicion = 0;
    private ArrayList<ListaAdapter> compras;
    private ArrayAdapter<ListaAdapter> adaptador1;
    private ListView listView;
    private EditText editorTexto;
    private ArrayList<String> listaCompras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        compras = new ArrayList<>();

        ContextThemeWrapper themedContext = new ContextThemeWrapper(this, R.style.Theme_lista);
        adaptador1 = new ArrayAdapter<ListaAdapter>(themedContext, android.R.layout.simple_list_item_multiple_choice, compras) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(compras.get(position).getItem());
                textView.setBackgroundColor(getColor(R.color.marino));
                textView.setTextColor(getColor(R.color.purple_fondo));
                textView.setTextSize(18);
                textView.setTypeface(Typeface.SERIF,Typeface.BOLD);
                return view;
            }
        };

        listView = (ListView) findViewById(R.id.list1);
        listView.setAdapter(adaptador1);
        editorTexto = findViewById(R.id.text_imput_lista);

        editorTexto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == keyEvent.KEYCODE_ENTER) {
                    agregar(view);
                    return true;
                }
                return false;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                createCustomDialog(i).show();
                return false;
            }
        });
    }

  public void onPause(){
        super.onPause();
        TinyDB tinyDB = new TinyDB(this);
        tinyDB.putListString("listaCompras", listaCompras);
    }

   public void onResume(){
        super.onResume();
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        listaCompras = tinyDB.getListString("listaCompras");
        for(int i=0; i<listaCompras.size(); i++){
           compras.add(new ListaAdapter(i, listaCompras.get(i)));
       }
    }

    public void agregar(View v) {
        posicion++;
        compras.add(new ListaAdapter(posicion, editorTexto.getText().toString()));
        listaCompras.add(editorTexto.getText().toString());
        Toast.makeText(getApplicationContext(),"Comprar: " + editorTexto.getText().toString(),Toast.LENGTH_SHORT).show();
        editorTexto.setText("");
        adaptador1.notifyDataSetChanged();
    }

    public AlertDialog createCustomDialog(int i) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView tv;

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.alert_dialog_lista, null);

        tv = (TextView) v.findViewById(R.id.articulo_alert);
        tv.setText(listaCompras.get(i));
        builder.setView(inflater.inflate(R.layout.alert_dialog_lista, null));

        Button agregar = (Button) v.findViewById(R.id.agregar_alert);
        Button eliminar = (Button) v.findViewById(R.id.eliminar_alert);
        Button salir = (Button) v.findViewById(R.id.salir_alert);

        builder.setView(v);
        alertDialog = builder.create();
        agregar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Aceptar
                        alertDialog.dismiss();
                    }
                }
        );
        eliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        compras.remove(i);
                        listaCompras.remove(i);
                        adaptador1.notifyDataSetChanged();
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