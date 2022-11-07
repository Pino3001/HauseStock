package com.app_control_stock.hausestock.AgregarStock;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.R;

public class Agregar extends Activity {

    Button botonAgregar;
    EditText articulo, fechaVencimiento, cantidad, ubicacion;
    Spinner spinner;
    String unidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        botonAgregar = (Button) findViewById(R.id.button_agregar);

        articulo = (EditText) findViewById(R.id.articulos_stock) ;
        fechaVencimiento = (EditText) findViewById(R.id.fecha_vencimiento);
        cantidad = (EditText) findViewById(R.id.cantidad_articulos);
        ubicacion = (EditText) findViewById(R.id.ubicacion_articulos);
        spinner = (Spinner) findViewById(R.id.unidad_medida_spiner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unidad_medida, R.layout.spiner_unidades_medida);
        adapter.setDropDownViewResource(R.layout.spiner_unidades_medida);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
              unidad = (String) adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
        spinner.setAdapter(adapter);

        ubicacion.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_GO){
                    agrega_stock(v);
                    return true;
                }
                return false;
            }
        });
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agrega_stock(view);
            }
        });

    }

    public void agrega_stock(View view) {
        int i = 1;
        if(!articulo.getText().toString().equals("")  && !ubicacion.getText().toString().equals("")) {
            Estructura_BD db = new Estructura_BD(Agregar.this);
            i = Integer.parseInt(cantidad.getText().toString());
            long id = db.insertStock(articulo.getText().toString(), i, unidad, fechaVencimiento.getText().toString(), ubicacion.getText().toString());
            if (id > 0) {
                Toast.makeText(Agregar.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                limpiar();
            } else {
                Toast.makeText(Agregar.this, "Error al guardar el registro", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(Agregar.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar() {
        articulo.setText("");
        cantidad.setText("");
        fechaVencimiento.setText("");
        ubicacion.setText("");

    }
}

