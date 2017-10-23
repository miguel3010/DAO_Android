/**
 * Copyright (C) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Miguel Ángel Campos <camposmiguelangel3010@gmail.com>  twitter: @Miguel_Angel_30,
 * July 2017
 */
package com.miguel.dao_android.DataAccessLayer.DataAccessObjects;

import android.content.Context;

import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies.DAO;
import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies.Limiter;
import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.IDAO.MySQL.SQLite_ContactoDAO;
import com.miguel.dao_android.Domain_Layer.Contacto;

import java.util.*;

/**
 * Data access object para la tabla Contacto mapeada
 */
public abstract class ContactoDAO implements DAO<Contacto> {
    protected Context context;
    //Methods

    /**
     * Obtener instancia a la implementacion concreta de esta clase abstracta (FACTORY) bajo la premisa de 'Un DBMS especifico'.
     */
    public static ContactoDAO getInstance() {
        return new SQLite_ContactoDAO();
    }

    public ContactoDAO setContext(Context ctx) {
        this.context = ctx;
        return this;
    }

    public abstract boolean create(Contacto dto);

    public abstract void delete(int id);

    public abstract Contacto read(int id);

    public abstract List<Contacto> readALL(Limiter limiter);

    public abstract boolean update(Contacto dto);
}