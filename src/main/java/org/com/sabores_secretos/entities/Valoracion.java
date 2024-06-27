package org.com.sabores_secretos.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Valoracion {

    private int id_valoracion;
    private int puntuacion;
    private String comentario;
    private String nombre_usuario;
    private int id_receta;

    public Valoracion() {
    }

    public Valoracion(int id_valoracion, int puntuacion, String comentario, String nombre_usuario, int id_receta) {
        this.id_valoracion = id_valoracion;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.nombre_usuario = nombre_usuario;
        this.id_receta = id_receta;
    }

    public int getId_valoracion() {
        return id_valoracion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_valoracion(int id_valoracion) {
        this.id_valoracion = id_valoracion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    @Override
    public String toString() {
        return "Valoracion: \nId valoracion=" + id_valoracion + "\nPuntuacion=" + puntuacion + "\nComentario=" + comentario
                + "\nNombre usuario=" + nombre_usuario + "\nId receta=" + id_receta + '}';
    }

}
