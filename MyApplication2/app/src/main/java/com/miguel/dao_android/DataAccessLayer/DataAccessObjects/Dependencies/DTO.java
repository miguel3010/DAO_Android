package com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies;

/**
 * Interfaz de definición de operaciones comunes entre todas las estructuras de
 * datos, que hacen la funcion de transportar datos persistentes.
 */
public interface DTO {

    /**
     * Proceso de validación de datos, pertenecientes a el presente DTO
     *
     * @return true = valido, false = error
     */
    boolean isValid();
}
