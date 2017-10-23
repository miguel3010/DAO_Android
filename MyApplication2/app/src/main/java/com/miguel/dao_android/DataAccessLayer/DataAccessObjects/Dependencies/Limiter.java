package com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies;

/**
 * Limitador de elementos en lectura
 */
public class Limiter {

    /**
     * Constructor de Limitador
     */
    public Limiter() {
        ResultsOffeset = 0;
        ResultsCount = 0;
    }

    /**
     * Cantidad de resultados deben ser leidos
     */
    public int ResultsCount;

    /**
     * Cantidad de elementos deben ser desplazados
     */
    public int ResultsOffeset;
}
