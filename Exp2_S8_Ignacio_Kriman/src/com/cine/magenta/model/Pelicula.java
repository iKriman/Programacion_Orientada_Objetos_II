package com.cine.magenta.model;


public class Pelicula {
    private int id;
    private String titulo;
    private String director;
    private int anio;
    private int duracion;
    private String genero;


    public Pelicula() {
    }


    public Pelicula(String titulo, String director, int anio, int duracion, String genero) {
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.duracion = duracion;
        this.genero = genero;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public boolean esValida() {
        if (titulo == null || titulo.trim().equals("")) {
            return false;
        }
        if (director == null || director.trim().equals("")) {
            return false;
        }
        if (anio < 1900 || anio > 2030) {
            return false;
        }
        if (duracion <= 0) {
            return false;
        }
        if (genero == null || genero.trim().equals("")) {
            return false;
        }
        return true;
    }
}