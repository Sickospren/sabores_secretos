package org.com.sabores_secretos.dao.consultas;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.com.sabores_secretos.dao.interfaces.DAO_Usuario;
import org.com.sabores_secretos.entities.Usuario;

public class DAO_UsuarioImpl implements DAO_Usuario {

    ConexionMySQL SQL = new ConexionMySQL();

    @Override
    public String registrarUser(Usuario user) throws Exception {
        String sql = "INSERT INTO usuario (nombre_usuario, contrasena, correo_electronico) "
                +    " VALUES (?,?,?);";
        String pk = null;

        //Sentencia pàra insertar usuario
        try (Connection conexion = SQL.conectarMySQL(); 
                    PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, user.getNombre());
            st.setString(2, user.getContrasena());
            st.setString(3, user.getCorreo());
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("No se pudo insertar el usuario");
            } else {
                pk = user.getNombre();
            }
        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return pk;
    }

    @Override
    public int eliminar(Usuario user) throws Exception {
        String sql = "DELETE FROM usuario"
                + " WHERE nombre_usuario=?;";
        int estado;

        //Sentencia para eliminar al usuario
        try (Connection conexion = SQL.conectarMySQL(); 
                PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, user.getNombre());

            //Comprobar si se ha eliminado algún dato
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                estado = 1;   //Se eliminaron datos
            } else {
                estado = 0;   //No se eliminaron datos
            }
        } catch (SQLException sqlex) {
            // Error específico para SQL
            estado = -2;
        } catch (Exception e) {
            // Otro tipo de error
            estado = -1;
        }
        return estado;
    }

    @Override
    public int modificarCorreo(Usuario user, String nuevo) throws Exception {
        String sql = "UPDATE usuario SET correo_electronico=? "
                + " WHERE nombre_usuario=?;";
        int estado;

        //Sentencia para modificar/actualizar correo electrónico
        try (Connection conexion = SQL.conectarMySQL(); 
                PreparedStatement st = conexion.prepareStatement(sql);) {

            st.setString(1, nuevo);
            st.setString(2, user.getNombre());
            //Comprobar si se ha modificado algún dato
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                estado = 1;   //Se modificaron datos
            } else {
                estado = 0;   //No se modificaron datos
            }
        } catch (SQLException sqlex) {
            // Error específico para SQL
            estado = -2;
        } catch (Exception e) {
            // Otro tipo de error
            estado = -1;
        }
        return estado;
    }

    @Override
    public int modificarContrasena(Usuario user, String nuevo) throws Exception {
        String sql = "UPDATE usuario SET contrasena=? "
                + " WHERE nombre_usuario=?;";
        int estado;

        //Sentencia para modificar/actualizar contraseña
        try (Connection conexion = SQL.conectarMySQL(); PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevo);
            st.setString(2, user.getNombre());

            //Comprobar si se ha modificado algún dato
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                estado = 1;   //Se modificaron datos
            } else {
                estado = 0;   //No se modificaron datos
            }
        } catch (SQLException sqlex) {
            // Error específico para SQL
            estado = -2;
        } catch (Exception e) {
            // Otro tipo de error
            estado = -1;
        }
        return estado;
    }

    @Override
    public int bajaUsuario(String id_usuario) throws Exception {
        String sql = "UPDATE usuario SET activo=0 "
                + " WHERE nombre_usuario=? OR correo_electronico=?;";
        int estado;

        //Sentencia para dar de baja a un usuario
        try (Connection conexion = SQL.conectarMySQL(); 
                PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, id_usuario);
            st.setString(2, id_usuario);

            //Comprobar si se ha modificado algún dato
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                estado = 1;   //Se modificaron datos
            } else {
                estado = 0;   //No se modificaron datos
            }
        } catch (SQLException sqlex) {
            // Error específico para SQL
            estado = -2;
        } catch (Exception e) {
            // Otro tipo de error
            estado = -1;
        }
        return estado;
    }

    @Override
    public int habilitarUsuario(String id_usuario, String contrasena) throws Exception {
        String sql = "UPDATE usuario SET activo=1 "
                + " WHERE (nombre_usuario=? OR correo_electronico=?) AND contrasena=?;";
        int estado;

         //Sentencia para habilitar a un usuario
        try (Connection conexion = SQL.conectarMySQL(); PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, id_usuario);
            st.setString(2, id_usuario);
            st.setString(3, contrasena);

            //Comprobar si se ha modificado algún dato
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                estado = 1;   //Se modificaron datos
            } else {
                estado = 0;   //No se modificaron datos
            }
        } catch (SQLException sqlex) {
            // Error específico para SQL
            estado = -2;
        } catch (Exception e) {
            // Otro tipo de error
            estado = -1;
        }
        return estado;
    }

    //Método que lista a todos los usuarios de la base de datos (para DEBUG)
    @Override
    public List<Usuario> listar() throws Exception {
        String sql = "SELECT u.nombre_usuario, u.contrasena, u.correo_electronico "
                + " FROM usuario u;";
        List<Usuario> listaUsuarios = new ArrayList<>();
        try (Connection conexion = SQL.conectarMySQL(); 
                PreparedStatement st = conexion.prepareStatement(sql); ResultSet rs = st.executeQuery();) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString("nombre_usuario"));
                u.setContrasena(rs.getString("contrasena"));
                u.setCorreo(rs.getString("correo_electronico"));

                //Añadir el usuario a la lista
                listaUsuarios.add(u);
            }

        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return listaUsuarios;
    }

    @Override
    public Usuario encontrarUsuario(String nombre) throws Exception {
        String sql = "SELECT u.nombre_usuario, u.contrasena ,u.correo_electronico "
                + " FROM usuario u "
                + " WHERE u.nombre_usuario= ? OR u.correo_electronico =?;";
        Usuario u = new Usuario();
        try (Connection conexion = SQL.conectarMySQL(); PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nombre);
            st.setString(2, nombre);
            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    u.setNombre(rs.getString("nombre_usuario"));
                    u.setContrasena(rs.getString("contrasena"));
                    u.setCorreo(rs.getString("correo_electronico"));
                } else {
                    throw new Exception("No se pudo encontrar el usuario");
                }
            }
        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return u;
    }

    @Override
    public boolean login(String nombre, String contrasena) throws Exception {
        String sql = "SELECT u.nombre_usuario, u.contrasena ,u.correo_electronico "
                + " FROM usuario u "
                + " WHERE (u.nombre_usuario=? OR u.correo_electronico=?) AND u.contrasena=? AND activo=1";
        try (Connection conexion = SQL.conectarMySQL(); PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nombre);
            st.setString(2, nombre);
            st.setString(3, contrasena);
            try (ResultSet rs = st.executeQuery();) {

                return rs.next();//si tiene informacion retornara true, sino false

            }

        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public int modificar(Usuario t, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int registrar(Usuario t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
