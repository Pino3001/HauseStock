package com.app_control_stock.hausestock.VerStocks;

public class AdaptadorStock {
    int id;
    String articulo;
    int cantidad;
    String UnidadMedida;
    String ubicacion;

    public AdaptadorStock() {
        this.id = id;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.UnidadMedida = UnidadMedida;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticulo(){ return articulo; }

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
