package org.com.sabores_secretos.dao.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.com.sabores_secretos.dao.interfaces.DAO_PasosReceta;
import org.com.sabores_secretos.entities.PasosReceta;

public class DAO_PasosRecetaImpl implements DAO_PasosReceta {

    ConexionMySQL SQL = new ConexionMySQL();

    @Override
    public int registrar(PasosReceta paso) throws Exception {
        String sql = "INSERT INTO pasos_receta (id_receta, numero_paso, descripcion, imagen) "
                    + "  VALUES (?,?,?,?);";
        int generatedId = 0;
        //Sentencia pàra insertar pasos de una receta
        try ( Connection conexion = SQL.conectarMySQL();  
                PreparedStatement st = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            st.setInt(1, paso.getId_receta());
            st.setInt(2, paso.getNumero_paso());
            st.setString(3, paso.getDescripcion());
            st.setString(4, paso.getImagen());

            int affectedRows = st.executeUpdate();
            if (affectedRows != 0) {
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
    public int eliminar(PasosReceta paso, int id_receta) throws Exception {
        String sql = "DELETE FROM pasos_receta "
                    + " WHERE id_receta=? AND numero_paso=?;";
        int estado;
        //Sentencia para eliminar un paso de una receta
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, id_receta);
            st.setInt(2, paso.getId_paso());

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
    public int modificar(PasosReceta paso, String nuevoPaso) throws Exception {
        String sql = "UPDATE pasos_receta SET descripcion=? "
                    + " WHERE id_receta=? AND numero_paso=?;";
        int estado;
        //Sentencia para modificar/actualizar descripcion
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevoPaso);
            st.setInt(2, paso.getId_receta());
            st.setInt(3, paso.getId_paso());

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
    public int modificarImagen(PasosReceta paso, String nuevaImagen) throws Exception {
        String sql = "UPDATE pasos_receta SET imagen=? "
                    + " WHERE id_receta=? AND numero_paso=?;";
        int estado;
        //Sentencia para modificar/actualizar imagen
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setString(1, nuevaImagen);
            st.setInt(2, paso.getId_receta());
            st.setInt(3, paso.getId_paso());

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
    public List<PasosReceta> listar(int id_receta) throws Exception {
        String sql = "SELECT numero_paso, descripcion, id_receta, id_paso, imagen"
                    + " FROM pasos_receta "
                    + " WHERE id_receta=?;";
        List<PasosReceta> listaPasosReceta = new ArrayList<>();
        try ( Connection conexion = SQL.conectarMySQL();  PreparedStatement st = conexion.prepareStatement(sql);) {
            st.setInt(1, id_receta);
            try ( ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    PasosReceta pr = new PasosReceta();
                    pr.setNumero_paso(rs.getInt("numero_paso"));
                    pr.setDescripcion(rs.getString("descripcion"));
                    pr.setId_receta(rs.getInt("id_receta"));
                    pr.setId_paso(rs.getInt("id_paso"));
                    pr.setImagen(rs.getString("imagen"));

                    //Añadir el objeto paso receta
                    listaPasosReceta.add(pr);
                }
            }
        } catch (SQLException sqlex) {
            throw new Exception("Error en la base de datos.");
        } catch (Exception e) {
            throw e;
        }
        return listaPasosReceta;
    }

    @Override
    public List<PasosReceta> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(PasosReceta t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

