package dev.dan.mcutils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import lombok.Getter;

import java.sql.SQLException;

@Getter
public abstract class MCDatabase {

    public DatabaseConnection connection;


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
            connection = new DatabaseConnection(new JdbcConnectionSource("jdbc:mysql://" + url), plugin);
        } catch (SQLException e){
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }


    public MCDatabase(String filePath, ManagedPlugin plugin) {
        try {
            plugin.getCustomLogger().log("&" +
                    plugin.getSecondaryColor() +
                            "Using &8SQLite &" +
                            plugin.getSecondaryColor() +
                            "for " + filePath.substring(filePath.lastIndexOf("\\") + 1) + ".", true);

            connection = new DatabaseConnection(new JdbcConnectionSource("jdbc:sqlite:" + filePath), plugin);
        } catch (SQLException e) {
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }


    protected void createTable(Class c){
        try {
            TableUtils.createTableIfNotExists(getConnectionSource(), c);
        } catch (SQLException e) {
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }


    protected Dao createDao(Class c) {
        Dao dao = null;
        try {
            dao = DaoManager.createDao(getConnectionSource(), c);
        } catch (SQLException e) {
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
        return dao;
    }


    public ConnectionSource getConnectionSource() {
        return getConnection().getConnectionSource();
    }

}
