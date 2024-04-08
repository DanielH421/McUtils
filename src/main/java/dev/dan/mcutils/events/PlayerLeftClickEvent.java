package dev.dan.mcutils.events;

import dev.dan.mcutils.enums.PlayerAction;
import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerLeftClickEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    @Getter
    protected ItemStack item;

    @Getter
    protected Block blockClicked;

    @Getter
    protected BlockFace blockFace;

    @Getter
    private Event.Result useClickedBlock;

    @Getter
    private Event.Result useItemInHand;

    @Getter
    private EquipmentSlot hand;

    @Getter
    protected PlayerAction action;

    public PlayerLeftClickEvent(@NotNull final Player who, PlayerAction action,@Nullable final ItemStack item, @Nullable final Block clickedBlock, @NotNull final BlockFace clickedFace) {
        this(who, action,item, clickedBlock, clickedFace, EquipmentSlot.HAND);
    }

    public PlayerLeftClickEvent(@NotNull final Player who, PlayerAction action,@Nullable final ItemStack item, @Nullable final Block clickedBlock, @NotNull final BlockFace clickedFace, @Nullable final EquipmentSlot hand) {
        super(who);
        this.item = item;
        this.blockClicked = clickedBlock;
        this.blockFace = clickedFace;
        this.hand = hand;
        this.action = action;

        useItemInHand = Event.Result.DEFAULT;
        useClickedBlock = clickedBlock == null ? Event.Result.DENY : Event.Result.ALLOW;
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

