package com.app_control_stock.hausestock.Lista;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app_control_stock.hausestock.R;
import com.example.TinyDB_implementacion.TinyDB;

import java.util.ArrayList;

public class Lista extends Activity {

    public ArrayList<String> paraComprar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }

    public void onPause(){
        super.onPause();
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        tinyDB.putListString("compras", paraComprar);
    }

    public void onResume(){
        super.onResume();
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        paraComprar = tinyDB.getListString("compras");
        imprimoLista(null);
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

    public void imprimoLista(View view){

        for (int i=0; i<paraComprar.size(); i++) {
            LinearLayout listado = (LinearLayout) findViewById(R.id.lista_compras);
            LinearLayout listadoHorizontal = new LinearLayout(Lista.this);
            listadoHorizontal.setOrientation(LinearLayout.HORIZONTAL);

            TextView txt = new TextView(Lista.this);
            CheckBox icono = new CheckBox(Lista.this);

            listadoHorizontal.addView(icono);
            listadoHorizontal.addView(txt);

            txt.setText(paraComprar.get(i));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                txt.setTextColor(getColor(R.color.purple_fondo));
            }
            txt.setTextSize(18);
            txt.setPadding(50, 10, 10, 10);

            listado.addView(listadoHorizontal);
        }
    }

    public void agregarLista(View view) {

        EditText text = findViewById(R.id.text_imput_lista);
        String strValor = text.getText().toString();

        paraComprar.add(strValor);

        LinearLayout listado = (LinearLayout) findViewById(R.id.lista_compras);
        LinearLayout listadoHorizontal = new LinearLayout(this);
        listadoHorizontal.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox check = new CheckBox(this);
        TextView txt = new TextView(this);
        ImageButton delete = new ImageButton(this);

        txt.setText(strValor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            txt.setTextColor(getColor(R.color.purple_fondo));
        }
        txt.setTextSize(18);
        txt.setPadding(50, 10, 10, 10);

        listadoHorizontal.addView(check);
        listadoHorizontal.addView(txt);
        listadoHorizontal.addView(delete);
        listado.addView(listadoHorizontal);

        Toast.makeText(getApplicationContext(),"Comprar: " + strValor,Toast.LENGTH_SHORT).show();
        text.setText("");
    }
}
