package com.app_control_stock.hausestock;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app_control_stock.hausestock.AgregarStock.Agregar;
import com.app_control_stock.hausestock.Lista.Lista;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ejec_lista(View view){
        Intent intencion = new Intent(this, Lista.class);
        startActivities(new Intent[]{intencion});
    }

    public void ejec_agregar(View view){
        Intent intencion = new Intent(this, Agregar.class);
        startActivities(new Intent[]{intencion});
    }

}