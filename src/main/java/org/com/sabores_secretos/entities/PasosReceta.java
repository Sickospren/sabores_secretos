package org.com.sabores_secretos.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PasosReceta {

    private int id_paso;
    private int id_receta;
    private int numero_paso;
    private String descripcion;
    private String imagen;

    public PasosReceta() {
    }

    public PasosReceta(int id_paso, int id_receta, int numero_paso, String descripcion, String imagen) {
        this.id_paso = id_paso;
        this.id_receta = id_receta;
        this.numero_paso = numero_paso;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getId_paso() {
        return id_paso;
    }

    public int getId_receta() {
        return id_receta;
    }

    public int getNumero_paso() {
        return numero_paso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId_paso(int id_paso) {
        this.id_paso = id_paso;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public void setNumero_paso(int numero_paso) {
        this.numero_paso = numero_paso;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    

    @Override
    public String toString() {
        return "PasosReceta: \nId paso=" + id_paso + "\nId receta=" + id_receta + "\nNumero_paso=" + numero_paso
                + "\nDescripción=" + descripcion;
    }

}
