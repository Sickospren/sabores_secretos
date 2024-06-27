package org.com.sabores_secretos.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cantidades {

    private int id_ingrediente;
    private int id_receta;
    private int cantidad;
    private String unidad_medida;
    private String nombreIngrediente;

    public Cantidades() {
    }

    public Cantidades(int id_ingrediente, int id_receta, int cantidad, String unidad_medida) {
        this.id_ingrediente = id_ingrediente;
        this.id_receta = id_receta;
        this.cantidad = cantidad;
        this.unidad_medida = unidad_medida;
    }

    public Cantidades(int cantidad, String unidad_medida, String nombreIngrediente) {
        this.cantidad = cantidad;
        this.unidad_medida = unidad_medida;
        this.nombreIngrediente = nombreIngrediente;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }



    @Override
    public String toString() {
        return "Cantidades: \nId ingrediente=" + id_ingrediente + "\n Id receta=" + id_receta + "\nCantidad=" + cantidad
                + "\nUnidadMedida=" + unidad_medida + "\n"
                + "\nNombreIngrediente=" + nombreIngrediente + "\n";
    }

}
