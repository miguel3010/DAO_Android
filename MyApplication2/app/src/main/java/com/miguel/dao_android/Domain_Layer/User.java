/**
 * Copyright (C) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Miguel Ángel Campos <camposmiguelangel3010@gmail.com>  twitter: @Miguel_Angel_30,
 * July 2017
 */
package com.miguel.dao_android.Domain_Layer;

import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies.DTO;
import com.miguel.dao_android.General.Util;

import java.util.Date;


public class User implements DTO {
    //Atributes
    private int id;
    private String username;
    private String email;
    private Date createdDate;

    //Methods
    public boolean isValid() {
        if (Util.validateString(username) &&
                Util.validateString(email)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param id
     */
    public void setid(int id) {
        this.id = id;
    }

    /**
     *
     */
    public int getid() {
        return this.id;
    }

    /**
     *
     * @param username
     */
    public void setusername(String username) {
        this.username = username;
    }

    /**
     *
     */
    public String getusername() {
        return this.username;
    }

    /**
     *
     * @param email
     */
    public void setemail(String email) {
        this.email = email;
    }

    /**
     *
     */
    public String getemail() {
        return this.email;
    }

    /**
     *
     * @param createdDate
     */
    public void setcreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     *
     */
    public Date getcreatedDate() {
        return this.createdDate;
    }
}