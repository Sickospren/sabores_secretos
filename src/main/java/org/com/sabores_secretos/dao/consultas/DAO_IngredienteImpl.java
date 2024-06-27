package org.com.sabores_secretos.dao.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.com.sabores_secretos.dao.interfaces.DAO_Ingrediente;
import org.com.sabores_secretos.entities.Ingrediente;

public class DAO_IngredienteImpl implements DAO_Ingrediente {

    ConexionMySQL SQL = new ConexionMySQL();

    @Override
    public int registrar(Ingrediente ingrediente) throws Exception {
        String sql = "INSERT INTO ingrediente (nombre) VALUES (?);";
        int generatedId = 0;
        
        //Sentencia pàra insertar ingrediente
        try ( Connection conexion = SQL.conectarMySQL();  
                PreparedStatement st = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            st.setString(1, ingrediente.getNombre());
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
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
    public int modificar(Ingrediente ingrediente, String nuevoNombre) throws Exception {
        String sql = "UPDATE ingrediente SET nombre=? "
                    + " WHERE id_ingrediente=?;";
        int estado;

        //Sentencia para modificar/actualizar ingrediente
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevoNombre);
            st.setInt(2, ingrediente.getId_ingrediente());

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
    public int eliminar(Ingrediente ingrediente) throws Exception {
        String sql = "DELETE FROM ingrediente "
                    + " WHERE nombre=?;";
        int estado;

        //Sentencia para eliminaro el ingrediente
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, ingrediente.getNombre());

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
    public List<Ingrediente> listar() throws Exception {
        String sql = "SELECT id_ingrediente, nombre "
                    + " FROM ingrediente;";
        List<Ingrediente> listaIngredientes = new ArrayList<>();

        //Try con recursos
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);  
                ResultSet rs = st.executeQuery();) {
            while (rs.next()) {
                Ingrediente i = new Ingrediente();
                i.setNombre(rs.getString("nombre"));
                i.setId_ingrediente(rs.getInt("id_ingrediente"));

                //Añadir objeto a la lista
                listaIngredientes.add(i);
            }
        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return listaIngredientes;
    }

    //carga los datos de un ingrediente
    @Override
    public Ingrediente buscarIngrediente(String busqueda) throws Exception {
        String sql = "SELECT id_ingrediente, nombre "
                    + " FROM ingrediente "
                    + " WHERE nombre=?";
        Ingrediente ingrediente = new Ingrediente();

        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, busqueda);
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    ingrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
                    ingrediente.setNombre(rs.getString("nombre"));
                } else{
                    throw new Exception ("Ingrediente no encontrado.");
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return ingrediente;
    }
    
    @Override
    public int existe(String ingrediente) throws Exception {

        String sql = "SELECT id_ingrediente "
                + " FROM ingrediente "
                + " WHERE nombre=?;";
        int idIngrediente = 0;
        //Sentencia para realizar la consulta y comp`robar que hay un ingrediente
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, ingrediente);
            try ( ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    idIngrediente = rs.getInt("id_ingrediente");
                }
            }
        } catch (SQLException ex) {
            idIngrediente = -2;
        } catch (Exception e) {
            idIngrediente = -1;
        }
        return idIngrediente;
    }

}
