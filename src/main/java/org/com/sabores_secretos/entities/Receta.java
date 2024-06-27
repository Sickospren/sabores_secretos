package org.com.sabores_secretos.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Receta {

    private int id_receta;
    private String categoria;
    private String descripcion;
    private String origen;
    private int tiempo_preparacion;
    private String nombre_usuario;
    private String nombre_receta;
    private int numero_comensales;
    private byte privacidad;
    private String imagen;

    public Receta() {
    }

    public Receta(int id_receta, String categoria, String descripcion, String origen, int tiempo_preparacion, String nombre_usuario, String nombre_receta, int numero_comensales, byte privacidad, String imagen) {
        this.id_receta = id_receta;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.origen = origen;
        this.tiempo_preparacion = tiempo_preparacion;
        this.nombre_usuario = nombre_usuario;
        this.nombre_receta = nombre_receta;
        this.numero_comensales = numero_comensales;
        this.privacidad = privacidad;
        this.imagen = imagen;
    }

    public int getId_receta() {
        return id_receta;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public int getTiempo_preparacion() {
        return tiempo_preparacion;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getNombre_receta() {
        return nombre_receta;
    }

    public int getNumero_comensales() {
        return numero_comensales;
    }

    public byte getPrivacidad() {
        return privacidad;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setTiempo_preparacion(int tiempo_preparacion) {
        this.tiempo_preparacion = tiempo_preparacion;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setNombre_receta(String nombre_receta) {
        this.nombre_receta = nombre_receta;
    }

    public void setNumero_comensales(int numero_comensales) {
        this.numero_comensales = numero_comensales;
    }

    public void setPrivacidad(byte privacidad) {
        this.privacidad = privacidad;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Receta: \nId receta=" + id_receta + "\nCategoria=" + categoria + "\nDescripcion=" + descripcion + "\nOrigen=" + origen
                + "\n Tiempo preparacion=" + tiempo_preparacion + "\nNombre usuario=" + nombre_usuario + "\nNombre receta=" + nombre_receta
                + "\nNumero comensales=" + numero_comensales + "\nPrivado=" + privacidad;
    }

}
