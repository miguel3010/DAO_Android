package com.miguel.dao_android.DataAccessLayer.DataAccessObjects.IDAO.MySQL;

import android.database.Cursor;

import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies.Limiter;
import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.ContactoDAO;
import com.miguel.dao_android.DataAccessLayer.Database.DBConnections.DBParameters.Value;
import com.miguel.dao_android.DataAccessLayer.Database.DBConnections.SQLite_DBManager;
import com.miguel.dao_android.Domain_Layer.Contacto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguel on 10/22/2017.
 */

public class SQLite_ContactoDAO extends ContactoDAO {
    @Override
    public boolean create(Contacto dto) {
        if (dto != null && dto.isValid()) {
            SQLite_DBManager db = new SQLite_DBManager(this.context);
            String query = "INSERT INTO contacto (nombre, correo, salario) VALUES (@nombre, @correo, @salario);";
            Value values = new Value();
            values.add("@nombre", dto.getusername());
            values.add("@correo", dto.getemail());
            values.add("@salario", String.valueOf(dto.getSalario()));
            if (db.ExecSQL(query, values)) {
                dto.setid((int) db.getLastInsertedID());
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(int id) {
        if (id > 0) {
            SQLite_DBManager db = new SQLite_DBManager(this.context);
            String query = "DELETE FROM contacto where _id = @id;";
            Value values = new Value();
            values.add("@id", String.valueOf(id));
            if (db.ExecSQL(query, values)) {
                values.add(null, null);
            }
        }
    }

    @Override
    public Contacto read(int id) {
        Contacto dto = null;
        if (id > 0) {
            SQLite_DBManager db = new SQLite_DBManager(this.context);
            String query = "select nombre, correo , salario from contacto where _id = @id;";
            Value values = new Value();
            values.add("@id", String.valueOf(id));
            Cursor reader = db.ExceuteSQL(query, values);
            if (reader != null && reader.moveToNext()) {
                dto = new Contacto();
                dto.setid(id);
                dto.setusername(reader.getString(0));
                dto.setemail(reader.getString(1));
                dto.setSalario(reader.getFloat(2));
                reader.close();
            }
            db.close();
        }
        return dto;
    }

    @Override
    public List<Contacto> readALL(Limiter limiter) {
        List<Contacto> list = null;
        SQLite_DBManager db = new SQLite_DBManager(this.context);
        String query = "select _id from contacto";
        Cursor reader = db.ExceuteSQL(query);
        if (reader != null) {
            list = new ArrayList<>();
            while (reader.moveToNext()) {
                Contacto dto = this.read(reader.getInt(0));
                if (dto != null) {
                    list.add(dto);
                }
            }
            reader.close();
        }
        db.close();
        return list;
    }

    @Override
    public boolean update(Contacto dto) {
        if (dto != null && dto.isValid()) {
            SQLite_DBManager db = new SQLite_DBManager(this.context);
            String query = "update contacto set nombre = @nombre, correo = @correo, salario = @salario where _id = @id;";
            Value values = new Value();
            values.add("@id", String.valueOf(dto.getid()));
            values.add("@nombre", dto.getusername());
            values.add("@correo", dto.getemail());
            values.add("@salario", String.valueOf(dto.getSalario()));
            return db.ExecSQL(query, values);
        }
        return false;
    }
}
