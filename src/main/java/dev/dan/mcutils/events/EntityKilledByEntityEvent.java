package dev.dan.mcutils.events;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntityKilledByEntityEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    @Getter
    private Entity killer;
    @Getter
    private EntityDamageByEntityEvent parentEvent;

    public EntityKilledByEntityEvent(EntityDamageByEntityEvent parentEvent) {
        super(parentEvent.getEntity());
        this.killer = parentEvent.getDamager();
        this.parentEvent = parentEvent;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
