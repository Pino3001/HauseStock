package com.app_control_stock.hausestock.Lista;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class ListaAdapter {

    int id;
    String item;
    boolean ckick;

    public ListaAdapter(int id, String  item, boolean ckick) {
        this.id = id;
        this.item = item;
        this.ckick = ckick;
    }

    public boolean isCkick() {
        return ckick;
    }

    public void setCkick(boolean ckick) {
        this.ckick = ckick;
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
