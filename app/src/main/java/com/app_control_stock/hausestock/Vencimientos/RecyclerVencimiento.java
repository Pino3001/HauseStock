package com.app_control_stock.hausestock.Vencimientos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.R;

import java.util.ArrayList;

public class RecyclerVencimiento extends RecyclerView.Adapter<RecyclerVencimiento.VencimientoViewHolder> {

    ArrayList<AdaptadorVencimientos> listaVencimiento;

    public RecyclerVencimiento(ArrayList<AdaptadorVencimientos> listaVencimiento) {
        this.listaVencimiento = listaVencimiento;
    }

    @NonNull
    @Override
    public VencimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_vencimientos, null, false);
        return new VencimientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVencimiento.VencimientoViewHolder holder, int position) {
        holder.viewVencimiento.setText(listaVencimiento.get(position).getVencimiento());
    }

  /*  public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaArticulos.clear();
            listaArticulos.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<AdaptadorStock> collecion = listaArticulos.stream()
                        .filter(i -> i.getArticulo().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaArticulos.clear();
                listaArticulos.addAll(collecion);
            } else {
                for (AdaptadorStock ads : listaOriginal) {
                    if (ads.getArticulo().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaArticulos.add(ads);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/

    @Override
    public int getItemCount() {
        return listaVencimiento.size();
    }

    public class VencimientoViewHolder extends RecyclerView.ViewHolder {

        TextView viewArticulo, viewVencimiento;

        public VencimientoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewArticulo = itemView.findViewById(R.id.vencimiento_articulo);
            viewVencimiento= itemView.findViewById(R.id.view_vencimiento);
        }
    }
}
