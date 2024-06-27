package org.com.sabores_secretos.dao.interfaces;

import org.com.sabores_secretos.entities.Ingrediente;

public interface DAO_Ingrediente extends DAO_CRUD<Ingrediente> {

    public Ingrediente buscarIngrediente(String ingrediente) throws Exception;
    public int existe(String ingrediente) throws Exception;

}
