package com.app_control_stock.hausestock.Lista;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class ListaAdapter implements Serializable {

    int id;
    String item;

    public ListaAdapter(int id, String item) {
        this.id = id;
        this.item = item;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }


}
