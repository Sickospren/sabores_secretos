package org.com.sabores_secretos.dao.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.com.sabores_secretos.dao.interfaces.DAO_Valoracion;
import org.com.sabores_secretos.entities.Valoracion;

public class DAO_ValoracionImpl implements DAO_Valoracion {

    ConexionMySQL SQL = new ConexionMySQL();

    @Override
    public int registrar(Valoracion valoracion) throws Exception {
        String sql = "INSERT INTO valoracion (puntuacion, comentario, nombre_usuario, id_receta) VALUES (?,?,?,?);";
        int generatedId = 0;

        //Sentencia pàra insertar ingrediente
        try ( Connection conexion = SQL.conectarMySQL();  
                PreparedStatement st = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            st.setInt(1, valoracion.getPuntuacion());
            st.setString(2, valoracion.getComentario());
            st.setString(3, valoracion.getNombre_usuario());
            st.setInt(4, valoracion.getId_receta());

            //Comprobar si se ha insertado un dato
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    //Devolver la clave primaria
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
    public int eliminar(Valoracion valoracion) throws Exception {
        String sql = "DELETE FROM valoracion "
                    + " WHERE id_valoracion=?;";
        int estado;

        //Sentencia para eliminar la valoracion
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, valoracion.getId_valoracion());

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
    public int modificarPuntuacion(Valoracion valoracion, int nuevaPuntuacion) throws Exception {
        String sql = "UPDATE valoracion SET puntuacion=? "
                    + " WHERE id_valoracion=?;";
        int estado;

        //Sentencia para modificar/actualizar ingrediente
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, nuevaPuntuacion);
            st.setInt(2, valoracion.getId_valoracion());

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
    public int modificarComentario(Valoracion valoracion, String nuevoComentario) throws Exception {
        String sql = "UPDATE valoracion SET comentario=? "
                    + " WHERE id_valoracion=?;";
        int estado;

        //Sentencia para modificar/actualizar ingrediente
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevoComentario);
            st.setInt(2, valoracion.getId_valoracion());

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
    public List<Valoracion> listarValoracion(int id_receta) throws Exception {
        List<Valoracion> listaValoracion = new ArrayList<>();
        String sql = "SELECT v.id_valoracion, v.nombre_usuario, v.puntuacion, v.comentario, v.id_receta "
                    + " FROM valoracion v "
                    + " WHERE v.id_receta = ?;";

        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, id_receta);
          
            try ( ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    // Crear un objeto DetalleCantidad para cada fila
                    Valoracion valoracion = new Valoracion();
                    valoracion.setId_valoracion(rs.getInt("id_valoracion"));
                    valoracion.setNombre_usuario(rs.getString("nombre_usuario"));
                    valoracion.setPuntuacion(rs.getInt("puntuacion"));
                    valoracion.setComentario(rs.getString("comentario"));
                    valoracion.setId_receta(rs.getInt("id_receta"));

                    // Agregar el string DetalleCantidad a la lista
                    listaValoracion.add(valoracion);
                }
            }
        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return listaValoracion;
    }

    @Override
    public int modificar(Valoracion t, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Valoracion> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
