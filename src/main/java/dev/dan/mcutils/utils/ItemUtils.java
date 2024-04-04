package dev.dan.mcutils.utils;

import com.jeff_media.morepersistentdatatypes.DataType;
import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.nbt.BasicKeys;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ItemUtils {

    // all of this is also very old, and needs to be replaced.
    // TODO - Redo this entire thing.

    public static void setPlayerOwned(Item item, Player p){
        item.getPersistentDataContainer().set(BasicKeys.Keys.PLAYER_OWNED.getKey(), DataType.PLAYER, p);
    }
    public static OfflinePlayer getOwningPlayer(Item item){
        if(!item.getPersistentDataContainer().has(BasicKeys.Keys.PLAYER_OWNED.getKey()))
            return null;
        return item.getPersistentDataContainer().get(BasicKeys.Keys.PLAYER_OWNED.getKey(), DataType.PLAYER);

    }
    public static boolean doesPlayerOwn(Item item, Player p){
        if(!item.getPersistentDataContainer().has(BasicKeys.Keys.PLAYER_OWNED.getKey()))
            return true;
        return Objects.equals(item.getPersistentDataContainer().get(BasicKeys.Keys.PLAYER_OWNED.getKey(), DataType.PLAYER), p);
    }


    public static void setClaimedGroundItem(Item item){
        item.getPersistentDataContainer().set(new NamespacedKey(McUtils.getInstance(), "is-claimed"), PersistentDataType.INTEGER, 1);
    }

    public static boolean isClaimedGroundItem(Item item){
        return item.getPersistentDataContainer().has(new NamespacedKey(McUtils.getInstance(), "is-claimed"), PersistentDataType.INTEGER);
    }
}
