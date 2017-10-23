/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.dao_android.DataAccessLayer.Database.DBConnections.DBParameters;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguel
 */
public class Value {

    private List<Node> list = new ArrayList<>();

    public void add(String key, String value) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        list.add(node);
    }

    public List<Node> getList() {
        return list;
    }
}
