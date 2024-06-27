package org.com.sabores_secretos.dao.interfaces;

import java.util.List;

public interface DAO_CRUD<T> {

    public int registrar(T t) throws Exception;

    public int modificar(T t, String s) throws Exception;

    public int eliminar(T t) throws Exception;

    public List<T> listar() throws Exception;
}
