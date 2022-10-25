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

        Helper_BD helper = new Helper_BD(this);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Estructura_BD.TABLE_ID, (Integer) null);
                values.put(Estructura_BD.COLUMNA_ARTICULOS, articulo.getText().toString());
                values.put(Estructura_BD.COLUMNA_VENCIMIENTO, fechaVencimiento.getText().toString());
                values.put(Estructura_BD.COLUMNA_CANTIDAD, cantidad.getText().toString());
                values.put(Estructura_BD.COLUMNA_UNIDAD, unidadMedida.getText().toString());
                values.put(Estructura_BD.COLUMNA_UBICACION, ubicacion.getText().toString());



                long newRowId = db.insert(Estructura_BD.TABLE_NAME, null, values);
                Toast.makeText(Agregar.this, "Se agrego el articulo:" + Estructura_BD.TABLE_ID, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
