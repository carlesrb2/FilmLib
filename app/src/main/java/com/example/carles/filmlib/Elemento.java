package com.example.carles.filmlib;

/**
 * Created by Carles on 14/05/2017.
 */

public class Elemento {

    private String id;
    private String tipo;
    private String titulo;
    private String genero;
    private String director;
    private String temporadas;
    private String estreno;

    public Elemento(String id, String titulo, String genero, String director, String temporadas, String estreno) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.director = director;
        this.temporadas = temporadas;
        this.estreno = estreno;
    }

    public Elemento() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(String temporadas) {
        this.temporadas = temporadas;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Elemento{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", director='" + director + '\'' +
                ", temporadas='" + temporadas + '\'' +
                ", estreno='" + estreno + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }
}
