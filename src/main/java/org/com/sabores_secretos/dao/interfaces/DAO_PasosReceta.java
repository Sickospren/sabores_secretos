package org.com.sabores_secretos.dao.interfaces;

import java.util.List;
import org.com.sabores_secretos.entities.PasosReceta;

public interface DAO_PasosReceta extends DAO_CRUD<PasosReceta> {

    public int eliminar(PasosReceta paso, int id_receta) throws Exception;

    public List<PasosReceta> listar(int id_receta) throws Exception;
    
    public int modificarImagen(PasosReceta paso, String nuevaImagen) throws Exception;
}
