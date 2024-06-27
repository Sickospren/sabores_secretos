package org.com.sabores_secretos.dao.interfaces;

import java.util.List;
import org.com.sabores_secretos.entities.Valoracion;

public interface DAO_Valoracion extends DAO_CRUD<Valoracion> {
    public int modificarPuntuacion (Valoracion valoracion, int nuevaPuntuacion) throws Exception;
    
    public int modificarComentario (Valoracion valoracion, String nuevoComentario) throws Exception;
    
    //Añadir método de modificar nombre usuraio??
    
    public List<Valoracion> listarValoracion(int id_receta) throws Exception;
}
