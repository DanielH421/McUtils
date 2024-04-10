package dev.dan.mcutils.pluginmanager;

import co.aikar.commands.BukkitCommandManager;
import dev.dan.mcutils.McUtils;
import org.bukkit.Bukkit;

public abstract class Addon extends ManagedPlugin {

    public McUtils mcutils = null;

    @Override
    public void onEnable() {

        String topBar = "&"+getSecondaryColor()+"&m                     &r&"+getPrimaryColor()+" "+getName()+" &"+getSecondaryColor()+"&m                     ";
        getCustomLogger().log(topBar);
        long startTime = System.nanoTime();

        this.mcutils = (McUtils) Bukkit.getPluginManager().getPlugin("McUtils");
        mcutils.registerPlugin(this);


        if (Bukkit.getPluginManager().getPlugin(getParentPlugin()) == null) {
            getCustomLogger().error("&c" + this.getName() + " depends on " + getParentPlugin() + " but " + getParentPlugin() + "is not loaded.", true);
            getCustomLogger().error("&cThis addon could be missing " + getParentPlugin() + " as a dependency, or " + getParentPlugin() + " is not found in the plugin folder.", true);
            this.disable();
            return;
        }

        commandManager = new BukkitCommandManager(this);
        enable();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        StringBuilder secondBar = new StringBuilder("&"+getSecondaryColor()+"&m &r&"+getPrimaryColor()+" Loaded In "+duration+"ᴍs &"+getSecondaryColor()+"&m");
        int diff = topBar.length() - secondBar.length();

        for(int i = 0; i < Math.round((float) diff / 2); i++) {
            secondBar.insert(4, " ");
            secondBar.append(" ");
        }
        getCustomLogger().log(secondBar.toString());
    }


    public abstract void enable();
    public abstract void disable();


    public abstract String getParentPlugin();

}
