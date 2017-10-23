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


public class Contacto implements DTO {
    //Atributes
    private int id;
    private String nombre;
    private String correo;
    private float salario;

    public float getSalario(){
        return salario;
    }

    public void setSalario(float salario){
        this.salario = salario;
    }

    //Methods
    public boolean isValid() {
        if (Util.validateString(nombre) &&
                Util.validateString(correo)) {
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
        this.nombre = username;
    }

    /**
     *
     */
    public String getusername() {
        return this.nombre;
    }

    /**
     *
     * @param email
     */
    public void setemail(String email) {
        this.correo = email;
    }

    /**
     *
     */
    public String getemail() {
        return this.correo;
    }
}