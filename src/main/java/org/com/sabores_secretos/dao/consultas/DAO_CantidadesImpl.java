package org.com.sabores_secretos.dao.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.com.sabores_secretos.dao.interfaces.DAO_Cantidades;
import org.com.sabores_secretos.entities.Cantidades;

public class DAO_CantidadesImpl implements DAO_Cantidades {

    ConexionMySQL SQL = new ConexionMySQL();

    @Override
    public int registrar(Cantidades cantidad) throws Exception {
        String sql = "INSERT INTO cantidades (id_receta, id_ingrediente, cantidad, unidad_medida) VALUES (?,?,?,?);";
        int estado = 0;
        //Sentencia pàra insertar cantidades
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {

            st.setInt(1, cantidad.getId_receta());
            st.setInt(2, cantidad.getId_ingrediente());
            st.setInt(3, cantidad.getCantidad());
            st.setString(4, cantidad.getUnidad_medida());

            //Comprobar si se ha registrado
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                estado = 1;
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
    public int modificar(Cantidades t, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(Cantidades cantidad) throws Exception {
        String sql = "DELETE FROM cantidades "
                + " WHERE id_receta=? AND id_ingrediente=?;";
        int estado;
        //Sentencia para eliminar cantidades
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, cantidad.getId_receta());
            st.setInt(2, cantidad.getId_ingrediente());

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
    public int modificarCantidad(Cantidades cantidad, int nuevaCantidad) throws Exception {
        String sql = "UPDATE cantidades SET cantidad=? "
                + " WHERE id_receta=? AND id_ingrediente=?;";
        int estado;

        //Sentencia para modificar/actualizar cantidades
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, nuevaCantidad);
            st.setInt(2, cantidad.getId_receta());
            st.setInt(3, cantidad.getId_ingrediente());

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
    public int modificarUnidadMedida(Cantidades cantidad, String nuevaUnidad) throws Exception {
        String sql = "UPDATE cantidades SET unidad_medida=? "
                + " WHERE id_receta=? AND id_ingrediente=?;";
        int estado;

        //Sentencia para modificar/actualizar cantidades
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevaUnidad);
            st.setInt(2, cantidad.getId_receta());
            st.setInt(3, cantidad.getId_ingrediente());

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
    public List<Cantidades> listarDetalle(int id_receta) throws Exception {
        List<Cantidades> listaDetalle = new ArrayList<>();
        
        String sql = "SELECT i.nombre, c.cantidad, c.unidad_medida, c.id_ingrediente, c.id_receta"
            + " FROM receta r "
            + " JOIN cantidades c ON r.id_receta = c.id_receta"
            + " JOIN ingrediente i ON i.id_ingrediente = c.id_ingrediente"
            + " WHERE r.id_receta = ?";

        
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, id_receta);
            
            try ( ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    // Crear un objeto DetalleCantidad para cada fila
                    Cantidades cantidad = new Cantidades();
                    cantidad.setNombreIngrediente(rs.getString("nombre"));
                    cantidad.setCantidad(rs.getInt("cantidad"));
                    cantidad.setUnidad_medida(rs.getString("unidad_medida"));
                    cantidad.setId_ingrediente(rs.getInt("id_ingrediente"));
                    cantidad.setId_receta(rs.getInt("id_receta"));


                    // Agregar el string DetalleCantidad a la lista
                    listaDetalle.add(cantidad);
                }
            }
        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos."+ sqlex.getMessage());
        } catch (Exception e) {
            throw e;
        }
        return listaDetalle;
    }

    @Override
    public List<Cantidades> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
