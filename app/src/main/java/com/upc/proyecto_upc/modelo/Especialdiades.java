package com.upc.proyecto_upc.modelo;

public class Especialdiades {
    private int imagen;
    private String titulo;
    private int Descripcion;
    private String precio;

    public Especialdiades(int imagen, String titulo) {
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public Especialdiades(int imagen, String titulo, int descripcion, String precio) {
        this.imagen = imagen;
        this.titulo = titulo;
        Descripcion = descripcion;
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(int descripcion) {
        Descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
