package org.com.sabores_secretos.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {

    private String nombre_usuario;
    private String contrasena;
    private String correo_electronico;
    private Byte activo;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasena, String correo, Byte activo) {
        this.nombre_usuario = nombre;
        this.contrasena = contrasena;
        this.correo_electronico = correo;
        this.activo = activo;
        
    }

    public String getNombre() {
        return nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo_electronico;
    }

    public void setNombre(String nombre) {
        this.nombre_usuario = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCorreo(String correo) {
        this.correo_electronico = correo;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }
    
    

    @Override
    public String toString() {
        return "Usuario: \n\tNombre=" + nombre_usuario + "\n\tContrasena=" + contrasena + "\n\tCorreo=" + correo_electronico + "\n";
    }

}
