package com.example.carles.filmlib;
import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<Elemento> elementos = new ArrayList<Elemento>();

    public List<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }
}