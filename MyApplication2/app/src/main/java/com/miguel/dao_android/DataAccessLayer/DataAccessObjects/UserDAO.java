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
import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.IDAO.MySQL.SQLite_UserDAO;
import com.miguel.dao_android.Domain_Layer.User;

import java.util.*;

/**
 * Data access object para la tabla User mapeada
 */
public abstract class UserDAO implements DAO<User> {
    protected Context context;
    //Methods

    /**
     * Obtener instancia a la implementacion concreta de esta clase abstracta (FACTORY) bajo la premisa de 'Un DBMS especifico'.
     */
    public static UserDAO getInstance() {
        return new SQLite_UserDAO();
    }

    public UserDAO setContext(Context ctx) {
        this.context = ctx;
        return this;
    }

    public abstract boolean create(User dto);

    public abstract void delete(int id);

    public abstract User read(int id);

    public abstract List<User> readALL(Limiter limiter);

    public abstract boolean update(User dto);
}