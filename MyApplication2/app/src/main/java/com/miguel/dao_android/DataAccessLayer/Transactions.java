/**
Copyright (C) - All Rights Reserved
* Unauthorized copying of this file, via any medium is strictly prohibited
* Proprietary and confidential
* Written by Miguel Ángel Campos <camposmiguelangel3010@gmail.com>  twitter: @Miguel_Angel_30,
* July 2017
* 
*/
package com.miguel.dao_android.DataAccessLayer;

import com.miguel.dao_android.DataAccessLayer.Transactions_Logic.MySQL.MySQL_Transactions;

/**
* Modulo Destinado al manifiesto de las transacciones disponibles para la base de datos
*/public abstract class Transactions{

//Methods
/**
* Obtener instancia a la implementacion concreta de esta clase abstracta (FACTORY) bajo la premisa de 'Un DBMS especifico'.*/
public static Transactions getInstance(){
return new MySQL_Transactions();
}
}