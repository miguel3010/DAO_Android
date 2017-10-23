package com.miguel.dao_android.DataAccessLayer.DataAccessObjects.IDAO.MySQL;

import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.Dependencies.Limiter;
import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.UserDAO;
import com.miguel.dao_android.DataAccessLayer.Database.DBConnections.DBParameters.Value;
import com.miguel.dao_android.DataAccessLayer.Database.DBConnections.SQLite_DBManager;
import com.miguel.dao_android.Domain_Layer.User;
import java.util.List;

/**
 * Created by miguel on 10/22/2017.
 */

public class SQLite_UserDAO extends UserDAO {
    @Override
    public boolean create(User dto) {
        if (dto != null && dto.isValid()) {
            SQLite_DBManager db = new SQLite_DBManager(this.context);
            String query = "INSERT INTO `user` ( `id`, `username`, `email`, `createdDate`) VALUES ( NULL, @username, @email, @createdDate);";
            Value values = new Value();
            values.add("@username", dto.getusername());
            values.add("@email", dto.getemail());
            values.add("@createdDate", String.valueOf(dto.getcreatedDate()));
            if (db.ExecSQL(query, values)) {
                dto.setid((int) db.getLastInsertedID());
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public List<User> readALL(Limiter limiter) {
        return null;
    }

    @Override
    public boolean update(User dto) {
        return false;
    }
}
