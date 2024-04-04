package dev.dan.mcutils.events;

import dev.dan.mcutils.enums.PlayerAction;

import lombok.Getter;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerClickDroppedItemEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    @Getter
    protected Item item;

    @Getter
    protected ItemStack itemInMainHand;

    @Getter
    protected ItemStack itemInOffHand;

    @Getter
    protected double distance;

    @Getter
    protected PlayerAction action;




    public PlayerClickDroppedItemEvent(@NotNull Player player, Item item, PlayerAction action) {
        super(player);
        this.action = action;
        this.item = item;
        this.distance = player.getEyeLocation().distance(item.getLocation());
        this.itemInMainHand = player.getInventory().getItemInMainHand();
        this.itemInOffHand = player.getInventory().getItemInOffHand();
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
