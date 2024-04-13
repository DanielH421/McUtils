package dev.dan.mcutils.database;

import com.j256.ormlite.support.ConnectionSource;
import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import lombok.Getter;

public class DatabaseConnection {

    @Getter
    private ConnectionSource connectionSource;
    @Getter
    private ManagedPlugin plugin;


    public DatabaseConnection(ConnectionSource connection, ManagedPlugin plugin) {
        this.connectionSource = connection;
        this.plugin = plugin;
    }
}
