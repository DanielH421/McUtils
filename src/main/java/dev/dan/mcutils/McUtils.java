package dev.dan.mcutils;

import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import dev.dan.mcutils.pluginmanager.PluginManager;
import dev.dan.mcutils.protocol.ProtocolListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class McUtils extends ManagedPlugin {

    @Getter
    private static McUtils instance;



    @Override
    public void enable() {
        instance = this;
        ProtocolListener.registerProtocolListeners();
    }

    @Override
    public void disable() { }

    @Override
    public String getVersion() {
        return "v1.0.0";
    }

    @Override
    public String getPrimaryColor() {
        return "c";
    }

    @Override
    public String getSecondaryColor() {
        return "4";
    }


    public void registerPlugin(ManagedPlugin p){
        PluginManager.registerPlugin(p);
    }
}
