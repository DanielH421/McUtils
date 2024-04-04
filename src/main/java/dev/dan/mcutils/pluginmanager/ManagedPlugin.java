package dev.dan.mcutils.pluginmanager;

import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.logger.Logger;
import dev.dan.mcutils.utils.ListenerUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class ManagedPlugin extends JavaPlugin {

    public McUtils mcutils = null;

    public Logger logger = new Logger(getPrefix());




    @Override
    public void onEnable(){
        String topBar = "&"+getSecondaryColor()+"&m                     &r&"+getPrimaryColor()+" "+getName()+" &"+getSecondaryColor()+"&m                     ";
        logger.log(topBar);
        long startTime = System.nanoTime();
        if(this.getName().equals(McUtils.class.getName())){
            this.mcutils = (McUtils) this;
        } else {
            this.mcutils = (McUtils) Bukkit.getPluginManager().getPlugin("McUtils");
            mcutils.registerPlugin(this);
        }

        enable();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        StringBuilder secondBar = new StringBuilder("&"+getSecondaryColor()+"&m &r&"+getPrimaryColor()+" Loaded In "+duration+"ᴍs &"+getSecondaryColor()+"&m");
        int diff = topBar.length() - secondBar.length();

        for(int i = 0; i < Math.round((float) diff / 2); i++) {
            secondBar.insert(4, " ");
            secondBar.append(" ");
        }
        logger.log(secondBar.toString());
    }

    public abstract void enable();
    public abstract void disable();



    public abstract String getVersion();
    public String getPrefix() {
        return "&8[&"+getPrimaryColor()+this.getName()+"&8]";
    }

    public abstract String getPrimaryColor();
    public abstract String getSecondaryColor();

    public void registerListener(Listener l){
        ListenerUtils.registerListener(this, l);
    }


    public void callSyncEvent(Event e){
        new BukkitRunnable(){
            @Override
            public void run(){
                getServer().getPluginManager().callEvent(e);
            }
        }.runTask(this);
    }
}
