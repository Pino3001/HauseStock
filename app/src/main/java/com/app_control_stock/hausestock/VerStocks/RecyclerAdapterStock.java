package com.app_control_stock.hausestock.VerStocks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.R;

import java.util.ArrayList;

public class RecyclerAdapterStock extends RecyclerView.Adapter<RecyclerAdapterStock.StockViewHolder> {

    ArrayList<AdaptadorStock> listaArticulos;
    private OnNoteListener onNoteListener;

    public RecyclerAdapterStock(ArrayList<AdaptadorStock> listaArticulos, OnNoteListener onNoteListener) {
        this.listaArticulos = listaArticulos;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_stock, null, false);
        return new StockViewHolder(view, onNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterStock.StockViewHolder holder, int position) {
        holder.viewArticulo.setText(listaArticulos.get(position).getArticulo());
        holder.viewCantidad.setText(String.valueOf(listaArticulos.get(position).getCantidad()));
        holder.viewUnidad.setText(listaArticulos.get(position).getUnidadMedida());
        holder.viewUbicacion.setText(listaArticulos.get(position).getUbicacion());
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
        return listaArticulos.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    public class StockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView viewArticulo, viewCantidad, viewUnidad, viewUbicacion;
        OnNoteListener onNoteListener;

        public StockViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            viewArticulo = itemView.findViewById(R.id.viewArticulo);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);
            viewUnidad = itemView.findViewById(R.id.viewUnidad);
            viewUbicacion = itemView.findViewById(R.id.view_ubicacion);

            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;

        }

        @Override
        public void onClick(View v) {
            this.onNoteListener.onNoteClick(getAdapterPosition());
            this.viewArticulo.getText();
        }
    }
}