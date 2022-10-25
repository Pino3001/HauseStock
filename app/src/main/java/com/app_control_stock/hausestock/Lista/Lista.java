package com.app_control_stock.hausestock.Lista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

        adaptador1 = new ArrayAdapter<ListaAdapter>(this, android.R.layout.simple_list_item_checked, compras) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(compras.get(position).getItem());
                textView.setBackgroundColor(getColor(R.color.marino));
                textView.setTextColor(getColor(R.color.purple_fondo));
                textView.setTextSize(22);
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
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Lista.this);
                dialogo1.setTitle("Atencion");
                dialogo1.setMessage("¿ Desea eliminar: " +listaCompras.get(i)+ "?");
                dialogo1.setCancelable(false);

                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        compras.remove(i);
                        listaCompras.remove(i);
                        adaptador1.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();
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
    }

  /* public void imprimoLista(View view){
        for (int i = 0; i < paraComprar.size(); i++) {
            listView = findViewById(R.id.list1);
            listView.setAdapter(adaptador1);
            editorTexto = findViewById(R.id.text_imput_lista);
            adaptador1.add(paraComprar.get(i));
            adaptador1.notifyDataSetChanged();

        }
   }*/
}

   /* public void onSaveInstanceState(Bundle bundle){
        bundle.putStringArrayList("compras", (ArrayList<String>) paraComprar);
        super.onSaveInstanceState(bundle);
    }

    public void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        paraComprar = bundle.getStringArrayList("compras");
        imprimoLista(null);
    }*/