package dev.dan.mcutils.nbt;

import dev.dan.mcutils.McUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicKeys {

    public static boolean isPlaceable(ItemStack item){
        if(item.getItemMeta() == null)
            return true;
        return !item.getItemMeta().getPersistentDataContainer().has(Keys.NOT_PLACEABLE.getKey()) || item.getItemMeta().getPersistentDataContainer().get(Keys.NOT_PLACEABLE.getKey(), PersistentDataType.INTEGER).equals(0);
    }

    public static boolean isClickable(ItemStack item){
        if(item.getItemMeta() == null)
            return true;
        return !item.getItemMeta().getPersistentDataContainer().has(Keys.NOT_CLICKABLE.getKey()) || item.getItemMeta().getPersistentDataContainer().get(Keys.NOT_CLICKABLE.getKey(), PersistentDataType.INTEGER).equals(0);
    }

    public enum Keys {
        MATERIAL("Material"),
        NOT_PLACEABLE("Not-Placeable"),
        MOB_PICKUP("Mob-Pickup"),
        DISPLAY_NAME_ON_DROP("Display-Name-On-Drop"),
        GLOW_ON_DROP("Glow-On-Drop"),
        PLAYER_OWNED("Player-Owned"),
        UNIQUE_ID("Unique-ID"),
        NOT_CLICKABLE("Not-Clickable");

        private final String key;
        Keys(String key){
            this.key = key;
        }
        public NamespacedKey getKey(){
            return new NamespacedKey(McUtils.getInstance(), key);
        }
        public NamespacedKey getKey(JavaPlugin plugin){
            return new NamespacedKey(plugin, key);
        }
    }
}
