package org.com.sabores_secretos.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ingrediente {
    private int id_ingrediente;
    private String nombre;

    public Ingrediente(){
    }
    
    public Ingrediente(int id_ingrediente, String nombre) {
        this.id_ingrediente = id_ingrediente;
        this.nombre = nombre;
    }    
    
    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ingrediente: \nId ingrediente=" + id_ingrediente + "\nNombre=" + nombre+ "\n";
    }
    
}
