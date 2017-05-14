package com.example.carles.filmlib;

/**
 * Created by Carles on 14/05/2017.
 */

public class Elemento {

    private int id;
    private String titulo;
    private String genero;
    private String director;
    private int temporadas;
    private int estreno;

    public Elemento(int id, String titulo, String genero, String director, int temporadas, int estreno) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.director = director;
        this.temporadas = temporadas;
        this.estreno = estreno;
    }

    public Elemento() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getEstreno() {
        return estreno;
    }

    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
