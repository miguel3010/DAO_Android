package com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies;

import java.util.List;

/**
 * Interfaz de definición de operaciones CRUD básicas aplicables a cualquier DTO Persistente
 * @param <T> Data Transfer Object
 */
public interface DAO<T> {

    /**
     * Metodo de Creación (registro) de entidad (DTO)
     *
     * @param entity Data Transfer Object
     * @return
     */
    boolean create(T entity);

    /**
     * Metodo de lectura de entidad (DTO) persistente
     *
     * @param id
     * @return
     */
    T read(int id);

    /**
     * Lectura de Todos los Elementos T (DTO) que pertenencen al conjunto (DTO)
     * en base de datos, tal que cumplan con las condiciones de limit
     *
     * @param limit Limitador de elementos y desplazamiento
     * @return Lista de T, donde T es elemento de DTO
     */
    List<T> readALL(Limiter limit);

    /**
     * Proceso de eliminacion de elemento que pertenece al conjunto (DTO), tal
     * que sea identificable por un id = ID
     *
     * @param id Numero de identificación de elemento
     */
    void delete(int id);

    /**
     * Proceso de actualizacion de entidad T, tal que t es elemento del conjunto
     * DTO
     *
     * @param entity Data Transfer Object
     * @return true = correcto, false = error
     */
    boolean update(T entity);
}
