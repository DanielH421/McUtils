package dev.dan.mcutils.pluginmanager;

import java.util.HashMap;

public class PluginManager {

    public static HashMap<String, ManagedPlugin> plugins = new HashMap<>();
    public static void registerPlugin(ManagedPlugin plugin){
        plugins.put(plugin.getName(), plugin);
        plugin.logger.log("&8McUtils is managing this plugin.", true);
    }
}
