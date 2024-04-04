package dev.dan.mcutils.lists;

import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public abstract class ConditionClearHashMap extends HashMap {

    public ConditionClearHashMap(ManagedPlugin plugin, int period){
        new BukkitRunnable(){
            @Override
            public void run(){
                clear();
            }
        }.runTaskTimer(plugin, 0, period);
    }


    public abstract void clear();


}
