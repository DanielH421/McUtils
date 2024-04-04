package dev.dan.mcutils.loot;

import dev.dan.mcutils.item.ItemCreator;
import dev.dan.mcutils.item.drops.ItemDrop;
import org.bukkit.inventory.ItemStack;

public class LootItem<T> {
    private ItemDrop drop;
    private final double weight;
    private final String id;
    private final ItemStack item;

    public LootItem(String id, ItemStack item, double weight) {
        this.id = id;
        this.item = item;
        this.weight = weight;
    }

    public void register(LootSection<T> section) {
        section.addDecision(weight, (T) this);
    }

    public T addItemDrop(ItemDrop drop) {
        this.drop = drop;
        return (T) this;
    }

    public ItemStack getItem() {
        return item;
    }

    public double getWeight() {
        return weight;
    }

    public String getId() {
        return id;
    }

    public ItemCreator getItemCreator() {
        return new ItemCreator(item);
    }

    public ItemDrop getDrop() {
        return drop;
    }

}