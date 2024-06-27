package org.com.sabores_secretos.dao.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.com.sabores_secretos.dao.interfaces.DAO_Receta;
import org.com.sabores_secretos.entities.Receta;

public class DAO_RecetaImpl implements DAO_Receta {

    ConexionMySQL SQL = new ConexionMySQL();

    @Override
    public int registrar(Receta receta) throws Exception {
        String sql = "INSERT INTO receta (categoria, descripcion, origen, tiempo_preparacion, nombre_usuario, "
                + " nombre_receta, numero_comensales, privacidad, imagen) VALUES (?,?,?,?,?,?,?,?,?);";
        int generatedId = 0;
        //Sentencia pàra insertar receta
        try (Connection conexion = SQL.conectarMySQL(); 
             PreparedStatement st = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
           
            st.setString(1, receta.getCategoria());
            st.setString(2, receta.getDescripcion());
            st.setString(3, receta.getOrigen());
            st.setInt(4, receta.getTiempo_preparacion());
            st.setString(5, receta.getNombre_usuario());
            st.setString(6, receta.getNombre_receta());
            st.setInt(7, receta.getNumero_comensales());
            st.setByte(8, receta.getPrivacidad());
            st.setString(9, receta.getImagen());
            
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    //Se insertó el dato y se obtucvo la clave
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException sqlex) {
            // Error específico para SQL
            generatedId = -2;
        } catch (Exception e) {
            // Otro tipo de error
            generatedId = -1;
        }
        return generatedId;
    }

    @Override
    public int eliminar(Receta receta) throws Exception {
        String sql = "DELETE FROM receta WHERE id_receta=?;";
        int estado;
        //Sentencia para eliminar una receta
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, receta.getId_receta());

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
    public int modificarDescripcion(Receta receta, String nuevo) throws Exception {
        String sql = "UPDATE receta SET descripcion=? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar descripcion
        try (Connection conexion = SQL.conectarMySQL();PreparedStatement st = conexion.prepareStatement(sql);){
            
            st.setString(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public int modificarNombreReceta(Receta receta, String nuevo) throws Exception {
        String sql = "UPDATE receta SET nombre_receta=? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar nombre de la receta
        try (Connection conexion = SQL.conectarMySQL();PreparedStatement st = conexion.prepareStatement(sql);){
            st.setString(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public int modificarTiempoPreparacion(Receta receta, int nuevo) throws Exception {
        String sql = "UPDATE receta SET tiempo_preparacion=? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar tiempo de preparacion
        try ( Connection conexion = SQL.conectarMySQL();PreparedStatement st = conexion.prepareStatement(sql);){
            st.setInt(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public int modificarNumeroComensales(Receta receta, int nuevo) throws Exception {
        String sql = "UPDATE receta SET numero_comensales=? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar numero de comensales
        try ( Connection conexion = SQL.conectarMySQL();PreparedStatement st = conexion.prepareStatement(sql);){
            st.setInt(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public int modificarCategoria(Receta receta, String nuevo) throws Exception {
        String sql = "UPDATE receta SET categoria=? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar categoria
        try ( Connection conexion = SQL.conectarMySQL();PreparedStatement st = conexion.prepareStatement(sql);){
            st.setString(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public int modificarOrigen(Receta receta, String nuevo) throws Exception {
        String sql = "UPDATE receta SET origen=? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar origen
        try (Connection conexion = SQL.conectarMySQL(); PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public int modificarPrivacidad(Receta receta, byte nuevo) throws Exception {
        String sql = "UPDATE receta SET privacidad=? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar privacidad
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setByte(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public int modificarImagen(Receta receta, String nuevo) throws Exception{
        String sql = "UPDATE receta SET imagen = ? "
                    + " WHERE id_receta=?;";
        int estado;
        //Sentencia para modificar/actualizar privacidad
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevo);
            st.setInt(2, receta.getId_receta());

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
    public List<Receta> listar() throws Exception {
        String sql = "SELECT categoria, descripcion, origen, tiempo_preparacion, nombre_usuario, nombre_receta, "
                    + " numero_comensales, privacidad, id_receta, imagen "
                        + " FROM receta;";
        List<Receta> listaRecetas = new ArrayList<>();
        try (Connection conexion = SQL.conectarMySQL(); PreparedStatement st = conexion.prepareStatement(sql);
            ResultSet rs = st.executeQuery();){
            while (rs.next()) {
                Receta r = new Receta();
                r.setId_receta(rs.getInt("id_receta"));
                r.setCategoria(rs.getString("categoria"));
                r.setDescripcion(rs.getString("descripcion"));
                r.setOrigen(rs.getString("origen"));
                r.setTiempo_preparacion(rs.getInt("tiempo_preparacion"));
                r.setNombre_usuario(rs.getString("nombre_usuario"));
                r.setNombre_receta(rs.getString("nombre_receta"));
                r.setNumero_comensales(rs.getInt("numero_comensales"));
                r.setPrivacidad(rs.getByte("privacidad"));
                r.setImagen(rs.getString("imagen"));

                //Añadir el objeto receta
                listaRecetas.add(r);
            }

        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return listaRecetas;
    }

    @Override
    public int modificar(Receta t, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
