package dev.dan.mcutils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.dan.mcutils.pluginmanager.ManagedPlugin;

import java.sql.SQLException;

public abstract class MCDatabase {

    public ConnectionSource connection;


    // The logging messages are only here while I write all this database related stuff.
    // If I want to keep them I MUST make it nicer.
    public MCDatabase(String host, int port, String username, String password, String database, ManagedPlugin plugin) {
        try {
            plugin.getCustomLogger().log("&" +
                    plugin.getSecondaryColor() +
                            "Using &8MySQL &" +
                            plugin.getSecondaryColor() +
                            "for " + database + ".", true);

            String url = host + ":" + port + "/" + database + "?user=" + username + "&password=" + password;
            connection = new JdbcConnectionSource("jdbc:mysql://" + url);
            initialize();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public MCDatabase(String filePath, ManagedPlugin plugin) {
        try {
            plugin.getCustomLogger().log("&" +
                    plugin.getSecondaryColor() +
                            "Using &8SQLite &" +
                            plugin.getSecondaryColor() +
                            "for " + filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length()) + ".", true);

            connection = new JdbcConnectionSource("jdbc:sqlite:" + filePath);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void createTable(Class c){
        try {
            TableUtils.createTableIfNotExists(connection, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected Dao createDao(Class c) {
        Dao dao = null;
        try {
            dao = DaoManager.createDao(connection, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }


    protected abstract void initialize();

}
