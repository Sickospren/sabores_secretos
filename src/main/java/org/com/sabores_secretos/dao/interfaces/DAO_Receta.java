package org.com.sabores_secretos.dao.interfaces;

import java.util.List;
import org.com.sabores_secretos.entities.Receta;

public interface DAO_Receta extends DAO_CRUD<Receta> {

    public int modificarDescripcion(Receta receta, String nuevo) throws Exception;

    public int modificarNombreReceta(Receta receta, String nuevo) throws Exception;

    public int modificarTiempoPreparacion(Receta receta, int nuevo) throws Exception;

    public int modificarNumeroComensales(Receta receta, int nuevo) throws Exception;

    public int modificarCategoria(Receta receta, String nuevo) throws Exception;

    public int modificarOrigen(Receta receta, String nuevo) throws Exception;

    public int modificarPrivacidad(Receta receta, byte nuevo) throws Exception;
    
    public int modificarImagen(Receta receta, String nuevo) throws Exception;
}
