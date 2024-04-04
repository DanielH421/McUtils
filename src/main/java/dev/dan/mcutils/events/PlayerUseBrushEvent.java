package dev.dan.mcutils.events;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerUseBrushEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    @Getter
    private ItemStack brush;

    @Getter
    private Block brushedBlock;

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

}