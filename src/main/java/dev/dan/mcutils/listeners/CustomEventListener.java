package dev.dan.mcutils.listeners;

import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.events.PlayerUseBrushEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class CustomEventListener implements Listener {
    private static McUtils plugin = McUtils.getInstance();

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent e){
        if(!e.getItem().getType().equals(Material.BRUSH))
            return;

        PlayerUseBrushEvent brushEvent = new PlayerUseBrushEvent(e.getPlayer(), e.getItem());
        Bukkit.getPluginManager().callEvent(brushEvent);
    }


    @EventHandler
    public void onBrush(PlayerUseBrushEvent e){
        System.out.println(e.getBrushedBlock().getType());
    }


}
