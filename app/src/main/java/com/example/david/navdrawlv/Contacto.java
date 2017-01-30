package com.example.david.navdrawlv;

import java.io.Serializable;

/**
 * Created by David on 02/12/2016.
 */

public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String nombre;

    public Contacto(long id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
