package dev.dan.mcutils.item.drops;

import com.jeff_media.morepersistentdatatypes.DataType;
import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.item.ItemCreator;
import dev.dan.mcutils.utils.ItemUtils;
import dev.dan.mcutils.utils.WorldUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
public abstract class ItemDrop<T> {

    private ItemCreator container;

    private final DropType type;


    public ItemDrop(DropType type){this.type = type;}
    public abstract void onDrop(T type, ItemStack item, Object... optional);

    public ItemDrop setContainer(ItemCreator container) {
        this.container = container;
        return this;
    }
    public ItemDrop setContained(ItemCreator contained){
        ItemMeta meta = container.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(McUtils.getInstance(), "contained-stack"), DataType.ITEM_STACK, contained.getStack());
        container.setItemMeta(meta);
        return this;
    }

    public static class Defaults {
        public static ItemDrop PLAYER_INVENTORY = new ItemDrop<Player>(DropType.PLAYER) {
            @Override
            public void onDrop(Player p, ItemStack item, Object... optional) {
                p.getInventory().addItem(item);
            }
        };
        public static ItemDrop GROUND_WORLD = new ItemDrop<Location>(DropType.LOCATION){
            @Override
            public void onDrop(Location loc, ItemStack item, Object... optional){
                WorldUtils.dropItemNaturally(loc, new ItemCreator(item));
            }
        };

        public static ItemDrop CONTAINED_GROUND = new ItemDrop<Location>(DropType.LOCATION) {
            @Override
            public void onDrop(Location loc, ItemStack item, Object... optional) {
                if(this.getContainer() != null){
                    this.setContained(new ItemCreator(item));
                    Item ground = WorldUtils.dropItemNaturally(loc, getContainer().addUUID());
                    Player owner = (Player) optional[0];
                    ItemUtils.setPlayerOwned(ground, owner);
                } else {
                    WorldUtils.dropItemNaturally(loc, new ItemCreator(item));
                }
            }
        };

    }
}