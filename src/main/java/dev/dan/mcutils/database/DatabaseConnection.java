package dev.dan.mcutils.database;

import com.j256.ormlite.support.ConnectionSource;
import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import lombok.Getter;

@Getter
public class DatabaseConnection {

    private final ConnectionSource connectionSource;
    private final ManagedPlugin plugin;


    public DatabaseConnection(ConnectionSource connection, ManagedPlugin plugin) {
        this.connectionSource = connection;
        this.plugin = plugin;
    }
}
