package org.com.sabores_secretos.dao.interfaces;

import org.com.sabores_secretos.entities.Usuario;

public interface DAO_Usuario extends DAO_CRUD<Usuario> {

    public String registrarUser(Usuario user) throws Exception;

    public int modificarCorreo(Usuario user, String nuevo) throws Exception;

    public int modificarContrasena(Usuario user, String nuevo) throws Exception;

    public Usuario encontrarUsuario(String nombre) throws Exception;
    
    public int bajaUsuario(String id_usuario) throws Exception;
    
    public int habilitarUsuario(String id_usuario, String contrasena) throws Exception;
    
    public boolean login(String nombre,String contrasena)throws Exception;
}
