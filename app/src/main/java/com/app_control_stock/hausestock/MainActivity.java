package com.app_control_stock.hausestock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app_control_stock.hausestock.AgregarStock.Agregar;
import com.app_control_stock.hausestock.Lista.Lista;
import com.app_control_stock.hausestock.Vencimientos.VerVencimientos;
import com.app_control_stock.hausestock.VerStocks.VerStock;

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

    public void ejec_VerStock(View view){
        Intent intencion = new Intent(this, VerStock.class);
        startActivities(new Intent[]{intencion});
    }

    public void ejec_VerVencimientos(View view){
        Intent intencion = new Intent(this, VerVencimientos.class);
        startActivities(new Intent[]{intencion});
    }

}