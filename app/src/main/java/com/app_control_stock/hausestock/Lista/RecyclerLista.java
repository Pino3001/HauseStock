package com.app_control_stock.hausestock.Lista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app_control_stock.hausestock.BaseDatos.Estructura_BD;
import com.app_control_stock.hausestock.BaseDatos.Helper_BD;
import com.app_control_stock.hausestock.R;
import com.app_control_stock.hausestock.VerStocks.AdaptadorStock;
import com.app_control_stock.hausestock.VerStocks.RecyclerAdapterStock;

import java.util.ArrayList;

public class RecyclerLista extends RecyclerView.Adapter<RecyclerLista.ListaViewHolder>{

    ArrayList<ListaAdapter> lista_compras;
    private OnNoteListener onNote, onNote2;


    public RecyclerLista(ArrayList<ListaAdapter> lista_compras, OnNoteListener onNote,  OnNoteListener onNote2) {
        this.lista_compras = lista_compras;
        this.onNote =  onNote;
        this.onNote2 = onNote2;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_lista, null, false);
        return new ListaViewHolder(view, onNote, onNote2);

    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        holder.viewItem.setText(lista_compras.get(position).getItem());
        holder.check.setChecked(lista_compras.get(position).isCkick());
    }


    @Override
    public int getItemCount() {
        return lista_compras.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {

        TextView viewItem;
        CheckBox check;
        OnNoteListener onNoteListener, onNote2 ;

        public ListaViewHolder(@NonNull View itemView, OnNoteListener onNoteListener,OnNoteListener onNote2) {
            super(itemView);

            check = itemView.findViewById(R.id.checkBox_list);
            viewItem = itemView.findViewById(R.id.text_lista);
            this.onNoteListener = onNoteListener;
            this.onNote2 = onNote2;
            itemView.findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNoteListener.onNoteClick(getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.checkBox_list).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNote2.onNoteClick(getAdapterPosition());
                }
            });
        }
    }
}
