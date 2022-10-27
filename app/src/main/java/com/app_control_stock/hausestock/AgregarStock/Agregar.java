package com.app_control_stock.hausestock.AgregarStock;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.BaseDatos.Helper_BD;
import com.app_control_stock.hausestock.R;

public class Agregar extends Activity {

    Button botonAgregar;
    EditText articulo, fechaVencimiento, cantidad, unidadMedida, ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        botonAgregar = (Button) findViewById(R.id.button_agregar);

        articulo = (EditText) findViewById(R.id.articulos_stock) ;
        fechaVencimiento = (EditText) findViewById(R.id.fecha_vencimiento);
        cantidad = (EditText) findViewById(R.id.cantidad_articulos);
        unidadMedida = (EditText) findViewById(R.id.unidad_medida);
        ubicacion = (EditText) findViewById(R.id.ubicacion_articulos);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!articulo.getText().toString().equals("")  && !unidadMedida.getText().toString().equals("") && !ubicacion.getText().toString().equals("")) {

                    Estructura_BD db = new Estructura_BD(Agregar.this);
                    int i = Integer.parseInt(cantidad.getText().toString());
                    long id = db.insertarStock(articulo.getText().toString(), i, unidadMedida.getText().toString(), fechaVencimiento.getText().toString(), ubicacion.getText().toString());

                    if (id > 0) {
                        Toast.makeText(Agregar.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(Agregar.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Agregar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        articulo.setText("");
        cantidad.setText("");
        unidadMedida.setText("");
        fechaVencimiento.setText("");
        ubicacion.setText("");

    }
}

