package dev.dan.mcutils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public abstract class TableTemplate<T> {
    public Dao<T, String> dao;

    public abstract void initialize();

    protected void initialize(ConnectionSource connection) {
        try {
            TableUtils.createTableIfNotExists(connection, this.getClass());
            this.dao = (Dao<T, String>) DaoManager.createDao(connection, this.getClass());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void add(T t) {
        try {
            dao.createIfNotExists(t);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void update(T t){
        try {
            dao.update(t);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }


}
