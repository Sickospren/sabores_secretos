package org.com.sabores_secretos.dao.interfaces;

import java.util.List;
import org.com.sabores_secretos.entities.Cantidades;

public interface DAO_Cantidades extends DAO_CRUD<Cantidades> {

    public int modificarCantidad(Cantidades cantidad, int nuevaCantidad) throws Exception;

    public int modificarUnidadMedida(Cantidades cantidad, String nuevaUnidad) throws Exception;

    public List<Cantidades> listarDetalle(int id_receta) throws Exception;

}
