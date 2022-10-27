package com.app_control_stock.hausestock.VerStocks;

import java.io.Serializable;

public class AdaptadorStock {

    String articulo;
    int cantidad;
    String UnidadMedida;
    String ubicacion;

    public AdaptadorStock() {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.UnidadMedida = UnidadMedida;
        this.ubicacion = ubicacion;
    }

    public String getArticulo() {

        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String UnidadMedida) {
        this.UnidadMedida = UnidadMedida;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
