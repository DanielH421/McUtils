package dev.dan.mcutils.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.enums.PlayerAction;
import dev.dan.mcutils.events.PlayerClickDroppedItemEvent;
import dev.dan.mcutils.events.PlayerLeftClickEvent;
import dev.dan.mcutils.events.PlayerRightClickEvent;
import org.bukkit.FluidCollisionMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

public class ProtocolListener {
    private static RayTraceResult rayTraceResult = null;

    //This event will only be called if the player has an item in their hand.
    //No packets are sent if they do not have any item in their hand.
    private static PacketAdapter rightClickListener = new PacketAdapter(McUtils.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.BLOCK_PLACE) {
        @Override
        public void onPacketReceiving(PacketEvent e) {
            // Get rayTraceResult from the world thread. Unfortunately this is how it's done.
            new BukkitRunnable(){
                @Override
                public void run(){
                    rayTraceResult = e.getPlayer().getWorld().rayTrace(e.getPlayer().getEyeLocation(), e.getPlayer().getEyeLocation().getDirection(), 4, FluidCollisionMode.NEVER, true, 0.125, p -> !e.getPlayer().equals(p));
                }
            }.runTask(McUtils.getInstance());



            /*
            RIGHT CLICK
            get block & face, action == right click air || block.
             */
            if(rayTraceResult == null){
                // result is air
                PlayerRightClickEvent rightClickEvent = new PlayerRightClickEvent(e.getPlayer(), PlayerAction.RIGHT_CLICK_AIR, e.getPlayer().getInventory().getItemInMainHand(), null, null);
                McUtils.getInstance().callSyncEvent(rightClickEvent);
                return;
            }
            // result is block or entity
            PlayerRightClickEvent rightClickEvent = new PlayerRightClickEvent(e.getPlayer(), PlayerAction.RIGHT_CLICK_BLOCK, e.getPlayer().getInventory().getItemInMainHand(), rayTraceResult.getHitBlock(), rayTraceResult.getHitBlockFace());
            McUtils.getInstance().callSyncEvent(rightClickEvent);


            /*
            PlayerClickDroppedItemEvent
            PlayerAction == PlayerAction.RIGHT_CLICK;
             */
            if(rayTraceResult.getHitEntity() == null)
                return;
            Entity entity = rayTraceResult.getHitEntity();
            if(entity != null){
                if (entity instanceof Item) {
                    PlayerClickDroppedItemEvent event = new PlayerClickDroppedItemEvent(e.getPlayer(), ((Item) entity), PlayerAction.RIGHT_CLICK);
                    McUtils.getInstance().callSyncEvent(event);
                }
            }

        }
    };

    private static PacketAdapter leftClickListener = new PacketAdapter(McUtils.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.ARM_ANIMATION) {
        @Override
        public void onPacketReceiving(PacketEvent e) {
            // Get rayTraceResult from the world thread. Unfortunately this is how it's done.
            new BukkitRunnable(){
                @Override
                public void run(){
                    rayTraceResult = e.getPlayer().getWorld().rayTrace(e.getPlayer().getEyeLocation(), e.getPlayer().getEyeLocation().getDirection(), 4, FluidCollisionMode.NEVER, true, 0.125, p -> !e.getPlayer().equals(p));
                }
            }.runTask(McUtils.getInstance());



            /*
            LEFT CLICK
            Ray trace blocks distance of 4, get block & face, action == right click air || block.
             */
            if(rayTraceResult == null){
                //result is air
                PlayerLeftClickEvent leftClickEvent = new PlayerLeftClickEvent(e.getPlayer(), PlayerAction.LEFT_CLICK_AIR, e.getPlayer().getInventory().getItemInMainHand(), null, null);
                McUtils.getInstance().callSyncEvent(leftClickEvent);
                return;
            }
            //result is block or entity
            PlayerLeftClickEvent leftClickEvent = new PlayerLeftClickEvent(e.getPlayer(), PlayerAction.LEFT_CLICK_BLOCK, e.getPlayer().getInventory().getItemInMainHand(), rayTraceResult.getHitBlock(), rayTraceResult.getHitBlockFace());
            McUtils.getInstance().callSyncEvent(leftClickEvent);



            /*
            PlayerClickDroppedItemEvent
            PlayerAction == PlayerAction.LEFT_CLICK;
             */
            if(rayTraceResult.getHitEntity() == null)
                return;
            Entity entity = rayTraceResult.getHitEntity();
            if(entity != null){
                if (entity instanceof Item) {
                    PlayerClickDroppedItemEvent event = new PlayerClickDroppedItemEvent(e.getPlayer(), ((Item) entity), PlayerAction.LEFT_CLICK);
                    McUtils.getInstance().callSyncEvent(event);
                }
            }
        }
    };



    /*
        Registers all packet listeners through protocollib
     */
    public static void registerProtocolListeners(){
        ProtocolLibrary.getProtocolManager().addPacketListener(rightClickListener);
        ProtocolLibrary.getProtocolManager().addPacketListener(leftClickListener);
    }
}
