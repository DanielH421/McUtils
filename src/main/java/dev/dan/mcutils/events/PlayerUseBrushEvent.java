package dev.dan.mcutils.events;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerUseBrushEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;


    @Getter
    private final ItemStack brush;

    @Getter
    private final Block brushedBlock;

    public PlayerUseBrushEvent(@NotNull Player player, ItemStack brush) {
        super(player);
        this.brush = brush;
        this.brushedBlock = player.getTargetBlock(null, 5);
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
        cancelled = b;
    }
}