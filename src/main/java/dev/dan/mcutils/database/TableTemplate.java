package dev.dan.mcutils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;
import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.logger.Logger;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.HashMap;

// I'm not too proud of all this database stuff. The code isn't great. But it works for now.
public abstract class TableTemplate<T, I> {
    public Dao<T, I> dao;
    private DatabaseConnection connection;

    private HashMap<I, T> changes = new HashMap<>();

    public abstract void initialize();

    protected void initialize(DatabaseConnection connection) {
        this.connection = connection;
        try {
            TableUtils.createTableIfNotExists(connection.getConnectionSource(), this.getClass());
            this.dao = (Dao<T, I>) DaoManager.createDao(connection.getConnectionSource(), this.getClass());

            new BukkitRunnable(){
                @Override
                public void run() {
                    saveAllChanges();
                }
            }.runTaskTimerAsynchronously(McUtils.getInstance(), 1200, 1200);

        } catch (SQLException e){
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }

    private void add(T t) {
        try {
            dao.createIfNotExists(t);
        } catch (SQLException e){
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }


    public void update(T t){
        try {
            add(t);
            dao.update(t);
        } catch (SQLException e){
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
    }


    public void submitChanges(I id, T t){
        if (changes.containsKey(id))
            changes.remove(id);
        changes.put(id, t);
    }


    public T getById(I id){
        if (changes.containsKey(id)){
            return changes.get(id);
        }
        try{
            return dao.queryForId(id);
        } catch (SQLException e){
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }
        return null;
    }


    public void saveAllChanges() {
        changes.values().forEach(change -> update(change));
        changes.clear();
        Logger logger = connection.getPlugin().getCustomLogger();
        logger.log( logger.getSecondaryColor() + "Autosaving table " + logger.getPrimaryColor() + dao.getTableName() + ".", true);
    }


}
