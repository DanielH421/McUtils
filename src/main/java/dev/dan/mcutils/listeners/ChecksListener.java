package dev.dan.mcutils.listeners;


import com.destroystokyo.paper.MaterialTags;
import dev.dan.mcutils.item.ItemCreator;
import dev.dan.mcutils.nbt.BasicKeys;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

/**
 * This class is a listener that handles various events related to inventory checks.
 * It cancels the events under certain conditions to enforce inventory checks.
 */
public class ChecksListener implements Listener {


    private static final Set<Material> containerTypes = EnumSet.of(
            Material.CHEST, Material.DROPPER,
            Material.HOPPER, Material.DISPENSER,
            Material.TRAPPED_CHEST, Material.BREWING_STAND,
            Material.FURNACE, Material.BLAST_FURNACE
    );



    @EventHandler
    public void onPlace(PlayerInteractEvent e) {
        if (isInvalidPlaceEvent(e)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerAttemptPickupItemEvent e) {
        e.setCancelled(!new ItemCreator(e.getItem().getItemStack()).canMobPickup());
    }



    private boolean isInvalidPlaceEvent(PlayerInteractEvent event) {
        return !event.getAction().isLeftClick() && hasItemPlaceable(event)
                && !event.getPlayer().isSneaking() && hasClickedBlock(event)
                && isTagged(event.getClickedBlock());
    }

    private boolean hasItemPlaceable(PlayerInteractEvent event) {
        return Optional.ofNullable(event.getItem())
                .filter(BasicKeys::isPlaceable)
                .isPresent();
    }

    private boolean hasClickedBlock(PlayerInteractEvent event) {
        return Optional.ofNullable(event.getClickedBlock()).isPresent();
    }

    private boolean isTagged(Block b) {
        return MaterialTags.SHULKER_BOXES.isTagged(b)
                || MaterialTags.FENCE_GATES.isTagged(b)
                || MaterialTags.DOORS.isTagged(b)
                || containerTypes.contains(b.getType());
    }
}

