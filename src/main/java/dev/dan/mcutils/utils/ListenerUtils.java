package dev.dan.mcutils.utils;

import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import org.bukkit.event.Listener;

public class ListenerUtils {
    public static void registerListener(ManagedPlugin p, Listener l){
        p.getServer().getPluginManager().registerEvents(l, p);
        p.logger.log("&"+p.getSecondaryColor()+"Listener: &8" + l.getClass().getSimpleName() + " &"+p.getSecondaryColor()+"has been registered!", true);
    }
}
