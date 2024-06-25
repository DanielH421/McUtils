package dev.dan.mcutils.listeners;

import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.events.EntityKilledByEntityEvent;
import dev.dan.mcutils.events.PlayerUseBrushEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class CustomEventListener implements Listener {
    private static final McUtils plugin = McUtils.getInstance();

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent e){
        if(!e.getItem().getType().equals(Material.BRUSH))
            return;

        PlayerUseBrushEvent brushEvent = new PlayerUseBrushEvent(e.getPlayer(), e.getItem());
        Bukkit.getPluginManager().callEvent(brushEvent);
        e.setCancelled(brushEvent.isCancelled());

    }

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if(!event.getEntity().isDead())
            return;
        EntityKilledByEntityEvent event1 = new EntityKilledByEntityEvent(event);
        Bukkit.getPluginManager().callEvent(event1);
        event.setCancelled(event1.isCancelled());
    }
}
