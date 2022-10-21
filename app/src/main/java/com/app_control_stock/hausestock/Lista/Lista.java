package com.app_control_stock.hausestock.Lista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.app_control_stock.hausestock.R;
import com.example.TinyDB_implementacion.TinyDB;

import java.util.ArrayList;

public class Lista extends Activity {

    private ArrayList<String> paraComprar;
    private ArrayAdapter<String> adaptador1;
    private ListView listView;
    private EditText editorTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        paraComprar = new ArrayList<>();
        adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, paraComprar) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(getColor(R.color.purple_fondo));
                textView.setTextSize(22);
                textView.setTypeface(Typeface.SERIF,Typeface.BOLD);
                return view;
            }
        };

        listView = findViewById(R.id.list1);
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
                dialogo1.setMessage("¿ Desea eliminar: " +paraComprar.get(i)+ "?");
                dialogo1.setCancelable(false);

                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        paraComprar.remove(posicion);
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

    /*public void onPause(){
        super.onPause();
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        tinyDB.putListString("compras", paraComprar);
    }

    public void onResume(){
        super.onResume();
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        paraComprar = tinyDB.getListString("compras");
        imprimoLista(null);
    }*/

    public void agregar(View v) {
        paraComprar.add(editorTexto.getText().toString());
        adaptador1.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Comprar: " + editorTexto.getText().toString(),Toast.LENGTH_SHORT).show();
        editorTexto.setText("");
    }
   /* public void imprimoLista(View view){
        for (int i = 0; i < paraComprar.size(); i++) {
            listView.setAdapter(adaptador1);
            editorTexto = findViewById(R.id.text_imput_lista);
            editorTexto.setText(i);
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