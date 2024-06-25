package dev.dan.mcutils.item;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.item.handlers.ItemHandler;
import dev.dan.mcutils.nbt.BasicKeys;
import dev.dan.mcutils.utils.StringUtils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.EventExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Getter
public class ItemCreator {
    private ItemStack stack;

    public ItemCreator(ItemStack i) {
        this.stack = i;
    }

    public ItemCreator(Material mat) {
        this(mat, 1);
    }

    public ItemCreator(Material mat, String name) {
        this(mat, 1, name);
    }

    public ItemCreator(Material mat, int amount) {
        this(mat, amount, null);
    }

    public ItemCreator(Material mat, int amount, String name) {
        ItemStack i = new ItemStack(mat, amount);
        if (name != null) {
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(StringUtils.altColor(name));
            i.setItemMeta(meta);
        }
        this.stack = i;
    }


    public ItemCreator setName(String name) {
        updateMeta(meta -> meta.setDisplayName(StringUtils.altColor(name)));
        return this;
    }

    public boolean isPlaceable() {
        if (stack.getItemMeta() == null)
            return true;
        return !stack.getItemMeta().getPersistentDataContainer().has(BasicKeys.Keys.NOT_PLACEABLE.getKey()) || Boolean.TRUE.equals(stack.getItemMeta().getPersistentDataContainer().get(BasicKeys.Keys.NOT_PLACEABLE.getKey(), PersistentDataType.BOOLEAN));
    }

    public ItemCreator setPlaceable(boolean placeable) {
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(BasicKeys.Keys.NOT_PLACEABLE.getKey(), PersistentDataType.BOOLEAN, placeable);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemCreator setMobPickup(boolean pickup) {
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(BasicKeys.Keys.MOB_PICKUP.getKey(), PersistentDataType.BOOLEAN, pickup);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemCreator setClickable(boolean clickable) {
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(BasicKeys.Keys.NOT_CLICKABLE.getKey(), PersistentDataType.BOOLEAN, !clickable);
        return this;
    }

    public boolean isClickable() {
        if(stack.getItemMeta() == null)
            return true;
        return Boolean.FALSE.equals(stack.getItemMeta().getPersistentDataContainer().get(BasicKeys.Keys.NOT_CLICKABLE.getKey(), PersistentDataType.BOOLEAN));
    }

    public boolean canMobPickup() {
        if (stack.getItemMeta() == null)
            return true;
        return !stack.getItemMeta().getPersistentDataContainer().has(BasicKeys.Keys.MOB_PICKUP.getKey()) || Boolean.TRUE.equals(stack.getItemMeta().getPersistentDataContainer().get(BasicKeys.Keys.MOB_PICKUP.getKey(), PersistentDataType.BOOLEAN));
    }

    public boolean isGlowing() {
        if (stack.getItemMeta() == null)
            return true;
        return !stack.getItemMeta().getPersistentDataContainer().has(BasicKeys.Keys.GLOW_ON_DROP.getKey()) || Boolean.TRUE.equals(stack.getItemMeta().getPersistentDataContainer().get(BasicKeys.Keys.GLOW_ON_DROP.getKey(), PersistentDataType.BOOLEAN));
    }

    public ItemCreator setGlowing(boolean glowing) {
        stack.getItemMeta().getPersistentDataContainer().set(BasicKeys.Keys.GLOW_ON_DROP.getKey(), PersistentDataType.BOOLEAN, glowing);
        return this;
    }

    public ItemCreator setNameOnDrop(boolean named) {
        stack.getItemMeta().getPersistentDataContainer().set(BasicKeys.Keys.DISPLAY_NAME_ON_DROP.getKey(), PersistentDataType.BOOLEAN, named);
        return this;
    }

    public boolean isNamedOnDrop() {
        if (stack.getItemMeta() == null)
            return true;
        return !stack.getItemMeta().getPersistentDataContainer().has(BasicKeys.Keys.DISPLAY_NAME_ON_DROP.getKey()) || Boolean.TRUE.equals(stack.getItemMeta().getPersistentDataContainer().get(BasicKeys.Keys.DISPLAY_NAME_ON_DROP.getKey(), PersistentDataType.BOOLEAN));
    }

    public ItemCreator addStringContainer(NamespacedKey key, String string) {
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, string);
        stack.setItemMeta(meta);
        return this;
    }

    public String getStringContainer(NamespacedKey key) {
        return stack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }

    public ItemCreator addIntegerContainer(NamespacedKey key, int num) {
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, num);
        stack.setItemMeta(meta);
        return this;
    }

    public Integer getIntegerContainer(NamespacedKey key) {
        return stack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
    }

    public ItemCreator addByteArrayContainer(NamespacedKey key, byte[] bytes) {
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE_ARRAY, bytes);
        stack.setItemMeta(meta);
        return this;
    }

    public byte[] getByteArrayContainer(NamespacedKey key) {
        return stack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.BYTE_ARRAY);
    }

    public ItemCreator addHandler(ItemHandler handler) {
        Listener l = new Listener() {
        };

        EventExecutor executor = (ignored, event) -> handler.onEvent(event);

        handler.getPlugin().getServer().getPluginManager().registerEvent(handler.getEventClass(), l, EventPriority.NORMAL, executor, handler.getPlugin());
        return this;
    }

    public ItemCreator addUUID() {
        addStringContainer(new NamespacedKey(McUtils.getInstance(), "item-uuid"), UUID.randomUUID().toString());
        return this;
    }

    public ItemCreator setLore(List<String> lore) {
        ItemMeta meta = stack.getItemMeta();
        List<String> l = new ArrayList<>();
        for (String str : lore) {
            l.add(StringUtils.altColor(str));
        }
        meta.setLore(l);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemCreator addLore(String line) {
        ItemMeta meta = stack.getItemMeta();
        List<String> lore;
        if (meta.lore() == null) {
            lore = new ArrayList<>();
        } else {
            lore = meta.getLore();
        }
        lore.add(StringUtils.altColor(line));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemCreator updateStack(ItemStack item) {
        this.stack = item;
        return this;
    }

    public ItemMeta getItemMeta() {
        return stack.getItemMeta();
    }

    public ItemCreator setItemMeta(ItemMeta meta) {
        stack.setItemMeta(meta);
        return this;
    }

    public GuiItem asGuiItem() {
        return new GuiItem(getStack());
    }

    private void updateMeta(Consumer<ItemMeta> metaUpdater) {
        ItemMeta meta = stack.getItemMeta();
        metaUpdater.accept(meta);
        stack.setItemMeta(meta);
    }
}
