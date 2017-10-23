package com.miguel.dao_android.DataAccessLayer.Database.DBConnections;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.miguel.dao_android.DataAccessLayer.Database.DBConnections.DBParameters.Node;
import com.miguel.dao_android.DataAccessLayer.Database.DBConnections.DBParameters.Value;
import com.miguel.dao_android.General.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguel on 10/22/2017.
 */

public class SQLite_DBManager {

    //Atributes
    private String LOGIN;
    private String PASSWORD;
    private static final String URL = "my_database.db";
    private static final String DBNAME = "MyDatabase";
    private long lasinsertid = 0;
    private DatabaseHelper mDbHelper;
    // Increment DB Version on any schema change
    private static final int DATABASE_VERSION = 1;
    private Context mContext;
    SQLiteDatabase conn;

    public SQLite_DBManager(Context ctx) {
        this.mContext = ctx;
    }

    private void open() {
        mDbHelper = new DatabaseHelper(mContext);
        conn = mDbHelper.getWritableDatabase();
    }

    public long getLastInsertedID() {
        return lasinsertid;
    }

    public Boolean ExecSQL(String query) {
        if (Util.validateString(query)) {
            try {
                open();
                conn.execSQL(query);
                getLastId();
                return true;
            } catch (Exception e) {
                Log.d("SQL", e.getMessage());
            } finally {
                this.close();
            }
        }
        return false;
    }

    public Boolean ExecSQL(String query, Value valores) {
        if (Util.validateString(query) && valores != null) {
            try {
                open();
                NamedParamStatement p = new NamedParamStatement(conn, query, valores);
                SQLiteStatement cmd = p.getPreparedStatement();
                System.out.print("SQL : " + cmd.toString());
                cmd.execute();
                getLastId();
                return true;
            } catch (Exception e) {
                System.err.println(e.toString());
            } finally {
                close();
            }

        }
        return false;
    }

    private void getLastId() {
        try {
            Cursor c = conn.rawQuery("SELECT last_insert_rowid();", null);
            if (c != null && c.moveToFirst()) {
                this.lasinsertid = c.getLong(0);
            }
        } catch (Exception ex) {

        }
    }

    public Cursor ExceuteSQL(String query) {
        Cursor reader = null;
        if (Util.validateString(query)) {
            try {
                open();
                reader = conn.rawQuery(query, null);
            } catch (Exception ex) {
            }
        }
        return reader;
    }

    public Cursor ExceuteSQL(String query, Value valores) {
        Cursor reader = null;
        if (Util.validateString(query)) {
            try {
                open();
                NamedParamStatement p = new NamedParamStatement(conn, query, valores);
                reader = conn.rawQuery(p.query, p.getValues());
            } catch (Exception ex) {
                Log.e("SQL", ex.getMessage());
            }
        }
        return reader;
    }


    public void close() {
        mDbHelper.close();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, URL, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table contacto (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NOMBRE TEXT, " +
                    "CORREO TEXT," +
                    "SALARIO REAL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w(DBNAME, "Upgrading database from version "
                    + oldVersion + " to "
                    + newVersion + " which destroys all old data");

            db.execSQL("DROP TABLE IF EXISTS contacto");
            onCreate(db);
        }
    }
}

class NamedParamStatement {

    private SQLiteStatement prepStmt;
    private List<String> fields = new ArrayList<String>();
    public List<String> values = new ArrayList<>();
    public String query;

    public NamedParamStatement(SQLiteDatabase conn, String sql, Value values) throws SQLException {
        int pos;
        while ((pos = sql.indexOf("@")) != -1) {
            int end = -1;
            end = checkRegexPos(end, sql.substring(pos).indexOf(" "));
            end = checkRegexPos(end, sql.substring(pos).indexOf(","));
            end = checkRegexPos(end, sql.substring(pos).indexOf(";"));
            end = checkRegexPos(end, sql.substring(pos).indexOf(")"));
            if (end == -1) {
                end = sql.length();
            } else {
                end += pos;
            }
            fields.add(sql.substring(pos, end));
            sql = sql.substring(0, pos) + "?" + sql.substring(end);
        }
        this.query = sql;
        prepStmt = conn.compileStatement(sql);
        prepareStatement(values);
    }


    private void prepareStatement(Value valores) {
        if (valores != null && valores.getList() != null && valores.getList().size() > 0) {
            List<Node> values = valores.getList();
            if (values != null) {
                for (Node prime : values) {
                    this.AddWithValue(prime.key, prime.value);
                }
            }
        }
    }

    private int checkRegexPos(int end, int newPos) {
        if (newPos == -1) {
            return end;
        }

        if (end == -1 && newPos > 0) {
            return newPos;
        }

        if (newPos > 0 && newPos < end) {
            return newPos;
        }
        return end;
    }

    public SQLiteStatement getPreparedStatement() {
        return prepStmt;
    }

    public void executeQuery() throws SQLException {
        prepStmt.execute();
    }

    public void close() throws SQLException {
        prepStmt.close();
    }

    public void setInt(String name, int value) throws SQLException {
        prepStmt.bindString(getIndex(name), String.valueOf(value));
    }

    private int getIndex(String name) {
        return fields.indexOf(name) + 1;
    }

    void AddWithValue(String key, String value) {
        try {
            int i = getIndex(key);
            prepStmt.bindString(i, value);
            if (i > values.size()) {
                values.add(value);
            } else {
                values.add(i, value);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public String[] getValues() {
        String[] d = new String[values.size()];
        int i = 0;
        while (i < values.size()) {
            d[i] = values.get(i);
            i++;
        }
        return d;
    }
}
